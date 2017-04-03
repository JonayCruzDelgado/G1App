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
   int getPosicionListaObrasPulsada();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {


    void onItemClickSelected(int pos);

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
    void setDescripcionAutor(String txt);
    void setIconoAutor(Bitmap bp);
    void actualizarListaObras(String[] nombresObras);
    void setNombreAutor(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {


      ///////////////////////////////////////////////////////////////////////////////////
      // Presenter To Model ////////////////////////////////////////////////////////////
      int idObraPulsada(String autor, int pos);

      String nombreObraPulsada(String autor, int pos);

      int getIdAutor(String categoria, int pos);

      String[] getObras(String autor);
    String getNombre(int posicion);
    String getDescripcion(int posicion);
    Bitmap getImagen(Context context, int id);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    String getCategoria();
  }
}
