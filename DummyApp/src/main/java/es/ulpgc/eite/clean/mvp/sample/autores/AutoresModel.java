package es.ulpgc.eite.clean.mvp.sample.autores;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.dataBase.ManejadorBaseDeDatos;
import es.ulpgc.eite.clean.mvp.sample.dataBaseSim.ManejadorBaseDeDatosSim;


public class AutoresModel extends GenericModel<Autores.ModelToPresenter>
        implements Autores.PresenterToModel {
  private AutoresDB arquitectura, pintura, escultura;
  private String autoresText;
  private String autoresLabel;
  private int numOfTimes;
  private String msgText;
  ManejadorBaseDeDatosSim manejadorSim;
  ManejadorBaseDeDatos manejador;
//crear objeto AutoresDB con dos atributos, uno tipo especialidad (esculturua, pintura...) y otro de array de string con los nombre de los AutoresDB

  private class AutoresDB {
    private String especialidad;
    private String[] nombre;

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

    escultura = new AutoresDB();
    arquitectura= new AutoresDB();
    pintura= new AutoresDB();

    //emulacion de la BD
    arquitectura.especialidad= "Arquitectura";
    arquitectura.nombre = new String[]{"Le Corbusier", "Ludwig Mies van der Rohe", "Alexandre Gustave Eiffel", "Santiago Calatrava", "Antoni Gaudi"};
    pintura.especialidad="Pintura";
    pintura.nombre= new String[]{"Leonardo da Vinci", "Salvador Dali", "Caravaggio", "Vincent van Gogh", "Francisco de Goya"};
    escultura.especialidad="Escultura";
    escultura.nombre = new String[] {"Miguel Angel", "Miron", "Gian Lorenzo Bernini", "Auguste Rodin", "Donatello"};


    autoresLabel = "Click Me!";
    autoresText = "Hello World!";

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
  private Object listaSeleccionada(String generoSeleccionado){
    if (arquitectura.especialidad.equals(generoSeleccionado)){
      return  arquitectura;
    }else if (escultura.especialidad.equals(generoSeleccionado)){
      return escultura;
    }else if (pintura.especialidad.equals(generoSeleccionado)){
      return pintura;
    }
    return null;
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
  @Override
  public String [] obtenerAutores(String generoSeleccionado){
    //AutoresDB autoresNombres = (AutoresDB) listaSeleccionada(generoSeleccionado);
    //return autoresNombres.nombre;
    /*int[] ids = manejadorSim.arrayIdsAutorByCategoria(generoSeleccionado);
    return manejadorSim.arrayNombresByIdsAutores(ids);*/
    int[] ids =manejador.getListaIdAutores(generoSeleccionado);
    return manejador.getNombresByArrayIdsAutores(ids);

  }
  @Override
  public String obtenerEspecialidad(String generoSeleccionado){
    /*AutoresDB autoresNombres = (AutoresDB) listaSeleccionada(generoSeleccionado);
    return  autoresNombres.especialidad;*/
   return generoSeleccionado;
  }
}
