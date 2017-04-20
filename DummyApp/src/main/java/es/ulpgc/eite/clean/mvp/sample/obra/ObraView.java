package es.ulpgc.eite.clean.mvp.sample.obra;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.obra.Obra;
import es.ulpgc.eite.clean.mvp.sample.obra.ObraPresenter;

public class ObraView
    extends GenericActivity<Obra.PresenterToView, Obra.ViewToPresenter, ObraPresenter>
    implements Obra.PresenterToView {

  private Toolbar toolbar;
  private ImageView button;
  private TextView descripcion;
  private ImageView imagen;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_obra);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    descripcion = (TextView) findViewById(R.id.descripcionObra);
    descripcion.setMovementMethod(new ScrollingMovementMethod());
    imagen =(ImageView)  findViewById(R.id.imagenObra);

    button = (ImageView) findViewById(R.id.btnMapa);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onButtonClicked();
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
    super.onResume(ObraPresenter.class, this);
    getPresenter().inicializarVista();
  }

  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_obras, menu);
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
  public void setDescripcionObra(String txt) {
    descripcion.setText(txt);
  }

  @Override
  public void setImagenObra(String icono) {
    try{
      imagen.setImageBitmap(getBitmapFromAssets(icono));
    }catch (Exception e){

      Picasso.with(getActivityContext()).load(icono).into(imagen);
    }

  }

  @Override
  public void setNombreObra(String txt) {
    toolbar.setTitle(txt);
  }
  private Bitmap getBitmapFromAssets(String fileName){
    AssetManager am = getAssets();
    InputStream is = null;
    try{

      is = am.open(fileName);
    }catch(IOException e){
      e.printStackTrace();
    }

    Bitmap bitmap = BitmapFactory.decodeStream(is);
    return bitmap;
  }
  @Override
  public void showToast(String txt){
    Toast toast = Toast.makeText(getActivityContext(), txt,Toast.LENGTH_SHORT);
    toast.show();

  }
  }


