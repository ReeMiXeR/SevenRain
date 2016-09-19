package raduga.duga;

import android.app.Activity;
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

    public DCaptureManager(Activity activity, DecoratedBarcodeView barcodeView) {
        super(activity, barcodeView);

        mBarcodeView = barcodeView;
        mActivity = activity;
    }

    @Override
    public void decode() {
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Toast.makeText(mActivity, result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }
}
