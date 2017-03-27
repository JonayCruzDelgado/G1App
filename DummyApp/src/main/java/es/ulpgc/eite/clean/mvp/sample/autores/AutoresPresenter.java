package es.ulpgc.eite.clean.mvp.sample.autores;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Navigator;

public class AutoresPresenter extends GenericPresenter
    <Autores.PresenterToView, Autores.PresenterToModel, Autores.ModelToPresenter, AutoresModel>
    implements Autores.ViewToPresenter, Autores.ModelToPresenter, Autores.AutoresTo, Autores.ToAutores {


  private boolean toolbarVisible;
  private String generoSelecionado;
  private int posicionListaAutoresPulsada;
  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Autores.PresenterToView view) {
    super.onCreate(AutoresModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingautoresScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingAutoresScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Autores.PresenterToView view) {
    setView(view);
  /*  Log.d(TAG, "calling onResume()");

    if(configurationChangeOccurred()) {
      getView().setLabel(getModel().getLabel());

      checkToolbarVisibility();
      checkTextVisibility();

      if (buttonClicked) {
        getView().setTituloToolbar(getModel().getText());
      }
    }*/
  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////


  @Override
  public void inicializarVista() {
      Mediator app = (Mediator) getApplication();
      getView().actualizarListaAutores(getModel().obtenerAutores(app.getLayaoutClicked()));
      getView().setTituloToolbar(getModel().obtenerEspecialidad(app.getLayaoutClicked()));
      getView().hideText();

  }

  @Override
  public void onItemClickSelected(int pos) {
    Log.d(TAG,"posicion pulsada" + pos);
    setPosicionListaAutoresPulsada(pos);
    Navigator app = (Navigator) getView().getApplication();
    app.goToAutorScreen(this);
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To autores //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
/*    if(isViewRunning()) {
      getView().setLabel(getModel().getLabel());
    }
    checkToolbarVisibility();
    checkTextVisibility();*/
  }

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // autores To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  ///////////////////////////////////////////////////////////////////////////////////

  private void checkToolbarVisibility(){
    Log.d(TAG, "calling checkToolbarVisibility()");
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

  public int getPosicionListaAutoresPulsada() {
    return posicionListaAutoresPulsada;
  }
  private void setPosicionListaAutoresPulsada(int posicionListaAutoresPulsada) {
    this.posicionListaAutoresPulsada = posicionListaAutoresPulsada;
  }
}
