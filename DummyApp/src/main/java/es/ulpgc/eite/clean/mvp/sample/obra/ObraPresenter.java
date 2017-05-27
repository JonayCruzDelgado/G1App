package es.ulpgc.eite.clean.mvp.sample.obra;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.obra.Obra;
import es.ulpgc.eite.clean.mvp.sample.obra.ObraModel;

public class ObraPresenter extends GenericPresenter
    <Obra.PresenterToView, Obra.PresenterToModel, Obra.ModelToPresenter, ObraModel>
    implements Obra.ViewToPresenter, Obra.ModelToPresenter, Obra.ObraTo, Obra.ToObra {


  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Obra.PresenterToView view) {
    super.onCreate(ObraModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Obra.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    inicializarVista();

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
    Mediator app=(Mediator) getView().getApplication();
    int id=app.getIdObraSelecionada();
    double latitude =getModel().getLatitud(id);
    double longitude = getModel().getLongitud(id);
    String nombreObra = getModel().getNombre(id);

    //mostrar chincheta en google maps : geo:0,0?q=latitude,longitude(label)
    String uri = String.format(Locale.ENGLISH, ("geo:0,0?q=%f,%f(%s)"), latitude, longitude,nombreObra);

    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    //linea de comando para evitar que la aplicacion crashee si no tiene instalado el google maps
    if (intent.resolveActivity(getManagedContext().getPackageManager()) != null) {
      getManagedContext().startActivity(intent);
    }else{
      getView().showToast("Google Maps necesario");
    }

  }
/*
pide al modelo la informacion del Idobra que se recupera del mediador y lo introduce en AutorView
*/

  @Override
  public void inicializarVista() {
    Mediator app=(Mediator) getView().getApplication();

    int id=app.getIdObraSelecionada();

      getView().setDescripcionObra(getModel().getDescripcion(id));
      getView().setNombreObra(getModel().getNombre(id));
      inicializarImagen(getModel().getInitial(id),id);
  }
  /*se pasa por parametros el id del autor, e inicial que determinara si el autor esta inicializado por
  la aplicacion o lo añadio el usuario. Esto ultimo es necesario porque es diferente la forma de cargar
  la imagen. El parametr inicio esta guardado en la base de datos*/
  private void inicializarImagen(Boolean inicial, int id){
    String imagen =getModel().getImagen(id);
    if (inicial){

      AssetManager am = getView().getActivityContext().getAssets();
      InputStream is = null;
      try{

        is = am.open(imagen);
      }catch(IOException e){
        e.printStackTrace();
      }

      Bitmap bitmapAssets = BitmapFactory.decodeStream(is);
      getView().setImagenObra(bitmapAssets);
    }else{

      File imgFile = new  File(imagen);
      Bitmap bitmapUsuario = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
      getView().setImagenObra(bitmapUsuario);
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Obra //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      inicializarVista();
    }

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Obra To //////////////////////////////////////////////////////////////////////


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

}
