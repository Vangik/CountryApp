package com.example.countryapp.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryListQuery
import com.example.countryapp.FakeResponse
import com.example.countryapp.R
import com.example.countryapp.constants.Const
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.ChildViewModel
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import okio.ByteString.Companion.encodeUtf8
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var viewModelFactory: ViewModelFactory

    @Mock
    private lateinit var countryRepo: CountryRepositoryImpl

    private val response: Response<CountryListQuery.Data> =
        CountryListQuery().parse(FakeResponse.COUNTRY_LIST.encodeUtf8())


    @Before
    fun setup() {
        Intents.init()
    }


    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testIsCountryListDisplayed() {
        onView(withId(R.id.rv_main_activity_country_details)).inRoot(RootMatchers.DEFAULT).check(
            matches(isDisplayed())
        )
    }


    @Test
    fun testChildActivityCountryInfo() {

        Thread.sleep(2000)
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed())).perform(click())
        intended(allOf(hasExtra(Const.INTENT_COUNTRY_DETAILS_NAME, FakeResponse.COUNTRY_ID)))
        Thread.sleep(600)
        onView(withId(R.id.tv_child_activity_country_name)).check(matches(withText(FakeResponse.COUNTRY_NAME)))
        onView(withId(R.id.tv_child_activity_country_capital)).check(matches(withText(FakeResponse.COUNTRY_CAPITAL)))
        onView(withId(R.id.tv_child_activity_country_region)).check(matches(withText(FakeResponse.COUNTRY_REGION)))
    }

    @Test
    fun testMainActivityCountryInfo() {
        Thread.sleep(2000)
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed()))
        onView(withText(FakeResponse.COUNTRY_CAPITAL)).check(matches(isDisplayed()))
        onView(withText(FakeResponse.COUNTRY_REGION)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewScrollable() {
        Thread.sleep(2000)

        onView(withId(R.id.rv_main_activity_country_details)).perform(
            scrollToPosition<CountryAdapter.CountryViewHolder>(130)
        )
    }

    @Test
    fun isToolBarViewDisplayed() {
        onView(withId(R.id.main_search_view)).inRoot(RootMatchers.DEFAULT).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun testBackArrowChildActivity() {
        Thread.sleep(2000)
        onView(withText(FakeResponse.COUNTRY_NAME)).perform(click())
        Thread.sleep(600)

        onView(withId(R.id.tv_child_activity_country_name)).check(matches(withText(FakeResponse.COUNTRY_NAME)))
        pressBack()
        onView(withId(R.id.rv_main_activity_country_details)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarVisible() {
        onView(withId(R.id.pb_main_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarGone() {
        Thread.sleep(3000)
        onView(withId(R.id.pb_main_activity)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }


    private fun <T : ViewModel> createViewModelFactory(repository: CountryRepositoryImpl): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
                return when {
                    viewModelClass.isAssignableFrom(MainViewModel::class.java) -> {
                        MainViewModel(repository) as T
                    }
                    viewModelClass.isAssignableFrom(ChildViewModel::class.java) -> {
                        ChildViewModel(repository) as T
                    }
                    else -> throw IllegalArgumentException("Unknown view model class " + viewModelClass)
                }
            }
        }
    }

}
