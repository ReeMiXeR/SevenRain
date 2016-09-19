package raduga.duga;

import net.grandcentrix.thirtyinch.TiView;

/**
 * Created by Teacher on 19.09.2016.
 */
public interface BarCodeInt extends TiView {

    rx.Observable<ActivityResultEvent> codePass();
}
