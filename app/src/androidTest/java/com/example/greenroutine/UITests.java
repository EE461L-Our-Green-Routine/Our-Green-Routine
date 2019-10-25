package com.example.greenroutine;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
public class UITests {
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void homeToAbout() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.aboutpage)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

    @Test
    public void homeToCategories() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

    @Test
    public void homeToFootprint(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.linearLayout)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

}
