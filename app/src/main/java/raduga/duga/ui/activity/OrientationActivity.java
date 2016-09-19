package raduga.duga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import net.grandcentrix.thirtyinch.TiActivity;

import io.realm.Realm;
import raduga.duga.ActivityResultEvent;
import raduga.duga.R;
import raduga.duga.model.BarCode;
import raduga.duga.presenter.OrientationPresenter;
import raduga.duga.view.OrientationView;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;

/**
 * Created by Shcherbakov on 16.09.2016.
 */
public class OrientationActivity extends TiActivity<OrientationPresenter, OrientationView> implements OrientationView {
    private Realm mRealm;
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    TextView barCodeView;
    private final BehaviorSubject<ActivityResultEvent> activityResultSubject = BehaviorSubject.create();

    @Override
    public Observable<String> barCode() {
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );
    }

    @Override
    public Observable<ActivityResultEvent> activityResult() {
        return activityResultSubject.asObservable();
    }

    @NonNull
    @Override
    public OrientationPresenter providePresenter() {
        return new OrientationPresenter();
    }

    @Override
    public void setText(String text) {
        barCodeView.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getInstance(this);



//        mRealm.beginTransaction();
//        BarCode barCode = mRealm.createObject(BarCode.class);
//        barCode.setEventId(14883333);
//        barCode.setEventName("Zopa i moroz");
//        barCode.setDateBegin("14-30 12-12-2016");
//        barCode.setDateEnd("18-00 12-12-2016");
//        barCode.setLetIn(1);
//        mRealm.commitTransaction();

        Log.e("ee", mRealm.getTable(BarCode.class).toString());

        barcodeScannerView = initializeContent();

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        TextView text = (TextView) findViewById(R.id.eventId);

        Intent intent = new Intent();
        intent.putExtra("barCode", 14881488);

        text.setOnClickListener(v -> onActivityResult(1,2,intent));

        barCodeView = (TextView) findViewById(R.id.barCode);




    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
        mRealm.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.orientation_activity);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        Log.e("e", "ActivityResult - ");
//        super.onActivityResult(requestCode, resultCode, data);
//        activityResultSubject.onNext(ActivityResultEvent.create(requestCode, resultCode, data));
//
//        if(result != null) {
//            if(result.getContents() == null) {
//                Log.d("MainActivity", "Error");
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
//
//            } else {
//
//
//                Log.d("MainActivity", "Scanned");
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

//                mRealm.beginTransaction();
//                RealmResults<BarCode> code = mRealm.where(BarCode.class).equalTo("eventName", result.getContents()).findAll();
//                if (!code.isEmpty()){
//                    BarCode barCode = code.get(0);
//                    Toast.makeText(this, "Greate! Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//                }
//                mRealm.commitTransaction();
//            }
//        } else {
//            // This is important, otherwise the result will not be passed to the fragment
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }
}
