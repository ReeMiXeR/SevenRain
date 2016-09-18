package raduga.duga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import net.grandcentrix.thirtyinch.TiActivity;

import io.realm.Realm;
import io.realm.RealmResults;
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

    @NonNull
    @Override
    public OrientationPresenter providePresenter() {
        return new OrientationPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getInstance(this);
        Log.e("ee", "Table - " + mRealm.getTable(BarCode.class).toString());


        barcodeScannerView = initializeContent();

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    /**
     * Override to use a different layout.
     *
     * @return the DecoratedBarcodeView
     */
//    protected DecoratedBarcodeView initializeContent() {
//        setContentView(com.google.zxing.client.android.R.layout.zxing_capture);
//        return (DecoratedBarcodeView)findViewById(com.google.zxing.client.android.R.id.zxing_barcode_scanner);
//    }

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
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Log.d("MainActivity", "Error");
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
//
//            } else {
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
//    }
}
