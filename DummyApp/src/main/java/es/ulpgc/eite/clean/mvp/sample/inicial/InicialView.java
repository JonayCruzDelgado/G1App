package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

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

  public void setLabel1(String txt) {
    label1.setText(txt);
  }
  public void setLabel3(String txt) {
    label3.setText(txt);
  }
  public void setLabel2(String txt) {
    label2.setText(txt);
  }
  public void setIcono1(String icono) {
    icono1.setImageBitmap(getBitmapFromAssets(icono));
  }
  public void setIcono2(String icono) {
    icono2.setImageBitmap(getBitmapFromAssets(icono));
  }
  public void setIcono3(String icono) {
    icono3.setImageBitmap(getBitmapFromAssets(icono));
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
