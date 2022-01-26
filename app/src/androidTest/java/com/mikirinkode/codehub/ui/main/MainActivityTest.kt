package com.mikirinkode.codehub.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mikirinkode.codehub.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testListVisibility(){
        onView(withId(R.id.rv_users)).check(matches(isDisplayed()))
    }
    @Test
    fun selectList(){
        onView(withId(R.id.rv_users))
            .perform(actionOnItemAtPosition<UsersAdapter.UserViewHolder>(0, click()))

        onView(withId(R.id.tv_item_username)).check(matches(withText("mojombo")))
    }

    @Test
    fun testBackNav(){
        onView(withId(R.id.rv_users))
            .perform(actionOnItemAtPosition<UsersAdapter.UserViewHolder>(0, click()))

        onView(withId(R.id.tv_item_username)).check(matches(withText("mojombo")))

        pressBack()
        onView(withId(R.id.rv_users)).check(matches(isDisplayed()))

    }
}