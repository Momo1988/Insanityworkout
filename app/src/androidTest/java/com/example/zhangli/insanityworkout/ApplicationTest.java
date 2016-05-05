package com.example.zhangli.insanityworkout;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.zhangli.insanityworkout.activity.MainActivity;
import com.example.zhangli.insanityworkout.model.ActivityCollector;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    public void testAddActivity() {
        assertEquals(0, ActivityCollector.activities.size());
        MainActivity loginActivity = new MainActivity();
        ActivityCollector.addActivity(loginActivity);
        assertEquals(1, ActivityCollector.activities.size());
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }



}