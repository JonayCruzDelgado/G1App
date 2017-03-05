package es.ulpgc.eite.clean.mvp.sample.autor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class AutorView
    extends GenericActivity<Autor.PresenterToView, Autor.ViewToPresenter, AutorPresenter>
    implements Autor.PresenterToView {

  private Toolbar toolbar;
  private TextView text;
  private ListView listaObras;
  private ImageView iconoAutor;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_autor);

    text = (TextView) findViewById(R.id.descripcionAutor);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    listaObras = (ListView) findViewById(R.id.listaObras);

    iconoAutor = (ImageView) findViewById(R.id.imagenAutor);
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
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
    text.setVisibility(View.GONE);
  }

  @Override
  public void showText() {
    text.setVisibility(View.VISIBLE);
  }

  @Override
  public void setText(String txt) {
    text.setText(txt);
  }

  public void setIconoAutor(Bitmap bp) {
    iconoAutor.setImageBitmap(bp);
  }
  @Override
  public void actualizarLista(String[] nombresObras){
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
}
