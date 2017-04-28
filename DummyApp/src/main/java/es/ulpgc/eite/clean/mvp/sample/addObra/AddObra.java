package es.ulpgc.eite.clean.mvp.sample.addObra;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface AddObra {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToAddObra {
    void onScreenStarted();

  }

  interface AddObraTo {
    Context getManagedContext();
    void destroyView();

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {


    ///////////////////////////////////////////////////////////////////////////////////
    // View To Presenter /////////////////////////////////////////////////////////////
    void onButtonAddImagenClicked();
    void setImagen();

    void onButtonDoneClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    String getSelectedImagePath();

    void finishScreen();
    void hideToolbar();

    String getNombre();

    String getDescripcion();

    String getLatitud();

    String getLongitud();

      void setTitle(String txt);

    void showToast(String txt);

      void setImagen(String imagen);

    void showImagen();

    void hideImagen();
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {

    void  addObraSinImagen(String nombre, String descripcion, int idAutor, Double latitud, Double longitud);

    void  addObraConImagen(String nombre, String descripcion, int idAutor, Double latitud, Double longitud, String PathImagen);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
