package raduga.duga.view;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import net.grandcentrix.thirtyinch.TiView;

import raduga.duga.model.BarCode;
import rx.Observable;
import rx.Observer;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public interface OrientationView extends TiView {

    TextView getBarCodeView();

    Context getApp();

    void setBarCode(BarCode code);
}
