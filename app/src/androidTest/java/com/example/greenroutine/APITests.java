package com.example.greenroutine;

import android.app.Activity;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.not;

import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class APITests {
    private UiDevice device;
    private static Activity currentActivity = null;
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void getInfo(){
        device = UiDevice.getInstance(getInstrumentation());
    }

    @Category(FastTest.class)
    @Test
    public void gitHubPull() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.aboutpage)).check(matches(isDisplayed()));
        SystemClock.sleep(4000);
        onView(withId(R.id.mpcommit)).check(matches(not(withText("... commits"))));
        onView(withId(R.id.mpIssues)).check(matches(not(withText("... issues"))));
        onView(withId(R.id.mptest)).check(matches(not(withText("... tests"))));
        onView(withId(R.id.mpcommit1)).check(matches(not(withText("... commits"))));
        onView(withId(R.id.mpIssues1)).check(matches(not(withText("... issues"))));
        onView(withId(R.id.mptest1)).check(matches(not(withText("... tests"))));
        onView(withId(R.id.mpcommit2)).check(matches(not(withText("... commits"))));
        onView(withId(R.id.mpIssues2)).check(matches(not(withText("... issues"))));
        onView(withId(R.id.mptest2)).check(matches(not(withText("... tests"))));
        onView(withId(R.id.mpcommit3)).check(matches(not(withText("... commits"))));
        onView(withId(R.id.mpIssues3)).check(matches(not(withText("... issues"))));
        onView(withId(R.id.mptest3)).check(matches(not(withText("... tests"))));
        onView(withId(R.id.mpcommit4)).check(matches(not(withText("... commits"))));
        onView(withId(R.id.mpIssues4)).check(matches(not(withText("... issues"))));
        onView(withId(R.id.mptest4)).check(matches(not(withText("... tests"))));
        onView(withId(R.id.totalTests)).check(matches(not(withText("0 Total Commits"))));
        onView(withId(R.id.totalCommits)).check(matches(not(withText("0 Total Issues"))));
        onView(withId(R.id.mpIssues)).check(matches(not(withText("0 Total Tests"))));
    }

    @Category(FastTest.class)
    @Test
    public void quickItemCardTest() throws UiObjectNotFoundException {
        onView(withId(R.id.button1)).perform(click());
        int cat = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_cats))).getAdapter().getItemCount();
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        if(cat == 0){
            fail("No categories!");
        }
        onView(ViewMatchers.withId(R.id.my_recycler_view_cats)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1500);
        int itm = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_items))).getAdapter().getItemCount();
        onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
        if(itm == 0){
            fail("No items!");
        }
        onView(ViewMatchers.withId(R.id.my_recycler_view_items)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //SystemClock.sleep(5000); //emulator lag
        SystemClock.sleep(500);
        onView(ViewMatchers.withId(R.id.location1)).check(matches(IsNot.not((withText("loc1")))));
        onView(ViewMatchers.withId(R.id.location2)).check(matches(IsNot.not((withText("loc2")))));
        onView(ViewMatchers.withId(R.id.location3)).check(matches(IsNot.not((withText("loc3")))));
        onView(ViewMatchers.withId(R.id.location4)).check(matches(IsNot.not((withText("loc4")))));
        onView(ViewMatchers.withId(R.id.location5)).check(matches(IsNot.not((withText("loc5")))));
        onView(ViewMatchers.withId(R.id.distance1)).check(matches(IsNot.not((withText("dist1")))));
        onView(ViewMatchers.withId(R.id.distance2)).check(matches(IsNot.not((withText("dist2")))));
        onView(ViewMatchers.withId(R.id.distance3)).check(matches(IsNot.not((withText("dist3")))));
        onView(ViewMatchers.withId(R.id.distance4)).check(matches(IsNot.not((withText("dist4")))));
        onView(ViewMatchers.withId(R.id.distance5)).check(matches(IsNot.not((withText("dist5")))));
        onView(ViewMatchers.withId(R.id.name)).check(matches(IsNot.not((withText("TextView")))));
        onView(ViewMatchers.withId(R.id.description)).check(matches(IsNot.not((withText("TextView")))));
        UiObject map = device.findObject(new UiSelector().descriptionContains("Google Map"));
        map.pinchIn(80, 10);
        UiObject loc = device.findObject(new UiSelector().descriptionContains("You are here."));
        loc.click();
        String test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location1))).getText();
        loc = device.findObject(new UiSelector().descriptionContains(test));
        loc.click();
        test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location2))).getText();
        loc = device.findObject(new UiSelector().descriptionContains(test));
        loc.click();
        test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location3))).getText();
        loc = device.findObject(new UiSelector().descriptionContains(test));
        loc.click();
        test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location4))).getText();
        loc = device.findObject(new UiSelector().descriptionContains(test));
        loc.click();
        test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location5))).getText();
        loc = device.findObject(new UiSelector().descriptionContains(test));
        loc.click();
        Espresso.pressBack();
        Espresso.pressBack();
    }

    @Category(SlowTest.class)
    @Test
    public void itemCardTest() throws UiObjectNotFoundException {
        onView(withId(R.id.button1)).perform(click());
        int cat = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_cats))).getAdapter().getItemCount();
        onView(withId(R.id.recyMainC)).check(matches(isDisplayed()));
        if(cat == 0){
            fail("No categories!");
        }
        onView(ViewMatchers.withId(R.id.my_recycler_view_cats)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(1500);
        int itm = ((RecyclerView)(getActivityInstance().findViewById(R.id.my_recycler_view_items))).getAdapter().getItemCount();
        onView(withId(R.id.recyMainI)).check(matches(isDisplayed()));
        if(itm == 0){
            fail("No items!");
        }
        for(int j = 0; j<itm; j++){
            onView(ViewMatchers.withId(R.id.my_recycler_view_items)).perform(RecyclerViewActions.actionOnItemAtPosition(j, click()));
            //SystemClock.sleep(5000); //emulator lag
            System.out.println("********** ITEM:" + j+" **************");
            SystemClock.sleep(500);
            onView(ViewMatchers.withId(R.id.location1)).check(matches(IsNot.not((withText("loc1")))));
            onView(ViewMatchers.withId(R.id.location2)).check(matches(IsNot.not((withText("loc2")))));
            onView(ViewMatchers.withId(R.id.location3)).check(matches(IsNot.not((withText("loc3")))));
            onView(ViewMatchers.withId(R.id.location4)).check(matches(IsNot.not((withText("loc4")))));
            onView(ViewMatchers.withId(R.id.location5)).check(matches(IsNot.not((withText("loc5")))));
            onView(ViewMatchers.withId(R.id.distance1)).check(matches(IsNot.not((withText("dist1")))));
            onView(ViewMatchers.withId(R.id.distance2)).check(matches(IsNot.not((withText("dist2")))));
            onView(ViewMatchers.withId(R.id.distance3)).check(matches(IsNot.not((withText("dist3")))));
            onView(ViewMatchers.withId(R.id.distance4)).check(matches(IsNot.not((withText("dist4")))));
            onView(ViewMatchers.withId(R.id.distance5)).check(matches(IsNot.not((withText("dist5")))));
            onView(ViewMatchers.withId(R.id.name)).check(matches(IsNot.not((withText("TextView")))));
            onView(ViewMatchers.withId(R.id.description)).check(matches(IsNot.not((withText("TextView")))));
            UiObject map = device.findObject(new UiSelector().descriptionContains("Google Map"));
            map.pinchIn(80, 10);
            UiObject loc = device.findObject(new UiSelector().descriptionContains("You are here."));
            loc.click();
            String test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location1))).getText();
            loc = device.findObject(new UiSelector().descriptionContains(test));
            loc.click();
            test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location2))).getText();
            loc = device.findObject(new UiSelector().descriptionContains(test));
            loc.click();
            test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location3))).getText();
            loc = device.findObject(new UiSelector().descriptionContains(test));
            loc.click();
            test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location4))).getText();
            loc = device.findObject(new UiSelector().descriptionContains(test));
            loc.click();
            test = (String) ((TextView)(getActivityInstance().findViewById(R.id.location5))).getText();
            loc = device.findObject(new UiSelector().descriptionContains(test));
            loc.click();
            Espresso.pressBack();
        }
    }

    @Category(FastTest.class)
    @Test
    public void tipTest(){
        SystemClock.sleep(4000);
        onView(withId(R.id.DOTD)).check(matches(not(withText(""))));
        onView(withId(R.id.IOTD)).check(matches(not(withText(""))));
    }


    final static class Income{
        final static String a =  "Average";
        final static String u =  "Less than $10,000";
        final static String one =  "$10,000 to $19,999";
        final static String two =  "$20,000 to $29,999";
        final static String three =  "$30,000 to $39,999";
        final static String four =  "$40,000 to $49,999";
        final static String five =  "$50,000 to $59,999";
        final static String six =  "$60,000 to $79,999";
        final static String eight =  "$80,000 to $99,999";
        final static String ten =  "$100,000 to $119,999";
        final static String twelve =  "$120,000 or more";
    }
    final static class People{
        final static String a =  "Average";
        final static String one =  "1 person";
        final static String two =  "2 person";
        final static String three =  "3 person";
        final static String four =  "4 person";
        final static String five =  "5 person or more";
    }

    @Category(FastTest.class)
    @Test
    public void calcNormal(){
        testInput("75093", Income.six, People.three);
    }
    @Category(FastTest.class)
    @Test
    public void calcNoZip(){
        testInput("", Income.u, People.three);
    }
    @Category(FastTest.class)
    @Test
    public void calcNoIncome(){
        testInput("75093", Income.a, People.two);
    }
    @Category(FastTest.class)
    @Test
    public void calcNoPeople(){
        testInput("75093", Income.five, People.a);
    }
    @Category(FastTest.class)
    @Test
    public void calcLetters(){
        testInput("GEC", Income.two, People.one);
    }

    private void testInput(String zip, String income, String people){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.coolClimate)).check(matches(isDisplayed()));
        onView(withId(R.id.editText2)).perform(typeText(zip));
        onView(withId(R.id.incomeSpin)).perform(click());
        onView(allOf(withText(income))).perform(click());
        onView(withId(R.id.sizeSpin)).perform(click());
        onView(allOf(withText(people))).perform(click());
        onView(withId(R.id.submit)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.textView7)).check(matches(not(withText("Output from API"))));
    }

    static Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> rActives = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity activity: rActives){
                    currentActivity = activity;
                    break;
                }
            }
        });
        return currentActivity;
    }
}
