package es.ulpgc.eite.clean.mvp.sample;

import org.junit.Test;

import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

  @Test
  public void getnombreAutor() throws Exception {
   assertTrue(ManejadorBaseDeDatos.getInstance().getNombreAutor(1).equals("Miguel Ángel"));

  }

  @Test
  public void getNombresCategoria() throws Exception {
    String[] array={"Pintura","Escultura","Arquitectura"};
    assertTrue(ManejadorBaseDeDatosSim.getInstance().arrayNombresCategorias().equals(array));

  }

}