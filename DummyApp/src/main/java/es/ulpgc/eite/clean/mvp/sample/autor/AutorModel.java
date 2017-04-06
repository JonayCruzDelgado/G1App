package es.ulpgc.eite.clean.mvp.sample.autor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;


public class AutorModel extends GenericModel<Autor.ModelToPresenter>
    implements Autor.PresenterToModel {

  ManejadorBaseDeDatos manejador;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Autor.ModelToPresenter presenter) {
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
  public int getIdObraPulsada(int idAutor, int pos){
    int[] ids = manejador.getListaIdObras(idAutor);
    return ids[pos];
  }

  @Override
  public String[] getObras(int idAutor){
    int[] ids = manejador.getListaIdObras(idAutor);
    return manejador.getNombresByArrayIdsObras(ids);
  }
  @Override
  public String getNombre(int id){
    return  manejador.getNombreAutor(id);
  }
  @Override
  public String getDescripcion(int id){
    return  manejador.getDescripcionAutor(id);
  }
  @Override
  public String getImagen( int id){
    return manejador.getImagenAutor(id);
  }
}
