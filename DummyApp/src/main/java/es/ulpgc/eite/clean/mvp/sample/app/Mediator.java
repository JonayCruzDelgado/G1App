package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.addAutor.AddAutorPresenter;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraPresenter;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.obra.Obra;

public interface Mediator {
  void startingDummyScreen(Dummy.ToDummy presenter);
  void startingAutoresScreen(Autores.ToAutores presenter);
  void startingInicialScreen(Inicial.ToInicial presenter);
  void startingAutorScreen(Autor.ToAutor presenter);
  void startingObraScreen(Obra.ToObra presenter);

  void startingAddAutorScreen(AddAutorPresenter addAutorPresenter);
  void startingAddObraScreen(AddObraPresenter addObraPresenter);

  int getIdBotonCategoriaClicked();
  int getPosicionAutores();
  int getIdAutorSelecionado();
  int getPosicionObras();
  int getIdObraSelecionada();


}
