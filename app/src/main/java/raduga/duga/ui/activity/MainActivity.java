package raduga.duga.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.grandcentrix.thirtyinch.TiActivity;

import raduga.duga.R;
import raduga.duga.presenter.MainPresenter;
import raduga.duga.view.MainView;
import rx.subjects.BehaviorSubject;


public class MainActivity extends TiActivity<MainPresenter, MainView> implements MainView {

    @NonNull
    @Override
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    public void showScanner() {
        startActivity(new Intent(this, OrientationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
