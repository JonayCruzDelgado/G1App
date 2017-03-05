package es.ulpgc.eite.clean.mvp.sample.autor;

import android.content.Context;
import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Autor {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToAutor {
    void onScreenStarted();
    void setToolbarVisibility(boolean visible);
    void setTextVisibility(boolean visible);
  }

  interface AutorTo {
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    boolean isTextVisible();
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
    void hideToolbar();
    void hideText();
    void showText();
    void setText(String txt);

    void setIconoAutor(Bitmap bp);

    void actualizarLista(String[] nombresObras);

    void setNombreAutor(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {

    void onChangeMsgByBtnClicked();
    String getText();
    String getLabel();

    String [] getObras(int posicion);

    String getNombre(int posicion);

    String getDescripcion(int posicion);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    String getCategoria();
  }
}
