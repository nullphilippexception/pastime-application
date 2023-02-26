package at.nullphilippexception.pastimeapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun GIVEN_userStartsApp_WHEN_onMainActivity_THEN_currentHobbyLabelsAreDisplayed() {
        assertOnScreen(R.id.iv_type)
        assertOnScreen(R.id.iv_participants)
        assertOnScreen(R.id.tv_accessibility)
        assertOnScreen(R.id.tv_price)
    }

    private fun assertOnScreen(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }
}