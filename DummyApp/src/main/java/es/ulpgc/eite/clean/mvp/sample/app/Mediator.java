package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

public interface Mediator {
  void startingDummyScreen(Dummy.ToDummy presenter);
  void startingInicialScreen(Inicial.ToInicial presenter);
}
