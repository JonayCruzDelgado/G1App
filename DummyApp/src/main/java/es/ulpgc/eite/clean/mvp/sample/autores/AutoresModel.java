package es.ulpgc.eite.clean.mvp.sample.autores;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.autor.Autor;
import es.ulpgc.eite.clean.mvp.sample.autores.Autores;


public class AutoresModel extends GenericModel<Autores.ModelToPresenter>
        implements Autores.PresenterToModel {
  private autores arquitectura, pintura, escultura;
  private String autoresText;
  private String autoresLabel;
  private int numOfTimes;
  private String msgText;
//crear objeto autores con dos atributos, uno tipo especialidad (esculturua, pintura...) y otro de array de string con los nombre de los autores

  private class autores{
    private String especialidad;
    private String[] nombre;


    public String getEspecialidad() {
      return especialidad;
    }

    public void setEspecialidad(String especialidad) {
      this.especialidad = especialidad;
    }

    public String[] getNombre() {
      return nombre;
    }

    public void setNombre(String[] nombre) {
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
  public void onCreate(Autores.ModelToPresenter presenter) {
    super.onCreate(presenter);

    escultura = new autores();
    arquitectura= new autores();
    pintura= new autores();

    //emulacion de la BD
    arquitectura.especialidad= "arquitectura";
    arquitectura.nombre = new String[]{"Le Corbusier", "Ludwig Mies van der Rohe", "Alexandre Gustave Eiffel", "Santiago Calatrava", "Antoni Gaudi"};
    pintura.especialidad="pintura";
    pintura.nombre= new String[]{"Leonardo da Vinci", "Salvador Dali", "Caravaggio", "Vincent van Gogh", "Francisco de Goya"};
    escultura.especialidad="escultura";
    escultura.nombre = new String[] {"Miguel Angel", "Miron", "Gian Lorenzo Bernini", "Auguste Rodin", "Donatello"};


    autoresLabel = "Click Me!";
    autoresText = "Hello World!";
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
    msgText = autoresText;
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
    return autoresLabel;
  }
}