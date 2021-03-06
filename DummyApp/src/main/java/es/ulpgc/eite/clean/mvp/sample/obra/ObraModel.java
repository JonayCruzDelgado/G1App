package es.ulpgc.eite.clean.mvp.sample.obra;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.I_ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;



public class ObraModel extends GenericModel<Obra.ModelToPresenter>
    implements Obra.PresenterToModel {

  I_ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Obra.ModelToPresenter presenter) {
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
  public String getNombre(int id){
    return manejador.getNombreObra(id);
  }
  @Override
  public String getDescripcion(int id){
    return  manejador.getDescripcionObra(id);
  }
  @Override
  public double getLatitud(int id){
    return  manejador.getLatitud(id);
  }
  @Override
  public double getLongitud(int id){
    return  manejador.getLongitud(id);
  }
  @Override
  public String getImagen(int id){
    return  manejador.getImagenObra(id);
  }
  @Override
  public Boolean getInitial(int id){
    return  manejador.isInAssetsObra(id);
  }
}
