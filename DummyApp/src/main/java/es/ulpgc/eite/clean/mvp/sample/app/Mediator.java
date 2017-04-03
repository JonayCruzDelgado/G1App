package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

public interface Mediator {
  void startingDummyScreen(Dummy.ToDummy presenter);

  void startingAutoresScreen(Autores.ToAutores presenter);

  void startingInicialScreen(Inicial.ToInicial presenter);

  void startingAutorScreen(Inicial.ToInicial presenter);

  void startingObraScreen(Inicial.ToInicial presenter);

  String getCategoriaClicked();
  int getPosicionAutores();
  int getPosicionObras();
  String getNombreAutorSelecionado();
  String getNombreObraSelecionada();
}
