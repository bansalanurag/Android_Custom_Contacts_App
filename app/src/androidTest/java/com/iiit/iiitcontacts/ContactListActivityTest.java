package com.iiit.iiitcontacts;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ContactListActivityTest {

    @Rule
    public ActivityTestRule<ContactListActivity> mActivityTestRule = new ActivityTestRule<>(ContactListActivity.class);

    @Test
    public void contactListActivityTest() {
        ViewInteraction frameLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                0),
                        0),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.action_bar_root),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction frameLayout3 = onView(
                allOf(withId(android.R.id.content),
                        childAtPosition(
                                allOf(withId(R.id.action_bar_root),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        frameLayout3.check(matches(isDisplayed()));

        ViewInteraction viewGroup = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.content),
                                childAtPosition(
                                        withId(R.id.action_bar_root),
                                        0)),
                        0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.app_bar),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                allOf(withId(R.id.app_bar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("IIIT Contacts"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("IIIT Contacts")));

        ViewInteraction frameLayout4 = onView(
                allOf(withId(R.id.frameLayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        frameLayout4.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.contact_list),
                        childAtPosition(
                                allOf(withId(R.id.frameLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.content), withText("Aarti Goel ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Aarti Goel ABES")));

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        1),
                        isDisplayed()));
        linearLayout5.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        1),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.content), withText("Abhilash Chanchal AMS"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        1),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Abhilash Chanchal AMS")));

        ViewInteraction linearLayout6 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        2),
                        isDisplayed()));
        linearLayout6.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        2),
                                0),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.content), withText("Abhimanyu Kumar SRE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        2),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Abhimanyu Kumar SRE")));

        ViewInteraction linearLayout7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        3),
                        isDisplayed()));
        linearLayout7.check(matches(isDisplayed()));

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        3),
                                0),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.content), withText("Abhinav Pandey ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        3),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("Abhinav Pandey ABES")));

        ViewInteraction linearLayout8 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        4),
                        isDisplayed()));
        linearLayout8.check(matches(isDisplayed()));

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        4),
                                0),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.content), withText("Abhishek IT ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        4),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Abhishek IT ABES")));

        ViewInteraction linearLayout9 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        5),
                        isDisplayed()));
        linearLayout9.check(matches(isDisplayed()));

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        5),
                                0),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.content), withText("Abhishek Nagar ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        5),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Abhishek Nagar ABES")));

        ViewInteraction linearLayout10 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        6),
                        isDisplayed()));
        linearLayout10.check(matches(isDisplayed()));

        ViewInteraction imageView7 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        6),
                                0),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.content), withText("Aditi Jain ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        6),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Aditi Jain ABES")));

        ViewInteraction linearLayout11 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        7),
                        isDisplayed()));
        linearLayout11.check(matches(isDisplayed()));

        ViewInteraction imageView8 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        7),
                                0),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.content), withText("Agam Agarwal ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        7),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Agam Agarwal ABES")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.content), withText("Agam Agarwal ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        7),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("Agam Agarwal ABES")));

        ViewInteraction linearLayout12 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.contact_list),
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0)),
                        8),
                        isDisplayed()));
        linearLayout12.check(matches(isDisplayed()));

        ViewInteraction imageView9 = onView(
                allOf(withId(R.id.id_text), withContentDescription("Contact Photo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        8),
                                0),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.content), withText("Aishwarya Govil ABES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_list),
                                        8),
                                1),
                        isDisplayed()));
        textView11.check(matches(withText("Aishwarya Govil ABES")));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab1),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction view = onView(
                allOf(withId(android.R.id.statusBarBackground),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                1),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction view2 = onView(
                allOf(withId(android.R.id.navigationBarBackground),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                2),
                        isDisplayed()));
        view2.check(matches(isDisplayed()));

        ViewInteraction view3 = onView(
                allOf(withId(android.R.id.navigationBarBackground),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                2),
                        isDisplayed()));
        view3.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
