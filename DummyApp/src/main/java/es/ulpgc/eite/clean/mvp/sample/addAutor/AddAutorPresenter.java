package es.ulpgc.eite.clean.mvp.sample.addAutor;


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

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;



public class AddAutorPresenter extends GenericPresenter
    <AddAutor.PresenterToView, AddAutor.PresenterToModel, AddAutor.ModelToPresenter, AddAutorModel>
    implements AddAutor.ViewToPresenter, AddAutor.ModelToPresenter, AddAutor.AddAutorTo, AddAutor.ToAddAutor {

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

  /*metodo que al que se llama por pulsar el boton addimagen
   que prepara el intent para abrir la galeria*/
  @Override
  public void onButtonAddImagenClicked(){
    observer = new MyObserver();
    Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    );
    getView().startGaleria(intent);

  }
  /*
  recupera la imagen del intent de onButtonAddImagenCliked a traves del observador.
  una ves recuperada la uri se pasa a formato path y se almacenan en el mediador y se cambia en la vista*/

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

  }/*
  se comprueban que los datos introducidos son validos, si no lo son se presenta un mensaje diciendo
  introducir datos validos. Si son validos se recuperan los datos y se almacen en la base de datos.*/
  @Override
  public void onButtonDoneClicked() {
    Mediator app = (Mediator) getView().getApplication();
    String nombre= getView().getNombre();
    String descripcion= getView().getDescripcion();
    String path = getImagenSelecionada();

    if((!nombre.equals(""))&&(!descripcion.equals(""))
            ){
      if (path.equals("ic_escultura.png")){
        getModel().addAutorSinImagen(nombre, descripcion, app.getIdBotonCategoriaClicked());
      }
      else {
        getModel().addAutorConImagen(nombre, descripcion, app.getIdBotonCategoriaClicked(),
                path);
      }
      getView().finishScreen();
    }else {
      getView().showToast("Introducir Datos Validos");
    }
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

  @Override
  public void setImagenSelecionada(){
    Mediator app = (Mediator) getView().getApplication();
    String imagen = imagenPath;
    app.setImagenAutor(imagen);

  }

  ///////////////////////////////////////////////////////////////////////////////////
/*se comprueba la imagen por defecto (ic_escultura.png), se oculta si coincide y se muestra si hay una imagen diferente*/
  public void inicializarVista(){
    getView().setTitle("Nuevo Autor");
    if(getImagenSelecionada().equals("ic_escultura.png")){
      getView().hideImagen();
    }else{
      getView().showImagen();
      setImagenView(getImagenSelecionada());
    }
  }

  private String getImagenSelecionada(){
    Mediator app = (Mediator) getView().getApplication();
    return app.getImagenAutor();

  }
  /*forma de pasar una path de una imagen a un Bitmap*/
  private void setImagenView(String imagen){
    if( imagen != null) {
      File imgFile = new File(imagen);
      if (imgFile.exists()) {
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        getView().setImagen(myBitmap);
      }
    }
  }
  /*forma de pasar una uri de una imagen a un path de la imagen*/
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
