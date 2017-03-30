package es.ulpgc.eite.clean.mvp.sample.autores;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;


public class AutoresModel extends GenericModel<Autores.ModelToPresenter>
        implements Autores.PresenterToModel {

  //ManejadorBaseDeDatosSim manejadorSim;

  ManejadorBaseDeDatos manejador;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Autores.ModelToPresenter presenter) {
    super.onCreate(presenter);

    //manejadorSim = ManejadorBaseDeDatosSim.getInstance();
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
  public String [] obtenerAutores(String categoriaSeleccionada){
    /*int[] ids = manejadorSim.arrayIdsAutorByCategoria(categoriaSeleccionada);
    return manejadorSim.arrayNombresByIdsAutores(ids);*/
    int[] ids =manejador.getListaIdAutores(categoriaSeleccionada);
    return manejador.getNombresByArrayIdsAutores(ids);

  }
  @Override
  public String obtenerCategoria(String categoriaSeleccionada){
   return categoriaSeleccionada;
  }
  @Override
  public int obtenerIdAutorSelecionado(String categoriaSeleccionada, int pos){
   /* int[] ids = manejadorSim.arrayIdsAutorByCategoria(categoriaSeleccionada);
    return ids[pos];*/
    int[] ids = manejador.getListaIdAutores(categoriaSeleccionada);
    return ids[pos];
  }
  @Override
  public String obtenerNombreAutorSelecionado(String categoriaSeleccionada,int pos){
   /* int[] ids = manejadorSim.arrayIdsAutorByCategoria(categoriaSeleccionada);
    return ids[pos];*/
    int[] ids = manejador.getListaIdAutores(categoriaSeleccionada);
    return manejador.getNombreAutor(ids[pos]);
  }
}
