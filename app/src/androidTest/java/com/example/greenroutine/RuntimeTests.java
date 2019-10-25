package com.example.greenroutine;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UITests.class,
        APITests.class
})

public class RuntimeTests {
}
