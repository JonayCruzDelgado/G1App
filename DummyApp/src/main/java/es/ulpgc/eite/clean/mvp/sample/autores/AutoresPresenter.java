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


  private int posicionListaAutoresSelecionada;
  private int idAutorSelecionado;
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
   Log.d(TAG, "calling onResume()");
    inicializarVista();
    if(configurationChangeOccurred()) {
      inicializarVista();

    }
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
      getView().actualizarListaAutores(getModel().getAutores(app.getIdBotonCategoriaClicked()));
      getView().setTituloToolbar(getModel().getNombreCategoria(app.getIdBotonCategoriaClicked()));
      getView().setPosicionLista(app.getPosicionAutores());

  }

  @Override
  public void onItemClickSelected(int pos) {
    Log.d(TAG,"posicion pulsada" + pos);
    Mediator mediator = (Mediator) getApplication();
    setPosicionListaAutoresSelecionada(pos);
    setIdAutorSelecionado(mediator.getIdBotonCategoriaClicked(),pos);
    Navigator app = (Navigator) getView().getApplication();
    app.goToAutorScreen(this);
  }
  @Override
  public void onButtonAddAutorCliked(){
      Navigator app = (Navigator) getView().getApplication();
      app.goToAddAutorScreen(this);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // To autores //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
   if(isViewRunning()) {
      inicializarVista();
    }
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

  ///////////////////////////////////////////////////////////////////////////////////

  @Override
  public int getPosicionListaAutoresSelecionada() {
    return posicionListaAutoresSelecionada;
  }
  @Override
  public int getIdAutorSelecionado() {
    return idAutorSelecionado;
  }

  private void setPosicionListaAutoresSelecionada(int posicionListaAutoresSelecionada) {
    this.posicionListaAutoresSelecionada = posicionListaAutoresSelecionada;
  }
  private void setIdAutorSelecionado(int idCategoria, int posicionListaAutoresSelecionada) {
    this.idAutorSelecionado = getModel().getIdAutorSelecionado(idCategoria,posicionListaAutoresSelecionada);
  }




}
