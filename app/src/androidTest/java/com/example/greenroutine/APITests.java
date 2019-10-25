package com.example.greenroutine;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class APITests {
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
}
