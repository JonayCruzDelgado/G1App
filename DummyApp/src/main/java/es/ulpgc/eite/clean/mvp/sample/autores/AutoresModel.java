package es.ulpgc.eite.clean.mvp.sample.autores;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.I_ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;



public class AutoresModel extends GenericModel<Autores.ModelToPresenter>
        implements Autores.PresenterToModel {

  I_ManejadorBaseDeDatos manejador;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Autores.ModelToPresenter presenter) {
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
  public String [] getAutores(int idCategoriaSeleccionada){
    int[] ids =manejador.getListaIdAutores(idCategoriaSeleccionada);
    return manejador.getNombresByArrayIdsAutores(ids);

  }
  @Override
  public String getNombreCategoria(int idCategoriaSeleccionada){
   return manejador.getNombreCategoria(idCategoriaSeleccionada);
  }
  /*  recibe por parametros el id de la categoria y la posicion que se ha pulsado en la lista ,
* con esto se devuelve el id que tiene el autor en la base de datos*/
  @Override
  public int getIdAutorSelecionado(int idCategoriaSeleccionada, int pos){
    int[] ids = manejador.getListaIdAutores(idCategoriaSeleccionada);
    return ids[pos];
  }

}
