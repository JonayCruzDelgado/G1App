package es.ulpgc.eite.clean.mvp.sample.addAutor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraPresenter;

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
    void setImagenSelecionada();
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
    AddAutorPresenter.MyObserver getObserver();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {

    void finishScreen();
    void startGaleria(Intent intent);
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
    void  addAutorSinImagen(String nombre, String descripcion, int idAutor);
    void  addAutorConImagen(String nombre, String descripcion, int idAutor, String PathImagen);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
