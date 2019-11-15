package com.example.greenroutine;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import androidx.test.uiautomator.*;

import java.util.Collection;


@RunWith(AndroidJUnit4.class)
public class UITests {
    private UiDevice device;

    @Rule public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void getInfo(){
        device = UiDevice.getInstance(getInstrumentation());
    }

    @Test
    public void homeToAbout() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.aboutpage)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

    @Test
    public void homeToWhere() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

    @Test
    public void homeToHow() {
        onView(withId(R.id.button4)).perform(click());
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

    @Test
    public void calculateFootprint(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.linearLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.textInputEditText)).perform(typeText("battery"));
        onView(withId(R.id.calculate)).perform(click());
        //onView(withId(R.id.carbonTotal)).check(matches(withText("15.0")));
    }

    @Test
    public void categoryUI(){
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        onView(withId(R.id.my_recycler_view_cats)).perform(click());
        onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
        onView(withId(R.id.my_recycler_view_items)).perform(click());
        onView(withId(R.id.itemCard)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.homePage)).check(matches(isDisplayed()));
    }

    @Test
    public void howToUI(){
        onView(withId(R.id.button4)).perform(click());
        int cat = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_cats))).getAdapter().getItemCount();
        for(int i = 0; i<cat; i++) {
            onView(ViewMatchers.withId(R.id.my_recycler_view_cats)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            int itm = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_items))).getAdapter().getItemCount();
            for(int j = 0; j<itm; j++){
                onView(ViewMatchers.withId(R.id.my_recycler_view_items)).perform(RecyclerViewActions.actionOnItemAtPosition(j, click()));
                SystemClock.sleep(5000);
                assertEquals("com.android.chrome",device.getCurrentPackageName());
                device.pressBack();
            }
            Espresso.pressBack();
        }
        //onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        //onView(withId(R.id.my_recycler_view_cats)).perform(click());
        //onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
    }
    Activity currentActivity = null;

    public Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities =
                        ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity activity: resumedActivities){
                    Log.d("Your current activity: ", activity.getClass().getName());
                    currentActivity = activity;
                    break;
                }
            }
        });

        return currentActivity;
    }

}
