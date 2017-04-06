package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;


public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
    implements Inicial.PresenterToModel {

  ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Inicial.ModelToPresenter presenter) {
    super.onCreate(presenter);

    manejador = ManejadorBaseDeDatos.getInstance();

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
   return manejador.getNombreCategoria(1);
  }

  @Override
  public String getTextBtn2() {
    return manejador.getNombreCategoria(2);
  }

  @Override
  public String getTextBtn3() {
    return manejador.getNombreCategoria(3);
  }

  @Override
  public Bitmap getIconoBtn1(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagenCategoria(1));
    return icon;
  }

  @Override
  public Bitmap getIconoBtn2(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagenCategoria(2));
    return icon;
  }

  @Override
  public Bitmap getIconoBtn3(Context context) {
      Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getIdImagenCategoria(3));
      return icon;
  }
}
