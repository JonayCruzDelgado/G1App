package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Rule;

import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.dataBase.Autor;
import es.ulpgc.eite.clean.mvp.sample.dataBase.Categoria;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.Obra;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(AndroidJUnit4.class)
public class BasicInstrumentedTest {

  @Rule
  public ActivityTestRule<InicialView> mActivityRule = new ActivityTestRule<InicialView>(InicialView.class);
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void testRealmGetNombreCategoria(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Categoria> result= realm.where(Categoria.class).equalTo("id",1).findAll();
        String nombre = result.get(0).getCategoria();
        assertEquals(nombre,"Pintura");
  }

  @Test
  public void testRealmGetidsObraPorAutor(){
        Realm realm = Realm.getDefaultInstance();
        int idAutor=3;
        int[] ids={7,8,9};

      RealmResults<Obra> result =realm.where(Obra.class).equalTo("idAutor",idAutor).findAll();
      int[] array=new int[result.size()];
      int i;
      for(i=0; i<result.size();i++){
          array[i] =result.get(i).getId();
      }
      assertEquals(ids[0],array[0]);
      assertEquals(ids[1],array[1]);
      assertEquals(ids[2],array[2]);
  }

  @Test
  public void testRealmGetNombreObra(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",1).findAll();
        String nombre = result.get(0).getNombre();
        assertEquals(nombre,"La Última Cena");
  }

  @Test
  public void testMockInicialPresenter(){
      InicialPresenter presentador =mock(InicialPresenter.class);

      // parametros esperados esperados
      /*doNothing().when(presentador).
      doNothing().when(presentador).setLabel1("Pintura");
      doNothing().when(presentador).setLabel2("Arquitectura");
      doNothing().when(presentador).setLabel3("Escultura");*/

  }



  @Test
  public void testBtnsIncialDisplay() {
      onView(allOf(withId(R.id.btnPintura), isDisplayed()));
      onView(withId(R.id.textoBtnPintura)).check(matches(withText("Pintura")));

      onView(allOf(withId(R.id.btnArquitectura), isDisplayed()));
      onView(withId(R.id.textoBtnArquitectura)).check(matches(withText("Arquitectura")));

      onView(allOf(withId(R.id.btnEscultura), isDisplayed()));
      onView(withId(R.id.textoBtnEscultura)).check(matches(withText("Escultura")));
  }
    @Test
    public void testDisplaylistaAutoresEscultura() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))) //comprobar el titulo de Toolbar
                .check(matches(withText("Escultura")));
        onView(allOf(withId(R.id.listaAutores),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .check(matches(withText("Miguel Ángel")));
    }
    @Test
    public void testDisplaylistaAutoresArquitectura() {
        onView(withId(R.id.btnArquitectura)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Arquitectura")));
        onView(allOf(withId(R.id.listaAutores),isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .check(matches(withText("Le Corbusier")));
    }
    @Test
    public void testDisplaylistaAutoresPintura() {
        onView(withId(R.id.btnPintura)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Pintura")));
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
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Miguel Ángel")));
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
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("El David")));
    }
    @Test
    public void testDisplayAddAutor() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onView(withId(R.id.btnAddAutor)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Nuevo Autor")));

    }
    @Test
    public void testDisplayAddObra() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.btnAddObra)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("Nueva Obra")));

    }
    @Test
    public void testAddAutorSinImagen() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onView(withId(R.id.btnAddAutor)).perform(click());
        onView(withId(R.id.nombreAutorIntroducido)).perform(typeText("Autor Nuevo"));
        onView(withId(R.id.descripcionAutorIntroducida)).perform(typeText("Descripcion del Autor Nuevo"));
        onView(withId(R.id.btnDoneAutor)).perform(click());
        //contar los elementos de la lista
        final int[] counts = new int[1];
        onView(withId(R.id.listaAutores)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView listView = (ListView) view;
                counts[0] = listView.getCount();
                return true;
            }
            @Override
            public void describeTo(org.hamcrest.Description description) {
            }
        }));

        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(counts[0] -1)
                .check(matches(withText("Autor Nuevo")));
    }

    @Test
    public void testAddObraSinImagen() {
        onView(withId(R.id.btnEscultura)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listaAutores))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.btnAddObra)).perform(click());
        onView(withId(R.id.nombreObraIntroducido)).perform(typeText("Obra Nueva"));
        onView(withId(R.id.descripcionObraIntroducida)).perform(typeText("Descripcion de la Obra Nueva"));
        onView(withId(R.id.latitudIntroducida)).perform(typeText("20"));
        onView(withId(R.id.longitudIntroducida)).perform(typeText("20"));
        onView(withId(R.id.btnDoneObra)).perform(click());
        //contar los elementos de la lista
        final int[] counts = new int[1];
        onView(withId(R.id.listaObras)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView listView = (ListView) view;
                counts[0] = listView.getCount();
                return true;
            }
            @Override
            public void describeTo(org.hamcrest.Description description) {
            }
        }));

        onData(anything())
                .inAdapterView(withId(R.id.listaObras))
                .atPosition(counts[0] -1)
                .check(matches(withText("Obra Nueva")));
    }



}