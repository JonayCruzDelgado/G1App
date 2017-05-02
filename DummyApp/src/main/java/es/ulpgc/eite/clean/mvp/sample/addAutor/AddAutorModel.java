package es.ulpgc.eite.clean.mvp.sample.addAutor;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.I_ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;


public class AddAutorModel extends GenericModel<AddAutor.ModelToPresenter>
    implements AddAutor.PresenterToModel {

  I_ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(AddAutor.ModelToPresenter presenter) {
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
  public void  addAutorSinImagen(String nombre,String descripcion,int idCategoria){
    String imgDefault ="ic_cuadro.jpg";
    manejador.addAutor(nombre,descripcion,idCategoria,imgDefault,true);

  }

  @Override
  public void  addAutorConImagen(String nombre, String descripcion, int idCategoria, String PathImagen){
    manejador.addAutor(nombre,descripcion,idCategoria,PathImagen,false);

  }
}
