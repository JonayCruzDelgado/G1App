package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

public interface Navigator {
  void goToNextScreen(Dummy.DummyTo presenter);

  void goToAutoresScreen(Inicial.InicialTo presenter);
}
