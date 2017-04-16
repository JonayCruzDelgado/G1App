package es.ulpgc.eite.clean.mvp.sample.addAutor;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.Locale;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class AddAutorPresenter extends GenericPresenter
    <AddAutor.PresenterToView, AddAutor.PresenterToModel, AddAutor.ModelToPresenter, AddAutorModel>
    implements AddAutor.ViewToPresenter, AddAutor.ModelToPresenter, AddAutor.AddAutorTo, AddAutor.ToAddAutor {


  private boolean toolbarVisible;
  private boolean buttonClicked;
  private boolean textVisible;

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

    if(configurationChangeOccurred()) {
      getView().setLabel(getModel().getLabel());

      checkToolbarVisibility();
      checkTextVisibility();

      if (buttonClicked) {
        getView().setText(getModel().getText());
      }
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
  public void onButtonClicked() {
    Log.d(TAG, "calling onButtonClicked()");
    /*if(isViewRunning()) {
      getModel().onChangeMsgByBtnClicked();
      getView().setTituloToolbar(getModel().getText());
      textVisible = true;
      buttonClicked = true;
    }
    checkTextVisibility();*/

    double latitude =20;
    double longitude = -15;
    //tras la %f el ?z=0 indica el nivel de zoom  z establece el nivel de zoom inicial del mapa.
    // Los valores aceptados varían de 0 que muestra el planeta a 21 que muestra edificios separados
    // El límite superior puede variar según los datos del mapa disponibles en la ubicación seleccionada.
    //geo:latitude,longitude?z=zoom

   /* la q sirbe para para mostrar una marca en un lugar o una dirección en particular, como un punto de referencia, un negocio,
     una función geográfica o una ciudad. Con esto no funciona el parametro zoom*/
   //geo:0,0?q=latitude,longitude(label)
    String uri = String.format(Locale.ENGLISH, ("geo:0,0?q=%f,%f(obra)"), latitude, longitude);

    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    //linea de comando para evitar que la aplicacion crashee si no tiene instalado el google maps
    /*if (intent.resolveActivity(getPackageManager()) != null) {
      getManagedContext().startActivity(intent);
    }*/
    getManagedContext().startActivity(intent);


  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To AddAutor //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      getView().setLabel(getModel().getLabel());
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
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
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

}