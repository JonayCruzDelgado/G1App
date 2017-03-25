package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialPresenter;
import io.realm.Realm;

public class InicialView
    extends GenericActivity<Inicial.PresenterToView, Inicial.ViewToPresenter, InicialPresenter>
    implements Inicial.PresenterToView {

  private Toolbar toolbar;
  private LinearLayout layoutPintura;
  private LinearLayout layoutArquitectura;
  private LinearLayout layoutEscultura;
  private TextView labelPintura;
  private TextView labelArquitectura;
  private TextView labelEscultura;
  private ImageView iconoPintura;
  private ImageView iconoArquitectura;
  private ImageView iconoEscultura;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicial);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    iconoPintura =(ImageView)  findViewById(R.id.iconoBtnPintura);
    iconoArquitectura =(ImageView)  findViewById(R.id.iconoBtnArquitectura);
    iconoEscultura =(ImageView)  findViewById(R.id.iconoBtnEscultura);

    labelPintura = (TextView) findViewById(R.id.textoBtnPintura);
    labelArquitectura = (TextView) findViewById(R.id.textoBtnArquitectura);
    labelEscultura = (TextView) findViewById(R.id.textoBtnEscultura);

    layoutPintura =(LinearLayout) findViewById(R.id.btnPintura);
    layoutPintura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClickedPintura();

      }
    });

    layoutArquitectura =(LinearLayout) findViewById(R.id.btnArquitectura);
    layoutArquitectura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClickedArquitectura();

      }
    });

    layoutEscultura =(LinearLayout) findViewById(R.id.btnEscultura);
    layoutEscultura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClickedEscultura();

      }
    });
    /*Log.d(TAG, "antes init realm");

    Realm.init(this);

    Log.d(TAG, "despues init realm");*/

  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(InicialPresenter.class, this);

  }

  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_Inicial, menu);
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
  public void setLabelPintura(String txt) {
    labelPintura.setText(txt);
  }
  @Override
  public void setLabelEscultura(String txt) {
    labelEscultura.setText(txt);
  }
  @Override
  public void setLabelArquitectura(String txt) {
    labelArquitectura.setText(txt);
  }

  @Override
  public void setIconoPintura(Bitmap bp) {
    iconoPintura.setImageBitmap(bp);
  }
  @Override
  public void setIconoArquitectura(Bitmap bp) {
    iconoArquitectura.setImageBitmap(bp);
  }
  @Override
  public void setIconoEscultura(Bitmap bp) {
    iconoEscultura.setImageBitmap(bp);
  }

}
