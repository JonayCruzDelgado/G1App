package es.ulpgc.eite.clean.mvp.sample.addObra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

  @Override
  public String getSelectedImagePath() {
    return selectedImagePath;
  }


  private String selectedImagePath;

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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);

      }
    });
  }
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (resultCode == RESULT_OK) {

      Uri selectedImageUri = data.getData();
      selectedImagePath = selectedImageUri.toString();
      String var = selectedImagePath;
      getRealPathFromURI(selectedImageUri);
    }
  }
  public String getRealPathFromURI(Uri uri) {
    String[] projection = { MediaStore.Images.Media.DATA };
    @SuppressWarnings("deprecation")
    Cursor cursor = managedQuery(uri, projection, null, null, null);
    int column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    imagenSelecionada.setImageURI(uri);
    Picasso.with(this).load(cursor.getString(column_index)).into(imagenSelecionada);
    return cursor.getString(column_index);
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
