package raduga.duga;

import com.google.zxing.integration.android.IntentIntegrator;

import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

/**
 * Created by Shcherbakov on 14.09.2016.
 */
public interface MainView extends TiView {

    @CallOnMainThread
    void showCamera();
}
