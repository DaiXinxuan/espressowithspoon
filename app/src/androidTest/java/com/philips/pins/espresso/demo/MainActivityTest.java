package com.philips.pins.espresso.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by 310231492 on 2016/2/26.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private Activity activity;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mActivityRule .launchActivity(intent);
        activity = mActivityRule.getActivity();
    }

    /**
     * test username empty
     */
    @Test
    public void test1() {
        Spoon.screenshot(mActivityRule.getActivity(), "initial_state");
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText (activity.getString(R.string.empty_user)))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        Spoon.screenshot(mActivityRule.getActivity(), "after_test");
    }


    /**
     * test password empty
     */
    @Test
    public void test2() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985619"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_username");
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText(activity.getString(R.string.empty_pass))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        Spoon.screenshot(activity, "after_test");
    }

    /**
     * test user regist
     */
    @Test
    public void test3() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985619"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_data");
        onView(withId(R.id.regist)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Spoon.screenshot(activity, "after_test");
        onView(withText(activity.getString(R.string.regist_success))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * test existing user regist
     */
    @Test
    public void test4() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985619"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_data");
        onView(withId(R.id.regist)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Spoon.screenshot(activity, "after_test");
        onView(withText(activity.getString(R.string.user_resued))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * test existing user login
     */
    @Test
    public void test5() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985619"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_data");
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Spoon.screenshot(activity, "after_test");
        onView(withText(activity.getString(R.string.login_success))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * test nonexisten user login
     */
    @Test
    public void test6() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985618"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abc"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_data");
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Spoon.screenshot(activity, "after_test");
        onView(withText(activity.getString(R.string.nonexisten_user))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * test user login with wrong password
     */
    @Test
    public void test7() {
        Spoon.screenshot(activity, "initial_state");
        onView(withId(R.id.username)).perform(typeText("18781985619"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("abcd"), closeSoftKeyboard());
        Spoon.screenshot(activity, "input_data");
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Spoon.screenshot(activity, "after_test");
        onView(withText(activity.getString(R.string.login_fail))).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
