package raduga.duga.ui.activity;


import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.TextView;


import com.google.zxing.integration.android.IntentIntegrator;

import net.grandcentrix.thirtyinch.TiActivity;


import raduga.duga.presenter.MainPresenter;
import raduga.duga.view.MainView;
import raduga.duga.R;


public class MainActivity extends TiActivity<MainPresenter, MainView> implements MainView {



    @NonNull
    @Override
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }



    @Override
    public void showScanner() {
        new IntentIntegrator(this)
                .setCaptureActivity(OrientationActivity.class)
                .setOrientationLocked(false)
                .setPrompt("Сканируйте билет")
                .initiateScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
