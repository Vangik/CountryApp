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
import com.example.countryapp.FakeResponse
import com.example.countryapp.R
import com.example.countryapp.mainActivity.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ChildActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setup() {
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
        Thread.sleep(2000)
        onView(withText(FakeResponse.COUNTRY_NAME)).perform(click())
        pressBack()
        onView(withText(R.string.call_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailsChildActivityInfo(){
        Thread.sleep(2000)
        onView(withText(FakeResponse.COUNTRY_NAME)).check(matches(isDisplayed())).perform(click())

        Thread.sleep(600)
        onView(withId(R.id.tv_child_activity_country_name)).check(matches(withText(FakeResponse.COUNTRY_NAME)))
        onView(withId(R.id.tv_child_activity_country_capital)).check(matches(withText(FakeResponse.COUNTRY_CAPITAL)))
        onView(withId(R.id.tv_child_activity_country_region)).check(matches(withText(FakeResponse.COUNTRY_REGION)))
    }

}