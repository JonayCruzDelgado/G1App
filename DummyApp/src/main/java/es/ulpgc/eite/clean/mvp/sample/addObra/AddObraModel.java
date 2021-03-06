package es.ulpgc.eite.clean.mvp.sample.addObra;



import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.I_ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;


public class AddObraModel extends GenericModel<AddObra.ModelToPresenter>
    implements AddObra.PresenterToModel {

  I_ManejadorBaseDeDatos manejador;

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

  /*añadir autor a la base de datos con imagen por defecto ic_cuadro.png
            */
  @Override
  public void  addObraSinImagen(String nombre,String descripcion,int idAutor,Double latitud,Double longitud){
    String imgDefault ="ic_cuadro.jpg";
    manejador.addObra(nombre,descripcion,idAutor,latitud,longitud,imgDefault,true);

  }
  /* añadir autor a la base de datos con la imagen pasada por parametros en el formato Path*/
  @Override
  public void  addObraConImagen(String nombre, String descripcion, int idAutor, Double latitud, Double longitud, String PathImagen){
    manejador.addObra(nombre,descripcion,idAutor,latitud,longitud,PathImagen,false);

  }
}
