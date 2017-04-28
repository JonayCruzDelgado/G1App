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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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
  private static final int SELECT_PICTURE = 1;

  private String selectedImagePath;

  @Override
  public String getSelectedImagePath() {
    return selectedImagePath;
  }



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_obra);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nombreIndtroducido = (EditText) findViewById(R.id.nombreObraIntroducido);
    descripcionIndtroducida = (EditText) findViewById(R.id.descripcionObraIntroducida);
    latitudIndtroducida = (EditText) findViewById(R.id.latitudIntroducida);
    longitudIndtroducida = (EditText) findViewById(R.id.longitudIntroducida);
    imagenSelecionada=(ImageView) findViewById(R.id.imagenSeleccionadaObra);

    btnDone=(Button) findViewById(R.id.btnDoneObra);
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
    // in onCreate or any event where your want the user to
        // select a file


//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,
//                "Select Picture"), SELECT_PICTURE);

        Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );

        startActivityForResult(Intent.createChooser(intent,
            "Select Picture"), SELECT_PICTURE);
      }
    });
  }
  public void onActivityResult(int requestCode, int resultCode, Intent data) {


    if (resultCode == RESULT_OK) {

      Uri selectedImageUri = data.getData();
      Log.d(getClass().getSimpleName() + ".onActivityResult", "selectedImageUri=" + selectedImageUri);

      /*
      try {

        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
        imagenSelecionada.setImageBitmap(bitmap);

      } catch (IOException error) {
        Log.d(getClass().getSimpleName() + ".onActivityResult", "error=" + error);
      }

      */

      if(selectedImageUri != null){
        //selectedImagePath = selectedImageUri.toString();
        selectedImagePath = getRealPathFromURI(selectedImageUri);
        Log.d(getClass().getSimpleName() + ".onActivityResult", "selectedImagePath=" + selectedImagePath);
        if(selectedImagePath != null) {
          getPresenter().setImagen(); // el presentador captura la imagen para manejarla el con los estados
          getPresenter().onResume(this); // refrescar la pantalla al salir de la galeria para que aparesca la imagen
        }
      }

    }

  }


  public String getRealPathFromURI(Uri contentUri) {

    String res = null;
    String[] proj = { MediaStore.Images.Media.DATA };
    Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
    if (cursor.moveToFirst()) {

      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      res = cursor.getString(column_index);
    }
    cursor.close();

    if( res != null) {
      File imgFile = new File(res);

      if (imgFile.exists()) {

        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


      }
    }

    return res;
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


  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_addObra, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
  */


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    finish();
  }

  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
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
  public void setImagen(String imagen){
    File imgFile = new  File(imagen);
    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

    imagenSelecionada.setImageBitmap(myBitmap);
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
