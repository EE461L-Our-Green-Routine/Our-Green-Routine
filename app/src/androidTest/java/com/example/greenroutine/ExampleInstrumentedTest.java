package com.example.greenroutine;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.greenroutine.test.*;

import java.io.IOException;
import java.util.ArrayList;
import com.example.greenroutine.MaterialsParser;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws IOException, JSONException {
        // Context of the app under test.
        onView(withId(R.string.earth911));
        ArrayList mp = MaterialsParser.getDatabase(System.getenv("earth911"));
        System.out.println("hey!");

    }
}
