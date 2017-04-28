package es.ulpgc.eite.clean.mvp.sample;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialModel;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialView;

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
   InicialView vistaInicial =mock(InicialView.class) ;
// parametros que genera el modelo
    when(modeloInicial.getTextBtn1()).thenReturn("Pintura");
    when(modeloInicial.getTextBtn2()).thenReturn("Arquitectura");
    when(modeloInicial.getTextBtn3()).thenReturn("Escultura");
    when(modeloInicial.getIconoBtn1()).thenReturn("ImagenPintura");
    when(modeloInicial.getIconoBtn2()).thenReturn("ImagenArquitectura");
    when(modeloInicial.getIconoBtn3()).thenReturn("ImagenEscultura");

// parametros esperados en la vista
    doNothing().when(vistaInicial).setLabel1("Pintura");
    doNothing().when(vistaInicial).setLabel2("Arquitectura");
    doNothing().when(vistaInicial).setLabel3("Escultura");
    doNothing().when(vistaInicial).setIcono1("ImagenPintura");
    doNothing().when(vistaInicial).setIcono2("ImagenArquitectura");
    doNothing().when(vistaInicial).setIcono3("ImagenEscultura");


     vistaInicial.setLabel1(modeloInicial.getTextBtn1());



    assertEquals(modeloInicial.getTextBtn1(), "Pintura");
    assertEquals(modeloInicial.getTextBtn2(), "Arquitectura");
    assertEquals(modeloInicial.getTextBtn3(), "Escultura");

  }

}