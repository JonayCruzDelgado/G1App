package es.ulpgc.eite.clean.mvp.sample.obra;

import android.content.Context;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Obra {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToObra {
    void onScreenStarted();

  }

  interface ObraTo {
    Context getManagedContext();
    void destroyView();

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onButtonClicked();
    void inicializarVista();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();

    void setDescripcionObra(String txt);
    void setImagenObra(Bitmap imagen);
    void setNombreObra(String txt);
    void showToast(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
      ///////////////////////////////////////////////////////////////////////////////////
      // Presenter To Model ////////////////////////////////////////////////////////////

    String getNombre(int id);
    String getDescripcion(int id);
    double getLatitud(int id);
    double getLongitud(int id);
    String getImagen(int id);
    Boolean getInitial(int id);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
