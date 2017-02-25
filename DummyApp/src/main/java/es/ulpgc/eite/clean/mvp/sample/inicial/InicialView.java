package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.inicial.Inicial;
import es.ulpgc.eite.clean.mvp.sample.inicial.InicialPresenter;

public class InicialView
    extends GenericActivity<Inicial.PresenterToView, Inicial.ViewToPresenter, InicialPresenter>
    implements Inicial.PresenterToView {

  private Toolbar toolbar;
  private Button button;
  private LinearLayout layoutPintura;
  private LinearLayout layoutArquitectura;
  private LinearLayout layoutEscultura;
  private TextView labelPintura;
  private TextView labelArquitectura;
  private TextView labelEscultura;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicial);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    labelPintura = (TextView) findViewById(R.id.textoBtnPintura);
    labelArquitectura = (TextView) findViewById(R.id.textoBtnArquitectura);
    labelEscultura = (TextView) findViewById(R.id.textoBtnEscultura);

    layoutPintura =(LinearLayout) findViewById(R.id.btnPintura);
    layoutPintura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    layoutArquitectura =(LinearLayout) findViewById(R.id.btnArquitectura);

    layoutArquitectura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    layoutEscultura =(LinearLayout) findViewById(R.id.btnEscultura);
    layoutEscultura.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });


    button = (Button) findViewById(R.id.button);
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

}
