package es.ulpgc.eite.clean.mvp.sample.addAutor;

import android.content.Context;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface AddAutor {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToAddAutor {
    void onScreenStarted();

  }

  interface AddAutorTo {
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

      void onButtonDoneClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    String getNombre();
    String getDescripcion();

    void setTitle(String txt);

      void showToast(String txt);

    void setImagen(Bitmap imagen);

    void showImagen();

    void hideImagen();
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    void  addAutorSinImagen(String nombre, String descripcion, int idCategoria);

      void  addAutorConImagen(String nombre, String descripcion, int idCategoria, String PathImagen);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
