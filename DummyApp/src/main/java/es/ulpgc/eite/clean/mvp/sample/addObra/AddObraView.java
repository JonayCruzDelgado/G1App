package es.ulpgc.eite.clean.mvp.sample.addObra;

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
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObra;
import es.ulpgc.eite.clean.mvp.sample.addObra.AddObraPresenter;

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

    btnDone=(Button) findViewById(R.id.btnDoneObra);
    addImagen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //getPresenter().onButtonDoneClicked();
      }
    });

    addImagen = (Button) findViewById(R.id.addImagenObra);
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
  public Double getLatitud(){
    String texto =latitudIndtroducida.getText().toString();
    Double value;

    if(texto == null || texto.isEmpty()) {

      value = 0.0;

    } else {

      value = Double.parseDouble(texto);

    }
    return value;
  }
  @Override
  public Double getLongitud(){
    String texto =longitudIndtroducida.getText().toString();
    Double value;

    if(texto == null || texto.isEmpty()) {

      value = 0.0;

    } else {

      value = Double.parseDouble(texto);

    }
    return value;
  }

}
