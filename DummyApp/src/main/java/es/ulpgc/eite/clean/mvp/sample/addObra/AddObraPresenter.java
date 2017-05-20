package es.ulpgc.eite.clean.mvp.sample.addObra;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class AddObraPresenter extends GenericPresenter
    <AddObra.PresenterToView, AddObra.PresenterToModel, AddObra.ModelToPresenter, AddObraModel>
    implements AddObra.ViewToPresenter, AddObra.ModelToPresenter, AddObra.AddObraTo, AddObra.ToAddObra {

  private String imagenPath;
  private static MyObserver observer;
  private Uri uri;


  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(AddObra.PresenterToView view) {
    super.onCreate(AddObraModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingAddObraScreen()");
    Mediator app = (Mediator) getView().getApplication();
    app.startingAddObraScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(AddObra.PresenterToView view) {
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
  public void onButtonAddImagenClicked(){
    observer = new MyObserver();
    Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    );
    getView().startGaleria(intent);

  }

  class MyObserver implements java.util.Observer{

    @Override
    public void update(Observable o, Object arg) {
      Log.d(TAG,"update observer");
      uri =(Uri)arg;
      if(uri != null){
        imagenPath = getRealPathFromURI(uri);
        if(imagenPath != null) {
          setImagenSelecionada();
          setImagenView(imagenPath);
          getView().showImagen();
        }
      }
    }
  }

  @Override
  public MyObserver getObserver(){
    return observer;

  }



  @Override
  public void onButtonDoneClicked() {
    Mediator app = (Mediator) getView().getApplication();
    String nombre= getView().getNombre();
    String descripcion= getView().getDescripcion();
    String path = getImagenSelecionada();
    String textoLatitud = getView().getLatitud();
    Double latitud;
          if(textoLatitud == null || textoLatitud.isEmpty()) {
            latitud = 0.0;
          } else {
            latitud = Double.parseDouble(textoLatitud);
          }
    String textoLongitud= getView().getLongitud();
          Double longitud;
          if(textoLongitud == null || textoLongitud.isEmpty()) {
            longitud = 0.0;
          } else {
            longitud = Double.parseDouble(textoLongitud);
          }

    if((!nombre.equals(""))&&(!descripcion.equals(""))&&
            (!textoLatitud.equals(""))&&(!textoLongitud.equals(""))){
            if (path.equals("ic_cuadro.jpg")){
              getModel().addObraSinImagen(nombre, descripcion, app.getIdAutorSelecionado(), latitud, longitud);
            }
            else {
              getModel().addObraConImagen(nombre, descripcion, app.getIdAutorSelecionado(),
                      latitud, longitud, path);
            }
            getView().finishScreen();
    }else {
      getView().showToast("Introducir Datos Validos");
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To AddObra //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
        inicializarVista();
    }

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // AddObra To //////////////////////////////////////////////////////////////////////


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
  public void setImagenSelecionada(){
    Mediator app = (Mediator) getView().getApplication();
    String imagen = imagenPath;
    app.setImagenObra(imagen);

  }



  ///////////////////////////////////////////////////////////////////////////////////

  private void inicializarVista(){
    getView().setTitle("Nueva Obra");
    if(getImagenSelecionada().equals("ic_cuadro.jpg")){  // imagen por defecto si no se seleciona ninguna
      getView().hideImagen();
    }else{
      getView().showImagen();
      setImagenView(getImagenSelecionada());
    }
  }

  private String getImagenSelecionada(){
    Mediator app = (Mediator) getView().getApplication();
    return app.getImagenObra();
  }

  private void setImagenView(String imagen){
    if( imagen != null) {
      File imgFile = new File(imagen);
      if (imgFile.exists()) {
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        getView().setImagen(myBitmap);
      }
    }
  }


  public String getRealPathFromURI(Uri contentUri) {

    String res = null;
    String[] proj = { MediaStore.Images.Media.DATA };
    Cursor cursor = getActivityContext().getContentResolver().query(contentUri, proj, null, null, null);
    if (cursor.moveToFirst()) {

      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      res = cursor.getString(column_index);
    }
    cursor.close();

    return res;
  }

}
