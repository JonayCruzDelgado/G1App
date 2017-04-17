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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InicialViewTest {

    @Rule
    public ActivityTestRule<InicialView> mActivityTestRule = new ActivityTestRule<>(InicialView.class);

    @Test
    public void inicialViewTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.textoBtnPintura), withText("Pintura"),
                        childAtPosition(
                                allOf(withId(R.id.btnPintura),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Pintura")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textoBtnArquitectura), withText("Arquitectura"),
                        childAtPosition(
                                allOf(withId(R.id.btnArquitectura),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Arquitectura")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textoBtnEscultura), withText("Escultura"),
                        childAtPosition(
                                allOf(withId(R.id.btnEscultura),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Escultura")));


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
