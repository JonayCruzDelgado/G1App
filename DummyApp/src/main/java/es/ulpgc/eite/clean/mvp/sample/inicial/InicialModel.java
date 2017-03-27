package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;


public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
    implements Inicial.PresenterToModel {

  ManejadorBaseDeDatosSim manejadorSim;
  //ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Inicial.ModelToPresenter presenter) {
    super.onCreate(presenter);

    manejadorSim = ManejadorBaseDeDatosSim.getInstance();
    //manejador = ManejadorBaseDeDatos.getInstance();

  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////

  @Override
  public String getTextBtn1() {
    return manejadorSim.nombreCategoria(0);
   //return manejador.getNombreCategoria(1);
  }

  @Override
  public String getTextBtn2() {
    return manejadorSim.nombreCategoria(1);
    //return manejador.getNombreCategoria(2);
  }

  @Override
  public String getTextBtn3() {
    return manejadorSim.nombreCategoria(2);
    //return manejador.getNombreCategoria(3);
  }

  @Override
  public Bitmap getIconoPintura(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejadorSim.idImagenCategoria(0));
    //Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagen(1));
    return icon;
  }

  @Override
  public Bitmap getIconoArquitectura(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(),manejadorSim.idImagenCategoria(1));
    //Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagen(2));
    return icon;
  }

  @Override
  public Bitmap getIconoEscultura(Context context) {
      Bitmap icon = BitmapFactory.decodeResource(context.getResources(),manejadorSim.idImagenCategoria(2));
      //Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagen(3));
      return icon;
  }
}
