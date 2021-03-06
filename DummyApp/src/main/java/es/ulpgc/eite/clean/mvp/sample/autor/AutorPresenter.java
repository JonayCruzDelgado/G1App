package es.ulpgc.eite.clean.mvp.sample.autor;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Navigator;

public class AutorPresenter extends GenericPresenter
    <Autor.PresenterToView, Autor.PresenterToModel, Autor.ModelToPresenter, AutorModel>
    implements Autor.ViewToPresenter, Autor.ModelToPresenter, Autor.AutorTo, Autor.ToAutor {

  private int posicionListaObraSelecionada;
  private int idObraSelecionada;


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
    app.startingAutorScreen(this);

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
/*
pide al modelo la informacion del Idautor que se recupera del mediador y lo introduce en AutorView
*/

  @Override
  public void inicializarVista(){
    Mediator app = (Mediator) getView().getApplication();
    int id= app.getIdAutorSelecionado();
    getView().setDescripcionAutor(getModel().getDescripcion(id));
    getView().setNombreAutor(getModel().getNombre(id));
    getView().actualizarListaObras(getModel().getObras(id));
    getView().setPosicionLista(app.getPosicionObras());

    inicializarImagen(getModel().getInitial(id),id);
  }/*
  se pasa por parametros el id del autor, e inicial que determinara si el autor esta inicializado por
  la aplicacion o lo añadio el usuario. Esto ultimo es necesario porque es diferente la forma de cargar
  la imagen. El parametr inicio esta guardado en la base de datos*/
  private void inicializarImagen(Boolean inicial, int id){
    String imagen =getModel().getImagen(id);
     // la imagen se obtiene desde assets
    if (inicial){

      AssetManager am = getView().getActivityContext().getAssets();
      InputStream is = null;
      try{

        is = am.open(imagen);
      }catch(IOException e){
        e.printStackTrace();
      }

      Bitmap bitmapAssets = BitmapFactory.decodeStream(is);
      getView().setImagenAutor(bitmapAssets);

      // no se obtiene de assets
    }else{

      File imgFile = new  File(imagen);
      Bitmap bitmapUsuario = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
      getView().setImagenAutor(bitmapUsuario);
    }
  }
  @Override
  public void onItemClickSelected(int pos) {
    Log.d(TAG,"posicion pulsada" + pos);
    Mediator mediator = (Mediator) getView().getApplication();
    setPosicionListaObraSelecionada(pos);
    setIdObraSelecionada(mediator.getIdAutorSelecionado(),pos);

    Navigator app = (Navigator) getView().getApplication();
    app.goToObraScreen(this);
  }
  @Override
  public void onButtonAddObraCliked(){
    Navigator app = (Navigator) getView().getApplication();
    app.goToAddObraScreen(this);

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // To Autor //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      inicializarVista();

    }

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
 public int getPosicionListaObraSelecionada(){
   return posicionListaObraSelecionada;

  }
  @Override
  public int getIdObraSelecionada() {
    return idObraSelecionada;
  }
  public void setPosicionListaObraSelecionada(int pos){
    this.posicionListaObraSelecionada =pos;

  }
  public void setIdObraSelecionada(int idAutor,int pos) {
    this.idObraSelecionada = getModel().getIdObraPulsada(idAutor,pos);
  }
  ///////////////////////////////////////////////////////////////////////////////////
}
