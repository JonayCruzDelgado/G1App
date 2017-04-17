package es.ulpgc.eite.clean.mvp.sample.inicial;


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

import es.ulpgc.eite.clean.mvp.sample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InicialViewTest2 {

    @Rule
    public ActivityTestRule<InicialView> mActivityTestRule = new ActivityTestRule<>(InicialView.class);

    @Test
    public void inicialViewTest2() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.btnArquitectura), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Le Corbusier"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Le Corbusier")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Ludwig Mies van der Rohe"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Ludwig Mies van der Rohe")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("Alexandre Gustave Eiffel"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Alexandre Gustave Eiffel")));

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("Santiago Calatrava"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText("Santiago Calatrava")));

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.text1), withText("Antoni Gaudí"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                4),
                        isDisplayed()));
        textView5.check(matches(withText("Antoni Gaudí")));

        ViewInteraction textView6 = onView(
                allOf(withId(android.R.id.text1), withText("Antoni Gaudí"),
                        childAtPosition(
                                allOf(withId(R.id.listaAutores),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                4),
                        isDisplayed()));
        textView6.check(matches(withText("Antoni Gaudí")));

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
