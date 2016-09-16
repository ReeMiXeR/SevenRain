package raduga.duga.presenter;


import net.grandcentrix.thirtyinch.TiPresenter;

import raduga.duga.view.MainView;

/**
 * Created by Shcherbakov on 14.09.2016.
 */
public class MainPresenter extends TiPresenter<MainView> {

    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        getView().showScanner();
    }


}

