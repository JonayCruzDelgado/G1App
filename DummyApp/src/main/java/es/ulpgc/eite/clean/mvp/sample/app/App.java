package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.addAutor.AddAutorPresenter;
import es.ulpgc.eite.clean.mvp.sample.addAutor.AddAutorView;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraPresenter;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraView;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autor.AutorView;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.autores.AutoresView;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.obra.Obra;
import es.ulpgc.eite.clean.mvp.sample.obra.ObraView;


public class App extends Application implements Mediator, Navigator {


  private InicialState toInicialState, inicialToState;
  private AutoresState toAutoresState, autoresToState;
  private AutorState toAutorState, autorToState;
  private ObraState toObraState, obraToState;
  private AddObraState toAddObraState, addObraToState;
  private AddAutorState toAddAutorState, addAutorToState;

  @Override
  public void onCreate() {
    super.onCreate();
    toInicialState = new InicialState();
    toInicialState.toolbarVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Mediator //////////////////////////////////////////////////////////////////////

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
  public void startingAddAutorScreen(AddAutorPresenter presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void startingAddObraScreen(AddObraPresenter presenter) {
    presenter.onScreenStarted();
  }
  ///////////////////////////////////////////////////////////////////////////////////
  // Navigator /////////////////////////////////////////////////////////////////////


  @Override
  public void goToAutoresScreen(Inicial.InicialTo presenter) {
    Log.d("APP", "goToAutoresScreen() has pulsado el boton: "+ presenter.getIdBoton());
    inicialToState = new InicialState();
    inicialToState.idBoton= presenter.getIdBoton();

    autoresToState = new AutoresState();
    autoresToState.posicionListaAutoresPulsada = 0;
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, AutoresView.class));

    }

  }
  @Override
  public void goToAutorScreen(Autores.AutoresTo presenter) {
    Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaAutoresSelecionada());
    autoresToState.posicionListaAutoresPulsada = presenter.getPosicionListaAutoresSelecionada();
    autoresToState.idAutorSelecionado=presenter.getIdAutorSelecionado();
    Log.d("APP", "goToAutorScreen() id del autor: "+ autoresToState.idAutorSelecionado);

    autorToState = new AutorState();
    autorToState.posicionListaObrasSelecionada = 0;
    Context view = presenter.getManagedContext();


    if (view != null) {
      view.startActivity(new Intent(view, AutorView.class));

    }
  }

  @Override
  public void goToObraScreen(Autor.AutorTo presenter){
    Log.d("APP", "goToAutorScreen() has pulsado: "+ presenter.getPosicionListaObraSelecionada());

    autorToState.posicionListaObrasSelecionada = presenter.getPosicionListaObraSelecionada();
    autorToState.idObraSelecionada = presenter.getIdObraSelecionada();

    Log.d("APP", "goToObraScreen() con id obra: "+ autorToState.idObraSelecionada);
    Context view = presenter.getManagedContext();

    if (view != null) {
      view.startActivity(new Intent(view, ObraView.class));

    }

  }

  @Override
  public void goToAddAutorScreen(Autores.AutoresTo presenter){

    Log.d("APP", "goToAddAutorScreen() ");
    Context view = presenter.getManagedContext();
    addAutorToState = new AddAutorState();
    addAutorToState.imagenAutor="ic_escultura.png";
    if (view != null) {
      view.startActivity(new Intent(view, AddAutorView.class));
    }

  }

  @Override
  public void goToAddObraScreen(Autor.AutorTo presenter){

    Log.d("APP", "goToAddAutorScreen()");
    Context view = presenter.getManagedContext();
    addObraToState = new AddObraState();
    addObraToState.imagenObra="ic_cuadro.jpg";
    if (view != null) {
      view.startActivity(new Intent(view, AddObraView.class));


    }

  }
  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


  private class InicialState{
    boolean toolbarVisibility;
    int idBoton;
  }
  private class AutoresState{
    int posicionListaAutoresPulsada;
    int idAutorSelecionado;

  }
  private class AutorState{
    int posicionListaObrasSelecionada;
    int idObraSelecionada;

  }
  private class ObraState{

  }
  private class AddObraState{
    String imagenObra;

  }
  private class AddAutorState{
    String imagenAutor;

  }

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

  @Override
  public String getImagenAutor(){
    return addAutorToState.imagenAutor;
  }
  @Override
  public String getImagenObra(){
    return addObraToState.imagenObra;

  }
  @Override
  public void setImagenObra(String imagen){
    addObraToState.imagenObra =imagen;
  }

  @Override
  public void setImagenAutor(String imagen){
    addAutorToState.imagenAutor =imagen;
  }

}
