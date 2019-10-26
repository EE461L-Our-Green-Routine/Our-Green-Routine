package com.example.greenroutine;

import android.content.Context;
import android.content.res.Resources;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class APITests {
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private Context context = getApplicationContext();

    @Test
    public void gitHubPull() throws InterruptedException {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.aboutpage)).check(matches(isDisplayed()));
        wait(1000);
    }

    @Test
    public void get_api_key() {
        Resources res = context.getResources();
        String key = res.getString(R.string.earth911);
        System.out.println("this is the key " + key);
    }
}
