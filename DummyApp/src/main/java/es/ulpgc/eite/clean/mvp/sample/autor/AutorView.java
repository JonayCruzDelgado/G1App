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

import java.io.File;
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


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    finish();
  }

  @Override
  public void setNombreAutor(String txt) {
    toolbar.setTitle(txt);
  }

  public void setDescripcionAutor(String txt) {
    descripcionAutor.setText(txt);
  }

  public void setImagenAutor(Bitmap imagen) {
    iconoAutor.setImageBitmap(imagen);
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
}
