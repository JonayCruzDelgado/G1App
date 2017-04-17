package es.ulpgc.eite.clean.mvp.sample.autor;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class AutorView
    extends GenericActivity<Autor.PresenterToView, Autor.ViewToPresenter, AutorPresenter>
    implements Autor.PresenterToView {

  private Toolbar toolbar;
  private TextView descripcionAutor;
  private ListView listaObras;
  private ImageView iconoAutor;
  private ImageView btnAddObra;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_autor);

    descripcionAutor = (TextView) findViewById(R.id.descripcionAutor);
    descripcionAutor.setMovementMethod(new ScrollingMovementMethod());

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    iconoAutor = (ImageView) findViewById(R.id.imagenAutor);
    btnAddObra=(ImageView) findViewById(R.id.btnAddObra);
    btnAddObra.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonAddObraCliked();

      }
    });

    listaObras = (ListView) findViewById(R.id.listaObras);
    listaObras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onItemClickSelected(i);
      }
    });
  }

  private void onItemClickSelected(int pos) {
    getPresenter().onItemClickSelected (pos);
  }


  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(AutorPresenter.class, this);
    getPresenter().inicializarVista();

  }

  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_Autor, menu);
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
  public void hideText() {
    descripcionAutor.setVisibility(View.GONE);
  }

  @Override
  public void showText() {
    descripcionAutor.setVisibility(View.VISIBLE);
  }

  public void setDescripcionAutor(String txt) {
    descripcionAutor.setText(txt);
  }

  public void setIconoAutor(String imagen) {

    iconoAutor.setImageBitmap(getBitmapFromAssets(imagen));
  }
  @Override
  public void actualizarListaObras(String[] nombresObras){

      ArrayAdapter<String> arrayAdapter = new
              ArrayAdapter<String>(
              this,
              android.R.layout.simple_expandable_list_item_1,
              nombresObras
      );
      listaObras.setAdapter(arrayAdapter);

  }
  @Override
  public void setNombreAutor(String txt) {
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
}
