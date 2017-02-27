package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.R;


public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
    implements Inicial.PresenterToModel {

  private String inicialTextBtn1;
  private String inicialTextBtn2;
  private String inicialTextBtn3;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Inicial.ModelToPresenter presenter) {
    super.onCreate(presenter);

    inicialTextBtn1 = "Pintura";
    inicialTextBtn2 = "Arquitectura";
    inicialTextBtn3 = "Escultura";

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
    return inicialTextBtn1;
  }

  @Override
  public String getTextBtn2() {
    return inicialTextBtn2;
  }

  @Override
  public String getTextBtn3() {
    return inicialTextBtn3;
  }

  @Override
  public Bitmap getIconoPintura(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_cuadro);
    return icon;
  }

  @Override
  public Bitmap getIconoArquitectura(Context context) {
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_arqui);

    return icon;
  }

  @Override
  public Bitmap getIconoEscultura(Context context) {
      Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_escultura);

      return icon;
  }
}
