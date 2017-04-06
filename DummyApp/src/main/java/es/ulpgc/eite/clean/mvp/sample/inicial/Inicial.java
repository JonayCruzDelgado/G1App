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
    int getIdBoton();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {

    void onButtonClicked2();
    void onButtonClicked1();
    void onButtonClicked3();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void setLabel1(String txt);
    void setLabel3(String txt);
    void setLabel2(String txt);
    void setIcono1(Bitmap bp);
    void setIcono2(Bitmap bp);
    void setIcono3(Bitmap bp);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getTextBtn1();
    String getTextBtn2();
    String getTextBtn3();
    Bitmap getIconoBtn1(Context context);
    Bitmap getIconoBtn2(Context context);
    Bitmap getIconoBtn3(Context context);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }
}
