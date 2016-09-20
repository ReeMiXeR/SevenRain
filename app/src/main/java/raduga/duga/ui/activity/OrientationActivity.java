package raduga.duga.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import net.grandcentrix.thirtyinch.TiActivity;

import org.w3c.dom.Text;

import io.realm.Realm;
import raduga.duga.DCaptureManager;
import raduga.duga.R;
import raduga.duga.model.BarCode;
import raduga.duga.presenter.OrientationPresenter;
import raduga.duga.view.OrientationView;

/**
 * Created by Shcherbakov on 16.09.2016.
 */
public class OrientationActivity extends TiActivity<OrientationPresenter, OrientationView> implements OrientationView {
    private Realm mRealm;
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    TextView barCodeView;
    TextView eventName;
    TextView dateBegin;
    TextView dateEnd;


    @Override
    public TextView getBarCodeView() {
        return (TextView) findViewById(R.id.barCode);
    }

    @Override
    public Context getApp() {
        return getBaseContext();
    }

    @Override
    public void setBarCode(BarCode code) {
        eventName = (TextView) findViewById(R.id.eventName);
        dateBegin = (TextView) findViewById(R.id.dateBegin);
        dateEnd = (TextView) findViewById(R.id.dateEnd);
        eventName.setText(code.getEventName());
        dateBegin.setText(code.getDateBegin());
        dateEnd.setText(code.getDateEnd());
    }

    @NonNull
    @Override
    public OrientationPresenter providePresenter() {
        return new OrientationPresenter();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        barCodeView = (TextView) findViewById(R.id.barCode);


//        mRealm = Realm.getInstance(this);
//        Log.e("ee", mRealm.getTable(BarCode.class).toString());
//        mRealm.beginTransaction();
//        BarCode barCode = mRealm.createObject(BarCode.class);
//        barCode.setEventId("3330004637115");
//        barCode.setEventName("Zopa i moroz");
//        barCode.setDateBegin("14-30 12-12-2016");
//        barCode.setDateEnd("18-00 12-12-2016");
//        barCode.setLetIn(1);
//        mRealm.commitTransaction();
//        Log.e("ee", mRealm.getTable(BarCode.class).toString());


        barcodeScannerView = initializeContent();

        capture = new DCaptureManager(this, barcodeScannerView);

        //capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
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
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

}
