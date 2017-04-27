package es.ulpgc.eite.clean.mvp.sample.addObra;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObra;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;


public class AddObraModel extends GenericModel<AddObra.ModelToPresenter>
    implements AddObra.PresenterToModel {

  ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(AddObra.ModelToPresenter presenter) {
    super.onCreate(presenter);

    manejador = ManejadorBaseDeDatos.getInstance();;
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
  public void  addObraSinImagen(String nombre,String descripcion,int idAutor,Double latitud,Double longitud){
    String imgDefault ="ic_cuadro.jpg";
    manejador.addObra(nombre,descripcion,idAutor,latitud,longitud,imgDefault,false);

  }
  @Override
  public void  addObraConImagen(String nombre, String descripcion, int idAutor, Double latitud, Double longitud, String PathImagen){
    manejador.addObra(nombre,descripcion,idAutor,latitud,longitud,PathImagen,false);

  }
}
