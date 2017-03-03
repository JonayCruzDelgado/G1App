package es.ulpgc.eite.clean.mvp.sample.autor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;


public class AutorModel extends GenericModel<Autor.ModelToPresenter>
    implements Autor.PresenterToModel {

  private String autorText;
  private String autorLabel;
  private int numOfTimes;
  private String msgText;

  private ArrayList<AutorDB> arquitectura, escultura, pintura;

  public class AutorDB{
    private String descripcion;
    private String[] obras;
    private Bitmap fotoAutor;
    private String nombre;

    private AutorDB(String descripcion, String[] obras, String nombre) {
      this.descripcion = descripcion;
      this.obras = obras;
      this.nombre = nombre;

    }
  }


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Autor.ModelToPresenter presenter) {
    super.onCreate(presenter);

    autorLabel = "Click Me!";
    autorText = "Hello World!";

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
  public void onChangeMsgByBtnClicked() {
    msgText = autorText;
    if(numOfTimes > 0) {
      msgText += ", " + numOfTimes + " times";
    }
    numOfTimes++;
  }

  @Override
  public String getText() {
    return msgText;
  }

  @Override
  public String getLabel() {
    return autorLabel;
  }

  @Override
  public String[] getObras(int posicion){
    return null;
  }
  @Override
  public String getNombre(int posicion){
    return  null;
  }
  @Override
  public String getDescripcion(int posicion){
    return  null;
  }
}
