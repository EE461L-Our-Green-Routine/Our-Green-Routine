package com.example.greenroutine;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import com.example.greenroutine.MaterialsParser;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws IOException, JSONException {
        // Context of the app under test.
        ArrayList mp = MaterialsParser.getDatabase(System.getenv("earth911"));
        System.out.println("hey!");
    }

    @Test
    public void nearbyRecycle(){
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.my_recycler_view_cats)).perform(click());
        onView(withId(R.id.my_recycler_view_items)).perform(click());
    }
}
