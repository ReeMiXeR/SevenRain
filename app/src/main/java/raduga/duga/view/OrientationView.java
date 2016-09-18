package raduga.duga.view;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import net.grandcentrix.thirtyinch.TiView;

import raduga.duga.ActivityResultEvent;
import rx.Observable;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public interface OrientationView extends TiView {


    Observable<String> barCode();

    Observable<ActivityResultEvent> activityResult();

}
