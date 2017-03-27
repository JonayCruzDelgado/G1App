package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.autor.AutorView;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.autores.AutoresView;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;


public class App extends Application implements Mediator, Navigator {

  private DummyState toDummyState, dummyToState;
  private InicialState toInicialState, inicialToState;
  private AutoresState toAuotoresState, autoresToState;

  @Override
  public void onCreate() {
    super.onCreate();
    toDummyState = new DummyState();
    toDummyState.toolbarVisibility = false;
    toDummyState.textVisibility = false;
    toInicialState = new InicialState();
    toInicialState.textVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Mediator //////////////////////////////////////////////////////////////////////

  @Override
  public void startingDummyScreen(Dummy.ToDummy presenter){
    if(toDummyState != null) {
      presenter.setToolbarVisibility(toDummyState.toolbarVisibility);
      presenter.setTextVisibility(toDummyState.textVisibility);
    }
    presenter.onScreenStarted();
  }
  @Override
  public void startingAutoresScreen(Autores.ToAutores presenter){

    presenter.onScreenStarted();
  }


  @Override
  public void startingInicialScreen(Inicial.ToInicial presenter){

    presenter.onScreenStarted();
  }
  ///////////////////////////////////////////////////////////////////////////////////
  // Navigator /////////////////////////////////////////////////////////////////////


  @Override
  public void goToNextScreen(Dummy.DummyTo presenter) {
    dummyToState = new DummyState();
    dummyToState.toolbarVisibility = presenter.isToolbarVisible();
    dummyToState.textVisibility = presenter.isTextVisible();

    Context view = presenter.getManagedContext();
    if (view != null) {
      view.startActivity(new Intent(view, DummyView.class));
      presenter.destroyView();
    }

  }
  @Override
  public void goToAutoresScreen(Inicial.InicialTo presenter) {
    Log.d("APP", "goToAutoresScreen() has pulsado: "+ presenter.getLayoutClicked());
    inicialToState = new InicialState();
    inicialToState.layaoutClicked= presenter.getLayoutClicked();
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutoresView.class));

    }

  }
  @Override
  public void goToAutorScreen(Autores.AutoresTo presenter) {
    Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaAutoresPulsada());
    autoresToState = new AutoresState();
    autoresToState.posicionListaPulsada = presenter.getPosicionListaAutoresPulsada();
    autoresToState.categoriaSeleccionada = inicialToState.layaoutClicked;
    Log.d("APP", "goToAutorScreen() categoria seleccionada: "+ autoresToState.categoriaSeleccionada);
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutorView.class));

    }

  }
  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class DummyState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }
  private class InicialState{
    boolean textVisibility;
    String layaoutClicked;
  }
  private class AutoresState{
    int posicionListaPulsada;
    String categoriaSeleccionada;
  }
  private class AutorState{

  }
  private class ObraState{

  }

  @Override
  public String getLayaoutClicked(){
    return inicialToState.layaoutClicked;
  }
  @Override
  public int getPosicion(){
    return autoresToState.posicionListaPulsada;
  }
}
