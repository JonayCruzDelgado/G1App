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
import io.realm.Realm;

public class InicialView
    extends GenericActivity<Inicial.PresenterToView, Inicial.ViewToPresenter, InicialPresenter>
    implements Inicial.PresenterToView {

  private Toolbar toolbar;
  private LinearLayout layout1;
  private LinearLayout layout2;
  private LinearLayout layout3;
  private TextView label1;
  private TextView label2;
  private TextView label3;
  private ImageView icono1;
  private ImageView icono2;
  private ImageView icono3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicial);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    icono1 =(ImageView)  findViewById(R.id.iconoBtnPintura);
    icono2 =(ImageView)  findViewById(R.id.iconoBtnArquitectura);
    icono3 =(ImageView)  findViewById(R.id.iconoBtnEscultura);

    label1 = (TextView) findViewById(R.id.textoBtnPintura);
    label2 = (TextView) findViewById(R.id.textoBtnArquitectura);
    label3 = (TextView) findViewById(R.id.textoBtnEscultura);

    layout1 =(LinearLayout) findViewById(R.id.btnPintura);
    layout1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClicked1();

      }
    });

    layout2 =(LinearLayout) findViewById(R.id.btnArquitectura);
    layout2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClicked2();

      }
    });

    layout3 =(LinearLayout) findViewById(R.id.btnEscultura);
    layout3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onButtonClicked3();

      }
    });
    Log.d(TAG, " init realm");
    Realm.init(this);
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
  public void setLabel1(String txt) {
    label1.setText(txt);
  }
  @Override
  public void setLabel3(String txt) {
    label3.setText(txt);
  }
  @Override
  public void setLabel2(String txt) {
    label2.setText(txt);
  }
  @Override
  public void setIcono1(Bitmap imagen) {
    icono1.setImageBitmap(imagen);
  }
  public void setIcono2(Bitmap imagen) {
    icono2.setImageBitmap(imagen);
  }
  public void setIcono3(Bitmap imagen) {
    icono3.setImageBitmap(imagen);
  }

}
