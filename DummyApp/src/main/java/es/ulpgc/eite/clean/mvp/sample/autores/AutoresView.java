package es.ulpgc.eite.clean.mvp.sample.autores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class AutoresView
    extends GenericActivity<Autores.PresenterToView, Autores.ViewToPresenter, AutoresPresenter>
    implements Autores.PresenterToView {

  private Toolbar toolbar;
  private FloatingActionButton btnAddAutor;
  private ListView listaAutores;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_autores);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    btnAddAutor= (FloatingActionButton) findViewById(R.id.btnAddAutor);
    btnAddAutor.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonAddAutorCliked();

      }
    });

    listaAutores = (ListView) findViewById(R.id.listaAutores);
    listaAutores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    super.onResume(AutoresPresenter.class, this);

  }


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
  public void actualizarListaAutores(String[] nombresAutores){
    ArrayAdapter<String> arrayAdapter = new
            ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            nombresAutores
    );
    listaAutores.setAdapter(arrayAdapter);
  }
  @Override
  public void setTituloToolbar(String txt) {
    toolbar.setTitle(txt);
  }

}
