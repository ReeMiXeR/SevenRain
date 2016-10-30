package raduga.duga.presenter;

import android.media.AudioManager;
import android.media.ToneGenerator;

import com.google.zxing.integration.android.IntentIntegrator;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;

import io.realm.Realm;
import raduga.duga.model.BarCodeServerDB;
import raduga.duga.view.OrientationView;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public class OrientationPresenter extends TiPresenter<OrientationView>  {
    private RxTiPresenterSubscriptionHandler rxSubscriptionHelper = new RxTiPresenterSubscriptionHandler(this);
    Realm realm;
    BarCodeServerDB barCodeServerDB;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();



        realm = Realm.getInstance(getView().getApp());


        getView().getObs()
                .filter(obj -> !(obj.getEventId().equals(getView().getBarCodeString())))
                
                .subscribe(s -> {
                    barCodeServerDB = realm.where(BarCodeServerDB.class).equalTo("eventId", s.getEventId()).findFirst();
                    if(barCodeServerDB != null) {
//                        barCodeCheckedDB = realm.where(BarCodePCheckedDB.class).equalTo("eventId", s.getEventId()).findFirst();
//                         if(barCodeCheckedDB != null){
//                            getView().setIncorrectBarCode(barCodeCheckedDB);
//                         }
                        getView().setCorrectBarCode(barCodeServerDB);
                        getView().makeVibrate(200);
                    }
                    else {
                        getView().setIncorrectBarCode(s);
                        getView().makeVibrate(1000);
                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 300);
                    }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
