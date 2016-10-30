package raduga.duga.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.camera.CameraSettings;

import net.grandcentrix.thirtyinch.TiActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import raduga.duga.DCaptureManager;
import raduga.duga.R;
import raduga.duga.model.BarCodeServerDB;
import raduga.duga.presenter.OrientationPresenter;
import raduga.duga.view.OrientationView;
import rx.Observable;

/**
 * Created by Shcherbakov on 16.09.2016.
 */
public class OrientationActivity extends TiActivity<OrientationPresenter, OrientationView> implements OrientationView {

    private DCaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private CameraSettings cameraSettings;

    @Bind(R.id.eventName)
    TextView eventNameView;

    @Bind(R.id.dateBegin)
    TextView dateBeginView;

    @Bind(R.id.dateEnd)
    TextView dateEndView;

    @Bind(R.id.barCode)
    TextView barCodeView;

    @Bind(R.id.letIn)
    TextView letInView;

    @Override
    public void makeVibrate(int duration) {
        ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(duration);
    }

    @Override
    public String getBarCodeString() {
        //barCodeView = (TextView) findViewById(R.id.barCode);
        String a =  barCodeView.getText().toString();
        if(a.equals(null))
            return "aa";
        return a;
    }

    @Override
    public Observable<BarCodeServerDB> getObs() {
        return capture.decodeAsObs();
    }

    @Override
    public Context getApp() {
        return getBaseContext();
    }

    @Override
    public void setCorrectBarCode(BarCodeServerDB code) {
        letInView.setText("ВХОД РАЗРЕШЕН");
        letInView.setTextColor(Color.parseColor("#00ac25"));

        barCodeView.setText(code.getEventId());
        eventNameView.setText(code.getEventName());
        dateBeginView.setText(code.getDateBegin());
        dateEndView.setText(code.getDateEnd());
    }

    @Override
    public void setIncorrectBarCode(BarCodeServerDB code) {
        letInView.setText("ВХОД НЕРАЗРЕШЕН");
        letInView.setTextColor(Color.parseColor("#FFEC0010"));

        barCodeView.setText(code.getEventId());
        eventNameView.setText("");
        dateBeginView.setText("");
        dateEndView.setText("");
    }

    @NonNull
    @Override
    public OrientationPresenter providePresenter() {
        return new OrientationPresenter();
    }

    private void fillDB(Realm real, String eventId, String eventName){
        real.beginTransaction();
        BarCodeServerDB barCode = real.createObject(BarCodeServerDB.class);
        barCode.setEventId(eventId);
        barCode.setEventName(eventName);
        barCode.setDateBegin("14-30 12-12-2016");
        barCode.setDateEnd("18-00 12-12-2016");
        barCode.setLetIn(1);
        real.commitTransaction();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        barcodeScannerView = initializeContent();

        capture = new DCaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
        Realm realm = Realm.getInstance(this);

        try{
        fillDB(realm, "4607167312203", "QWERTTT");
        fillDB(realm, "555020137962", "FSDFSDF");
        fillDB(realm, "789620137962", "GFDGDHB");
        fillDB(realm, "978020218762", "GHKPPIB");
        fillDB(realm, "367401377962", "IPOVCXU");
        fillDB(realm, "870012337962", "{POBCXB");
        fillDB(realm, "789620137962", "UOJJKLN");
        fillDB(realm, "999999137962", "{POUYMBXQ");
        fillDB(realm, "978132465962", "123KPMBM");
        fillDB(realm, "972123774442", "{POUOIGDF");
        } catch (Exception e){
            e.printStackTrace();
        }
//        cameraSettings = new CameraSettings();
//        cameraSettings.setScanInverted(true);
//        cameraSettings.setExposureEnabled(true);
//        cameraSettings.setFocusMode(CameraSettings.FocusMode.INFINITY);
        ButterKnife.bind(this);
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
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

}
