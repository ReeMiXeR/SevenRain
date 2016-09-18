package raduga.duga.presenter;

import android.util.Log;

import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;

import io.realm.Realm;
import raduga.duga.ActivityResultEvent;
import raduga.duga.ui.activity.OrientationActivity;
import raduga.duga.view.OrientationView;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public class OrientationPresenter extends TiPresenter<OrientationView> {
    private RxTiPresenterSubscriptionHandler rxSubscriptionHelper = new RxTiPresenterSubscriptionHandler(this);
    private Realm mRealm;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        mRealm = Realm.getInstance(OrientationActivity.class);

        getView().activityResult().subscribe(new Action1<ActivityResultEvent>() {
            @Override
            public void call(ActivityResultEvent activityResultEvent) {
                Log.e("qwe", activityResultEvent.toString());

            }
        });

    }
}
