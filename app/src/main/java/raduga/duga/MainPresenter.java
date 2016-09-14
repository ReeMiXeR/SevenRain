package raduga.duga;

import android.app.Activity;

import com.google.zxing.integration.android.IntentIntegrator;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shcherbakov on 14.09.2016.
 */
public class MainPresenter extends TiPresenter<MainView> {

    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        getView().showCamera();
    }
}

