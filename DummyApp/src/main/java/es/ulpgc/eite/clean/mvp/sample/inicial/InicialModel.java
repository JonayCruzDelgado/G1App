package es.ulpgc.eite.clean.mvp.sample.inicial;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
    implements Inicial.PresenterToModel {

  private String inicialTextBtn1;
  private String inicialTextBtn2;
  private String inicialTextBtn3;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Inicial.ModelToPresenter presenter) {
    super.onCreate(presenter);

    inicialTextBtn1 = "Pintura";
    inicialTextBtn2 = "Arquitectura";
    inicialTextBtn3 = "Esculturas";

  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }



  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////

  @Override
  public String getTextBtn1() {
    return inicialTextBtn1;
  }

  @Override
  public String getTextBtn2() {
    return inicialTextBtn2;
  }

  @Override
  public String getTextBtn3() {
    return inicialTextBtn3;
  }


}
