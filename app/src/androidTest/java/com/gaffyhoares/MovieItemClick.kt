package com.gaffyhoares

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gaffyhoares.ui.movies.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieItemClick {

    @Test
    fun createClickTask() {

        // start up Tasks screen
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)

        // Add active task
        onView(withId(R.id.parentLayout)).perform(click())

        // Open it in details view
        onView(withText("TITLE1")).perform(click())
        // Click delete task in menu
     /*   onView(withId(R.id.menu_delete)).perform(click())

        // Verify it was deleted
        onView(withId(R.id.menu_filter)).perform(click())
        onView(withText(string.nav_all)).perform(click())*/
        onView(withText("TITLE1")).check(doesNotExist())
    }
}