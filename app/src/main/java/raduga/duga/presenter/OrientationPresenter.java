package raduga.duga.presenter;

import android.util.Log;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;

import io.realm.Realm;
import raduga.duga.model.BarCode;
import raduga.duga.realm.RealmController;
import raduga.duga.ui.activity.MainActivity;
import raduga.duga.view.OrientationView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public class OrientationPresenter extends TiPresenter<OrientationView>  {
    private RxTiPresenterSubscriptionHandler rxSubscriptionHelper = new RxTiPresenterSubscriptionHandler(this);
    Realm realm;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();

         realm = Realm.getInstance(getView().getApp());

        Subscription subscription = RxTextView.afterTextChangeEvents(
                getView().getBarCodeView())
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                            BarCode code = realm.where(BarCode.class).equalTo(
                                    "eventId",
                                    getView().getBarCodeView().getText().toString())
                                    .findFirst();
                            if(code != null) {
                                getView().setBarCode(code);
                            }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
