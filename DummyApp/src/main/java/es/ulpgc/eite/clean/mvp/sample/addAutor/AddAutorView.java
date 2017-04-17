package es.ulpgc.eite.clean.mvp.sample.addAutor;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class AddAutorView
    extends GenericActivity<AddAutor.PresenterToView, AddAutor.ViewToPresenter, AddAutorPresenter>
    implements AddAutor.PresenterToView {

  private Toolbar toolbar;
  private Button addImagen;
  private EditText nombreIndtroducido;
  private EditText descripcionIndtroducida;
  private Button btnDone;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_autor);


    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    nombreIndtroducido = (EditText) findViewById(R.id.nombreAutorIntroducido);
    descripcionIndtroducida = (EditText) findViewById(R.id.descripcionAutorIntroducida);

    btnDone=(Button) findViewById(R.id.btnDoneAutor);
    btnDone.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onButtonDoneClicked();
      }
    });

    addImagen = (Button) findViewById(R.id.addImagenAutor);
    addImagen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //getPresenter().onButtonClicked();
      }
    });


  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(AddAutorPresenter.class, this);

  }

  // Custom method to get assets folder image as bitmap


  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_addAutor, menu);
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
  public void setTitle(String txt){
    toolbar.setTitle(txt);
  }


}
