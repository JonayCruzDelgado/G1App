package es.ulpgc.eite.clean.mvp.sample.inicial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Inicial {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface ToInicial {
    void onScreenStarted();
    void setToolbarVisibility(boolean visible);
  }

  interface InicialTo {
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();


    String getLayoutClicked();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {

    void onButtonClickedArquitectura();

    void onButtonClickedPintura();

    void onButtonClickedEscultura();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();



    void setLabelPintura(String txt);

    void setLabelEscultura(String txt);

    void setLabelArquitectura(String txt);

    void setIconoPintura(Bitmap bp);

    void setIconoArquitectura(Bitmap bp);

    void setIconoEscultura(Bitmap bp);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getTextBtn1();
    String getTextBtn2();
    String getTextBtn3();

    Bitmap getIconoPintura(Context context);

    Bitmap getIconoArquitectura(Context context);

    Bitmap getIconoEscultura(Context context);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
