package es.ulpgc.eite.clean.mvp.sample.addAutor;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class AddAutorPresenter extends GenericPresenter
    <AddAutor.PresenterToView, AddAutor.PresenterToModel, AddAutor.ModelToPresenter, AddAutorModel>
    implements AddAutor.ViewToPresenter, AddAutor.ModelToPresenter, AddAutor.AddAutorTo, AddAutor.ToAddAutor {



  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(AddAutor.PresenterToView view) {
    super.onCreate(AddAutorModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingAddAutorScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingAddAutorScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(AddAutor.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");
    inicializarVista();

    if(configurationChangeOccurred()) {
      inicializarVista();

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
  public void onButtonDoneClicked() {
    Log.d(TAG, "calling onButtonClicked()");
    Mediator mediador=(Mediator)getView().getApplication();
    int idCategoria=mediador.getIdBotonCategoriaClicked();
    String nombre=getView().getNombre();
    String descripcion =getView().getDescripcion();
      if((!nombre.equals("")) && (!descripcion.equals(""))){
          getModel().addAutorSinImagen(nombre, descripcion, idCategoria);
        getView().finishScreen();
      }
      getView().showToast("Introducir Datos Validos");



  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To AddAutor //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
        inicializarVista();
    }

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // AddAutor To //////////////////////////////////////////////////////////////////////


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

  ///////////////////////////////////////////////////////////////////////////////////

  public void inicializarVista(){
    getView().setTitle("Nuevo Autor");
  }



}
