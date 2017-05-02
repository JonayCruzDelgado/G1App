package es.ulpgc.eite.clean.mvp.sample.inicial;



import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.I_ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;


public class InicialModel extends GenericModel<Inicial.ModelToPresenter>
    implements Inicial.PresenterToModel {

  I_ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Inicial.ModelToPresenter presenter) {
    super.onCreate(presenter);

    manejador = ManejadorBaseDeDatos.getInstance();

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
   return manejador.getNombreCategoria(1);
  }

  @Override
  public String getTextBtn2() {
    return manejador.getNombreCategoria(2);
  }

  @Override
  public String getTextBtn3() {
    return manejador.getNombreCategoria(3);
  }

  @Override
  public String getIconoBtn1() {
    return manejador.getImagenCategoria(1);
  }

  @Override
  public String getIconoBtn2() {
    return manejador.getImagenCategoria(2);
  }

  @Override
  public String getIconoBtn3() {
      return manejador.getImagenCategoria(3);
  }
}
