package raduga.duga;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

import javax.security.auth.Subject;

import raduga.duga.model.BarCodeServerDB;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by android_dev on 19/09/16.
 */
public class DCaptureManager extends CaptureManager {
    DecoratedBarcodeView mBarcodeView;
    Activity mActivity;

    private final PublishSubject<BarCodeServerDB> subject = PublishSubject.create();

    public DCaptureManager(Activity activity, DecoratedBarcodeView barcodeView) {
        super(activity, barcodeView);

        mActivity = activity;
        mBarcodeView = barcodeView;

    }

    public Observable<BarCodeServerDB> decodeAsObs(){
        return subject.asObservable();
    }

    @Override
    public void decode() {
        mBarcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                BarCodeServerDB barCode = new BarCodeServerDB();
                barCode.setEventId(result.toString());
                subject.onNext(barCode);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
