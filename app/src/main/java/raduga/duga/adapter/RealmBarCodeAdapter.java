package raduga.duga.adapter;

import android.content.Context;

import io.realm.RealmResults;
import raduga.duga.model.BarCode;

/**
 * Created by Teacher on 15.09.2016.
 */
public class RealmBarCodeAdapter extends RealmModelAdapter<BarCode> {

    public RealmBarCodeAdapter(Context context, RealmResults<BarCode> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
