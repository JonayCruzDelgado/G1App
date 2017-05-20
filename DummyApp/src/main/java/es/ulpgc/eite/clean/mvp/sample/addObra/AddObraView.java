package es.ulpgc.eite.clean.mvp.sample.addObra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Observable;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class AddObraView
    extends GenericActivity<AddObra.PresenterToView, AddObra.ViewToPresenter, AddObraPresenter>
    implements AddObra.PresenterToView {

  private Toolbar toolbar;
  private Button addImagen;
  private Button btnDone;
  private EditText nombreIndtroducido;
  private EditText descripcionIndtroducida;
  private EditText latitudIndtroducida;
  private EditText longitudIndtroducida;
  private ImageView imagenSelecionada;
  private static MyObservable observable;
  private static final int SELECT_PICTURE = 1;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_obra);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nombreIndtroducido = (EditText) findViewById(R.id.nombreObraIntroducido);
    nombreIndtroducido.setMovementMethod(new ScrollingMovementMethod());
    descripcionIndtroducida = (EditText) findViewById(R.id.descripcionObraIntroducida);
    descripcionIndtroducida.setMovementMethod(new ScrollingMovementMethod());
    latitudIndtroducida = (EditText) findViewById(R.id.latitudIntroducida);
    latitudIndtroducida.setMovementMethod(new ScrollingMovementMethod());
    longitudIndtroducida = (EditText) findViewById(R.id.longitudIntroducida);
    longitudIndtroducida.setMovementMethod(new ScrollingMovementMethod());
    imagenSelecionada = (ImageView) findViewById(R.id.imagenSeleccionadaObra);

    btnDone = (Button) findViewById(R.id.btnDoneObra);
    btnDone.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onButtonDoneClicked();
      }
    });

    addImagen = (Button) findViewById(R.id.addImagenObra);
    addImagen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onButtonAddImagenClicked();
        observable = new MyObservable();
        observable.addObserver(getPresenter().getObserver());

      }
    });
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d(TAG,"on activity result");
    if (resultCode == RESULT_OK) {
      Uri selectedImageUri = data.getData();
      observable.imagenSelecionada(selectedImageUri);

    }
  }

  class MyObservable extends Observable{
    public void imagenSelecionada(Uri uri){
      Log.d(TAG,"on observable");
      setChanged();
      notifyObservers(uri);
    }
  }


  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(AddObraPresenter.class, this);

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    finish();
  }
  @Override
  public void startGaleria(Intent intent){
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

  }
  @Override
  public String getNombre(){
    return nombreIndtroducido.getText().toString();
  }
  @Override
  public String getDescripcion(){
    return descripcionIndtroducida.getText().toString();
  }
  @Override
  public String getLatitud(){
     return latitudIndtroducida.getText().toString();

  }
  @Override
  public String getLongitud(){
    return longitudIndtroducida.getText().toString();
  }
  @Override
  public void setTitle(String txt){
    toolbar.setTitle(txt);
  }
  @Override
  public void showToast(String txt){
      Toast toast = Toast.makeText(getActivityContext(),txt,Toast.LENGTH_SHORT);
    toast.show();
  }
  @Override
  public void setImagen(Bitmap imagen){
     imagenSelecionada.setImageBitmap(imagen);
  }
  @Override
  public void showImagen(){
    imagenSelecionada.setVisibility(View.VISIBLE);
  }
  @Override
  public void hideImagen(){
    imagenSelecionada.setVisibility(View.GONE);
  }



}
