package es.ulpgc.eite.clean.mvp.sample.autor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;


public class AutorModel extends GenericModel<Autor.ModelToPresenter>
    implements Autor.PresenterToModel {

  ManejadorBaseDeDatosSim manejadorSim;
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
  public int idObraPulsada(String autor, int pos){
   /* int[] ids = manejadorSim.arrayIdsObraByAutor(autor);
    return  ids[pos];*/
    int[] ids = manejador.getListaIdObras(autor);
    return ids[pos];
  }

  @Override
  public int getIdAutor(String categoria,int pos){
    /*int[] ids=manejadorSim.arrayIdsAutorByCategoria(categoria);
    return ids[pos];*/
    int[] ids=manejador.getListaIdAutores(categoria);
    return ids[pos];
  }


  @Override
  public String[] getObras(String autor){
   /* int[] ids=manejadorSim.arrayIdsObraByAutor(autor);
    return manejadorSim.arrayNombresByIdsObras(ids);*/
    int[] ids = manejador.getListaIdObras(autor);
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
  public Bitmap getImagen(Context context, int id){
    /*Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejadorSim.idImagenAutor(id));
    return icon;*/
    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), manejador.getImagenAutor(id));
    return icon;
  }
}
