package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertEquals;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;

import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RealmTestInicial {

  @Rule
  public ActivityTestRule<InicialView> mActivityRule = new ActivityTestRule<InicialView>(InicialView.class);

  @Test
  public void testBtnsIncialDisplay() {
      onView(allOf(withId(R.id.btnPintura), isDisplayed()));
      onView(allOf(withId(R.id.textoBtnPintura), withText("Pintura")));

      onView(allOf(withId(R.id.btnArquitectura), isDisplayed()));
      onView(allOf(withId(R.id.textoBtnArquitectura), withText("Arquitectura")));

      onView(allOf(withId(R.id.btnEscultura), isDisplayed()));
      onView(allOf(withId(R.id.textoBtnEscultura), withText("Escultura")));
  }
    @Test
    public void testDisplaylistaAutoresEscultura() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onView(allOf(withId(R.id.listaAutores),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .check(matches(withText("Miguel Ángel")));
    }
    @Test
    public void testDisplaylistaAutoresArquitectura() {
        onView(withId(R.id.btnArquitectura)).perform(click());
        onView(allOf(withId(R.id.listaAutores),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .check(matches(withText("Le Corbusier")));
    }
    @Test
    public void testDisplaylistaAutoresPintura() {
        onView(withId(R.id.btnPintura)).perform(click());
        onView(allOf(withId(R.id.listaAutores),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .check(matches(withText("Leonardo Da Vinci")));

    }

}