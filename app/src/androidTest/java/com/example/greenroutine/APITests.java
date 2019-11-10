package com.example.greenroutine;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static org.hamcrest.Matchers.not;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class APITests {
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private Context context = getApplicationContext();
    //Resources res = context.getResources();

    @Test
    public void gitHubPull() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.aboutpage)).check(matches(isDisplayed()));
        SystemClock.sleep(4000);
        onView(withId(R.id.mpcommit)).check(matches(not(withText("... Commits"))));
        onView(withId(R.id.mpIssues)).check(matches(not(withText("... Issues"))));
        onView(withId(R.id.mptest)).check(matches(not(withText("... Tests"))));
    }

    //String key = res.getString(R.string.earth911);
    @Test
    public void mapTest(){
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        onView(withId(R.id.my_recycler_view_cats)).perform(click());
        onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
        onView(withId(R.id.my_recycler_view_items)).perform(click());
        onView(withId(R.id.itemCard)).check(matches(isDisplayed()));
        SystemClock.sleep(4000);
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
}
