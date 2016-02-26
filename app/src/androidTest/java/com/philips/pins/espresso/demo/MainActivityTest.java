package com.philips.pins.espresso.demo;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by 310231492 on 2016/2/26.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testTextViewDisplay() {
        onView(withText(mActivityRule.getActivity().getString(R.string.hello_world))).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.changetxt)).perform(click());
        onView(withId(R.id.tv)).check(ViewAssertions.matches(withText(mActivityRule.getActivity().getString(R.string.changed_txt))));
    }

}
