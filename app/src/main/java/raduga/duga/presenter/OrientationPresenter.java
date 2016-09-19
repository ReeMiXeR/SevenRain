package raduga.duga.presenter;

import android.util.Log;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;

import io.realm.Realm;
import raduga.duga.ui.activity.MainActivity;
import raduga.duga.view.OrientationView;
import rx.functions.Action1;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public class OrientationPresenter extends TiPresenter<OrientationView>  {
    private RxTiPresenterSubscriptionHandler rxSubscriptionHelper = new RxTiPresenterSubscriptionHandler(this);
    private Realm mRealm;


    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        //mRealm = Realm.getInstance(OrientationActivity.class);

        MainActivity.activityResultSubject
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                })
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("wqe", s);
                getView().setText(s);

            }
        });

//        getView().activityResult()
//                .doOnError(new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                })
//                .subscribe(new Action1<ActivityResultEvent>() {
//            @Override
//            public void call(ActivityResultEvent activityResultEvent) {
//                Log.e("qwe", activityResultEvent.toString());
//                getView().setText(activityResultEvent.toString());
//
//            }
//        });

    }
}
