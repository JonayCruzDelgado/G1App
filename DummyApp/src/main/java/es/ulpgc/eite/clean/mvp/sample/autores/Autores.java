package es.ulpgc.eite.clean.mvp.sample.autores;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Autores {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToAutores {
    void onScreenStarted();
    void setToolbarVisibility(boolean visible);

  }

  interface AutoresTo {
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();

    int getPosicionListaAutoresSelecionada();
    int getIdAutorSelecionado();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void inicializarVista();
    void onItemClickSelected(int pos);
    void onButtonAddAutorCliked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void hideText();
    void actualizarListaAutores(String[] nombresAutores);
    void showText();
    void setTituloToolbar(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {

    String [] getAutores(int idCategoriaSeleccionada);
    String getNombreCategoria(int idCategoriaSeleccionada);
    int getIdAutorSelecionado(int idCategoriaSeleccionada, int pos);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
