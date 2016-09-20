package raduga.duga;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;
/**
 * Created by android_dev on 19/09/16.
 */
public class DCaptureManager extends CaptureManager {
    DecoratedBarcodeView mBarcodeView;
    Activity mActivity;
    TextView barCodeView;
    String barCode;

    public DCaptureManager(Activity activity, DecoratedBarcodeView barcodeView) {
        super(activity, barcodeView);

        mActivity = activity;
        mBarcodeView = barcodeView;

    }


    @Override
    public void decode() {
        mBarcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                barCodeView = (TextView) mActivity.findViewById(R.id.barCode);

                if(!(barCodeView.getText().toString().equals(result.toString()))) {
                    barCode = result.toString();
                    Log.e("barcode", result.toString());

                    barCodeView.setText(result.toString());
                }
                //Toast.makeText(mActivity, result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
        //mBarcodeView.decodeSingle();
    }
}
