package com.example.greenroutine;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({FastTest.class})
@Suite.SuiteClasses({
        UITests.class,
        APITests.class
})
public class FastTests {
}
