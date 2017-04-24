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
public class BasicTest {

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
    @Test
    public void testDisplayAutorMiguelAngel() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .perform(click());
        onView(allOf(withId(R.id.toolbar),withText("Miguel Ángel")));
        onView(allOf(withId(R.id.listaObras),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaObras))
                .atPosition(0)
                .check(matches(withText("El David")));
    }
    @Test
    public void testDisplayObraElDavid() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaObras))
                .atPosition(0)
                .perform(click());
        onView(allOf(withId(R.id.toolbar),withText("El David")));
    }
    @Test
    public void testDisplayAddAutor() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onView(withId(R.id.btnAddAutor)).perform(click());
        onView(allOf(withId(R.id.toolbar),withText("Nuevo Autor")));

    }
    @Test
    public void testDisplayAddObra() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.btnAddObra)).perform(click());
        onView(allOf(withId(R.id.toolbar),withText("Nueva Obra")));

    }

}