package com.example.countryapp.mainActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ActivityScenario
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
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.CountryListQuery
import com.example.countryapp.FakeResponse
import com.example.countryapp.R
import com.example.countryapp.childActivity.ChildActivity
import com.example.countryapp.constants.Const
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.CountryModel
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.ChildViewModel
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import okio.ByteString.Companion.encodeUtf8
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import java.io.FileReader

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private var countryRepo = mock(CountryRepository::class.java)

    private val responseCountry: Response<CountryByIdQuery.Data> =
        CountryByIdQuery(FakeResponse.COUNTRY_ID).parse(FakeResponse.COUNTRY_BY_ID.encodeUtf8())
    private val responseCountryList: Response<CountryListQuery.Data> =
        CountryListQuery().parse(FakeResponse.COUNTRY_LIST.encodeUtf8())

    @Before
    fun setup() {
        activityRule.scenario.onActivity { it.mainViewModel.setRepository(countryRepo) }
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
    fun testChildActivityCountryDetails() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(
                responseCountryList
            )
        )
        activityRule.scenario.onActivity {it.mainViewModel.fetchCountryList() }
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed())).perform(click())

        ActivityScenario.launch(ChildActivity::class.java).onActivity { it.childViewModel.setRepository(countryRepo)
            `when`(countryRepo.getCountryById(FakeResponse.COUNTRY_ID)).thenReturn(
                Observable.just(
                    responseCountry
                )
            )
            it.childViewModel.fetchCountryList(FakeResponse.COUNTRY_ID)
        }
        onView(withId(R.id.tv_child_activity_country_name)).check(matches(withText(FakeResponse.COUNTRY_NAME)))
        onView(withId(R.id.tv_child_activity_country_capital)).check(matches(withText(FakeResponse.COUNTRY_CAPITAL)))
        onView(withId(R.id.tv_child_activity_country_region)).check(matches(withText(FakeResponse.COUNTRY_REGION)))
    }

    @Test
    fun testMainActivityCountryDetails() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(
                responseCountryList
            )
        )
        activityRule.scenario.onActivity {it.mainViewModel.fetchCountryList() }
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed()))
        onView(withText(FakeResponse.COUNTRY_CAPITAL)).check(matches(isDisplayed()))
        onView(withText(FakeResponse.COUNTRY_REGION)).check(matches(isDisplayed()))
    }

    @Test
    fun testIntentMainActivity() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(
                responseCountryList
            )
        )
        activityRule.scenario.onActivity {it.mainViewModel.fetchCountryList() }
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed())).perform(click())
        intended(allOf(hasExtra(Const.INTENT_COUNTRY_DETAILS_NAME, FakeResponse.COUNTRY_ID)))
    }

    @Test
    fun isToolBarViewDisplayed() {
        onView(withId(R.id.main_search_view)).inRoot(RootMatchers.DEFAULT).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun testBackArrowChildActivity() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(
                responseCountryList
            )
        )
        activityRule.scenario.onActivity {it.mainViewModel.fetchCountryList() }
        onView(withText(FakeResponse.COUNTRY_NAME)).perform(click())
        pressBack()
        onView(withId(R.id.rv_main_activity_country_details)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarVisible() {
        onView(withId(R.id.pb_main_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarGone() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(
                responseCountryList
            )
        )
        activityRule.scenario.onActivity {it.mainViewModel.fetchCountryList() }
        onView(withId(R.id.pb_main_activity)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

}
