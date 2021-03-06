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

  }

  interface AutorTo {
    Context getManagedContext();
    void destroyView();
   int getPosicionListaObraSelecionada();
    int getIdObraSelecionada();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {


    void onItemClickSelected(int pos);
    void inicializarVista();
    void onButtonAddObraCliked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void setDescripcionAutor(String txt);
    void setImagenAutor(Bitmap imagen);
    void actualizarListaObras(String[] nombresObras);
    void setNombreAutor(String txt);

      void setPosicionLista(int pos);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {

    int getIdObraPulsada(int idAutor, int pos);
    String[] getObras(int idAutor);
    String getNombre(int posicion);
    String getDescripcion(int posicion);
    String getImagen(int id);
    Boolean getInitial(int id);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
