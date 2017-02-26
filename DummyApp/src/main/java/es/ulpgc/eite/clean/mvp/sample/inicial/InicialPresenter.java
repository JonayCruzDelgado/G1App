package es.ulpgc.eite.clean.mvp.sample.inicial;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialModel;

public class InicialPresenter extends GenericPresenter
    <Inicial.PresenterToView, Inicial.PresenterToModel, Inicial.ModelToPresenter, InicialModel>
    implements Inicial.ViewToPresenter, Inicial.ModelToPresenter, Inicial.InicialTo, Inicial.ToInicial {


  private boolean toolbarVisible;


  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Inicial.PresenterToView view) {
    super.onCreate(InicialModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingInicialScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingInicialScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Inicial.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    if(configurationChangeOccurred()) {
      cargarEtiquetasPantalla();
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
  public void onButtonClickedArquitectura() {
    Log.d(TAG, "calling onButtonClickedArquitectura()");

  }
  @Override
  public void onButtonClickedPintura() {
    Log.d(TAG, "calling onButtonClickedPintura()");
  }

  @Override
  public void onButtonClickedEscultura() {
    Log.d(TAG, "calling onButtonClickedEscultura()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Inicial //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      cargarEtiquetasPantalla();
    }
    checkToolbarVisibility();
  }

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }



  ///////////////////////////////////////////////////////////////////////////////////
  // Inicial To //////////////////////////////////////////////////////////////////////


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



  ///////////////////////////////////////////////////////////////////////////////////

  private void checkToolbarVisibility(){
    Log.d(TAG, "calling checkToolbarVisibility()");
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }
  private void cargarEtiquetasPantalla(){
    getView().setLabelPintura(getModel().getTextBtn1());
    getView().setLabelArquitectura(getModel().getTextBtn2());
    getView().setLabelEscultura(getModel().getTextBtn3());
    getView().setIconoPintura(getModel().getIconoPintura());
    getView().setIconoArquitectura(getModel().getIconoArquitectura());
    getView().setIconoEscultura(getModel().getIconoEscultura());
  }



}
