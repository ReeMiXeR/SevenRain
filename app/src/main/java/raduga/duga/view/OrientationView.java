package raduga.duga.view;

import net.grandcentrix.thirtyinch.TiView;

import raduga.duga.ActivityResultEvent;
import rx.Observable;

/**
 * Created by Shcherbakov on 18.09.2016.
 */
public interface OrientationView extends TiView {


    Observable<String> barCode();

    Observable<ActivityResultEvent> activityResult();

    void setText(String text);

}
