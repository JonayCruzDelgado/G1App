package es.ulpgc.eite.clean.mvp.sample.addObra;

import android.content.Context;
import android.content.Intent;
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
    AddObraPresenter.MyObserver getObserver();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {

    void startGaleria(Intent intent);
    void finishScreen();
    String getNombre();
    String getDescripcion();
    String getLatitud();
    String getLongitud();
    void setTitle(String txt);
    void setImagen(Bitmap imagen);
    void showImagen();
    void hideImagen();
    void showToast(String txt);
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
