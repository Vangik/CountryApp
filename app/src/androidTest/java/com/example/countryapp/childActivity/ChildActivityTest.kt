package com.example.countryapp.childActivity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.CountryListQuery
import com.example.countryapp.FakeResponse
import com.example.countryapp.R
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.repository.CountryRepository
import io.reactivex.rxjava3.core.Observable
import okio.ByteString.Companion.encodeUtf8
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ChildActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Mock
    val countryRepo = mock(CountryRepository::class.java)

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
    fun testIsToolBarDisplayed() {
        ActivityScenario.launch(ChildActivity::class.java)

        onView(withText(R.string.toolbar_text)).check(
            matches(
                allOf(
                    withParent(withId(R.id.tb_child_activity)),
                    isDisplayed()
                )
            )
        )
    }

    @Test
    fun testNavToMainActivity() {
        `when`(countryRepo.getCountryList()).thenReturn(
            Observable.just(responseCountryList)
        )
        activityRule.scenario.onActivity { it.mainViewModel.fetchCountryList() }
        onView(withText(FakeResponse.COUNTRY_NAME)).perform(click())
        pressBack()
        onView(withText(R.string.call_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailsChildActivityInfo() {
        ActivityScenario.launch(ChildActivity::class.java)
            .onActivity {
                it.childViewModel.setRepository(countryRepo)
                `when`(countryRepo.getCountryById(FakeResponse.COUNTRY_ID)).thenReturn(
                    Observable.just(responseCountry)
                )
                it.childViewModel.fetchCountryList(FakeResponse.COUNTRY_ID)
            }

        onView(withId(R.id.tv_child_activity_country_name)).check(matches(withText(FakeResponse.COUNTRY_NAME)))
        onView(withId(R.id.tv_child_activity_country_capital)).check(matches(withText(FakeResponse.COUNTRY_CAPITAL)))
        onView(withId(R.id.tv_child_activity_country_region)).check(matches(withText(FakeResponse.COUNTRY_REGION)))
    }

}