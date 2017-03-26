package es.ulpgc.eite.clean.mvp.sample;

import org.junit.Test;

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
    ManejadorBaseDeDatosSim.getInstance().nombreAutor(0).equals("Miguel Ángel");

  }
}