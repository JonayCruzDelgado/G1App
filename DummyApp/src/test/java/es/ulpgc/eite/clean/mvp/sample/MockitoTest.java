package es.ulpgc.eite.clean.mvp.sample;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MockitoTest {




  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();



  @Test
  public void testMockModeloInicial()  {

   Inicial.PresenterToModel modeloInicial = mock(Inicial.PresenterToModel.class);

    when(modeloInicial.getTextBtn1()).thenReturn("Pintura");
    when(modeloInicial.getTextBtn2()).thenReturn("Arquitectura");
    when(modeloInicial.getTextBtn3()).thenReturn("Escultura");

    // use mock in test....
    assertEquals(modeloInicial.getTextBtn1(), "Pintura");
    assertEquals(modeloInicial.getTextBtn2(), "Arquitectura");
    assertEquals(modeloInicial.getTextBtn3(), "Escultura");

  }

}