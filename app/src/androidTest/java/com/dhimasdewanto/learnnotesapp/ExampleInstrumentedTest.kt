package com.dhimasdewanto.learnnotesapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dhimasdewanto.learnnotesapp.presentation.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dhimasdewanto.learnnotesapp", appContext.packageName)
    }

    @Test
    fun testInputValue() {
        Espresso.onView(withId(R.id.menu_create)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.edit_text_title)).perform(ViewActions.typeText("A Title"))
        Espresso.onView(withId(R.id.edit_text_content)).perform(ViewActions.typeText("A Contents"))
    }

}
