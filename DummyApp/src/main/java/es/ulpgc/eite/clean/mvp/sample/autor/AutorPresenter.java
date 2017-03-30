package es.ulpgc.eite.clean.mvp.sample.autor;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Navigator;

public class AutorPresenter extends GenericPresenter
    <Autor.PresenterToView, Autor.PresenterToModel, Autor.ModelToPresenter, AutorModel>
    implements Autor.ViewToPresenter, Autor.ModelToPresenter, Autor.AutorTo, Autor.ToAutor {


  private boolean toolbarVisible;
  private boolean textVisible;
  private String categoria;
  private int PosicionListaObrasPulsada;
  private int idListaObrasPulsada;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Autor.PresenterToView view) {
    super.onCreate(AutorModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Mediator app = (Mediator) getView().getApplication();
    Log.d(TAG, "categoria seleccionada "+ app.getCategoriaClicked());
    categoria = app.getCategoriaClicked();

  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Autor.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");


    if(configurationChangeOccurred()) {

      checkToolbarVisibility();

      }
    }


  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void onItemClickSelected(int pos){
    Log.d(TAG,"posicion pulsada" + pos);
    setPosicionListaObrasPulsada(pos);
    Navigator app = (Navigator) getView().getApplication();
    app.goToObraScreen(this);

  }
  @Override
  public void inicializarVista(){
    Mediator app = (Mediator) getView().getApplication();
    int id= app.getIdAutorSelecionado();
    getView().setDescripcionAutor(getModel().getDescripcion(id));
    getView().setIconoAutor(getModel().getImagen(getManagedContext(),id));
    getView().setNombreAutor(getModel().getNombre(id));
    getView().actualizarListaObras(getModel().getObras(getModel().getNombre(id)));
    //String[] obras=getModel().getObras(app.getAutorSelecionado());
     // getView().actualizarListaObras(obras);


  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Autor //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {

    }
    checkToolbarVisibility();
    checkTextVisibility();
  }

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setTextVisibility(boolean visible) {
    textVisible = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Autor To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  @Override
  public boolean isTextVisible() {
    return textVisible;
  }


  ///////////////////////////////////////////////////////////////////////////////////

  private void checkToolbarVisibility(){
    Log.d(TAG, "calling checkToolbarVisibility()");
    /*if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }*/
  }

  private void checkTextVisibility(){
    Log.d(TAG, "calling checkTextVisibility()");
    if(isViewRunning()) {
      if(!textVisible) {
        getView().hideText();
      } else {
        getView().showText();
      }
    }
  }

  @Override
  public String getCategoria() {
    return categoria;
  }
  public void setPosicionListaObrasPulsada(int posicionListaObrasPulsada) {
    PosicionListaObrasPulsada = posicionListaObrasPulsada;
  }

  public void setIdObrasPulsada(int posicionListaObrasPulsada) {
    Mediator app = (Mediator) getView().getApplication();
    int id= app.getIdAutorSelecionado();
    idListaObrasPulsada = getModel().idObraPulsada(getModel().getNombre(id),posicionListaObrasPulsada);
  }
}
