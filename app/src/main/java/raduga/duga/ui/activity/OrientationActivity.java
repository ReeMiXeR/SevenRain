package raduga.duga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import io.realm.Realm;
import io.realm.RealmResults;
import raduga.duga.R;
import raduga.duga.model.BarCode;

/**
 * Created by Shcherbakov on 16.09.2016.
 */
public class OrientationActivity extends CaptureActivity {
    private Realm mRealm;


    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.orientation_activity);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getInstance(this);
        Log.e("ee", "Table - " + mRealm.getTable(BarCode.class).toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Error");
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();

            } else {
                Log.d("MainActivity", "Scanned");

                mRealm.beginTransaction();
                RealmResults<BarCode> code = mRealm.where(BarCode.class).equalTo("eventName", result.getContents()).findAll();
                if (!code.isEmpty()){
                    BarCode barCode = code.get(0);
                    Toast.makeText(this, "Greate! Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
                mRealm.commitTransaction();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
