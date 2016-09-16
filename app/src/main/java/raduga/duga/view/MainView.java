package raduga.duga.view;

import android.content.Context;


import net.grandcentrix.thirtyinch.TiView;
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;


/**
 * Created by Shcherbakov on 14.09.2016.
 */
public interface MainView extends TiView {

    @CallOnMainThread
    void showScanner();


}
