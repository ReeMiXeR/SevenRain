package raduga.duga.view;

import android.content.Context;

import net.grandcentrix.thirtyinch.TiView;

import raduga.duga.model.BarCodeServerDB;
import rx.Observable;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public interface OrientationView extends TiView {

    Context getApp();

    void setCorrectBarCode(BarCodeServerDB code);

    void setIncorrectBarCode(BarCodeServerDB code);

    Observable<BarCodeServerDB> getObs();

    String getBarCodeString();

    void makeVibrate(int duration);
}
