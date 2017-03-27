package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autor.AutorView;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.autores.AutoresView;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;


public class App extends Application implements Mediator, Navigator {

  private DummyState toDummyState, dummyToState;
  private InicialState toInicialState, inicialToState;
  private AutoresState toAutoresState, autoresToState;
  private AutorState toAutorState, autorToState;
  private ObraState toObrastate, obraToState;

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

  @Override
  public void startingAutorScreen(Inicial.ToInicial presenter){

    presenter.onScreenStarted();
  }

  @Override
  public void startingObraScreen(Inicial.ToInicial presenter){

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
    autoresToState.posicionListaAutoresPulsada = presenter.getPosicionListaAutoresPulsada();
    autoresToState.categoriaSeleccionada = inicialToState.layaoutClicked;
    Log.d("APP", "goToAutorScreen() categoria seleccionada: "+ autoresToState.categoriaSeleccionada);
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutorView.class));

    }
  }

  @Override
  public void goToObraScreen(Autor.AutorTo presenter){
    Log.d("APP", "goToAutorScreen() has pulsado: ");

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
    int posicionListaAutoresPulsada;
    String categoriaSeleccionada;
  }
  private class AutorState{
    String autorSelecionado;
    int posicionListaObrasPulsada;
  }
  private class ObraState{

  }

  @Override
  public String getLayaoutClicked(){
    return inicialToState.layaoutClicked;
  }
  @Override
  public int getPosicionAutores(){
    return autoresToState.posicionListaAutoresPulsada;
  }
  @Override
  public int getPosicionObras(){
    return autorToState.posicionListaObrasPulsada;
  }
  @Override
  public String getAutorSelecionado(){
    return autorToState.autorSelecionado;
  }
}
