package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.addAutor.AddAutorPresenter;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraPresenter;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autor.AutorView;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.autores.AutoresView;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.obra.Obra;
import es.ulpgc.eite.clean.mvp.sample.obra.ObraView;


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
  public void startingAutorScreen(Autor.ToAutor presenter){

    presenter.onScreenStarted();
  }

  @Override
  public void startingObraScreen(Obra.ToObra presenter){

    presenter.onScreenStarted();
  }

  @Override
  public void startingAddAutorScreen(AddAutorPresenter addAutorPresenter) {

  }

  @Override
  public void startingAddObraScreen(AddObraPresenter addObraPresenter) {

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
    Log.d("APP", "goToAutoresScreen() has pulsado el boton: "+ presenter.getIdBoton());
    inicialToState = new InicialState();
    inicialToState.idBoton= presenter.getIdBoton();
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutoresView.class));

    }

  }
  @Override
  public void goToAutorScreen(Autores.AutoresTo presenter) {
    Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaAutoresSelecionada());
    autoresToState = new AutoresState();
    autoresToState.posicionListaAutoresPulsada = presenter.getPosicionListaAutoresSelecionada();
    autoresToState.idAutorSelecionado=presenter.getIdAutorSelecionado();
    Log.d("APP", "goToAutorScreen() id del autor: "+ autoresToState.idAutorSelecionado);
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutorView.class));

    }
  }

  @Override
  public void goToObraScreen(Autor.AutorTo presenter){
    Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaObraSelecionada());
    autorToState = new AutorState();
    autorToState.posicionListaObrasSelecionada = presenter.getPosicionListaObraSelecionada();
    autorToState.idObraSelecionada = presenter.getIdObraSelecionada();

    Log.d("APP", "goToObraScreen() con id obra: "+ autorToState.idObraSelecionada);
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, ObraView.class));

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
    //String layaoutClicked;
    int idBoton;
  }
  private class AutoresState{
    int posicionListaAutoresPulsada;
    int idAutorSelecionado;
    //String categoriaSeleccionada;
    //String nombreAutorSelecionado;

  }
  private class AutorState{
   // String nombreAutorSelecionado;
    int posicionListaObrasSelecionada;
    int idObraSelecionada;

  }
  private class ObraState{
   // String nombreObraSelecionada;

  }

 /* @Override
  public String getCategoriaClicked(){
    return inicialToState.layaoutClicked;
  }*/
 @Override
  public int getIdBotonCategoriaClicked(){
    return inicialToState.idBoton;
  }
  @Override
  public int getPosicionAutores(){
    return autoresToState.posicionListaAutoresPulsada;
  }
  @Override
  public int getIdAutorSelecionado(){
    return autoresToState.idAutorSelecionado;
  }

  @Override
  public int getPosicionObras(){
    return autorToState.posicionListaObrasSelecionada;
  }

  @Override
  public int getIdObraSelecionada(){
    return autorToState.idObraSelecionada;
  }


/*  @Override
  public String getNombreAutorSelecionado(){
    return autorToState.nombreAutorSelecionado;
  }*/
/*  public String getNombreObraSelecionada(){
    return obraToState.nombreObraSelecionada;
  }
*/

}
