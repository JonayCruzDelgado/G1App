package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;

public interface Navigator {
  void goToAutoresScreen(Inicial.InicialTo presenter);
  void goToAutorScreen(Autores.AutoresTo presenter);
  void goToObraScreen(Autor.AutorTo presenter);
  void goToAddAutorScreen(Autores.AutoresTo presenter);
  void goToAddObraScreen(Autor.AutorTo presenter);
}
