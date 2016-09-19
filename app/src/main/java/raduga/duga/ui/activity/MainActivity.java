package raduga.duga.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.grandcentrix.thirtyinch.TiActivity;

import raduga.duga.R;
import raduga.duga.presenter.MainPresenter;
import raduga.duga.view.MainView;
import rx.subjects.BehaviorSubject;


public class MainActivity extends TiActivity<MainPresenter, MainView> implements MainView {

    public static BehaviorSubject<String> activityResultSubject = BehaviorSubject.create();

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
        Log.e("qwe", "Remake");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        activityResultSubject.onNext(result.toString());

    }
}
