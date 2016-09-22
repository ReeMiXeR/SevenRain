package raduga.duga.realm;

import io.realm.Realm;
import raduga.duga.model.BarCodeServerDB;

/**
 * Created by Teacher on 15.09.2016.
 */
public class RealmController {
    private Realm mRealm;

    RealmController(Realm realm){
        this.mRealm = realm;
    }

    public BarCodeServerDB findBarCode(String s){
        return mRealm.where(BarCodeServerDB.class).equalTo("eventId", s).findFirst();
    }

            public boolean hasBarCode() {

            return !mRealm.allObjects(BarCodeServerDB.class).isEmpty();
        }


//
//        private static RealmController instance;
//        private final Realm realm;
//
//        public RealmController(Application application) {
//            realm = Realm.getDefaultInstance();
//        }
//
//        public static RealmController with(Fragment fragment) {
//
//            if (instance == null) {
//                instance = new RealmController(fragment.getActivity().getApplication());
//            }
//            return instance;
//        }
//
//        public static RealmController with(Activity activity) {
//
//            if (instance == null) {
//                instance = new RealmController(activity.getApplication());
//            }
//            return instance;
//        }
//
//        public static RealmController with(Application application) {
//
//            if (instance == null) {
//                instance = new RealmController(application);
//            }
//            return instance;
//        }
//
//        public static RealmController getInstance() {
//
//            return instance;
//        }
//
//        public Realm getRealm() {
//
//            return realm;
//        }
//
//        //Refresh the realm istance
//        public void refresh() {
//
//            realm.refresh();
//        }
//
//        //clear all objects from Book.class
//        public void clearAll() {
//
//            realm.beginTransaction();
//            realm.clear(BarCodeServerDB.class);
//            realm.commitTransaction();
//        }
//
//        //find all objects in the Book.class
//        public RealmResults<BarCodeServerDB> getBarCode() {
//
//            return realm.where(BarCodeServerDB.class).findAll();
//        }
//
//        //query a single item with the given id
//        public BarCodeServerDB getBarCode(String id) {
//
//            return realm.where(BarCodeServerDB.class).equalTo("eventId", id).findFirst();
//        }
//
//        //check if Book.class is empty
//        public boolean hasBarCode() {
//
//            return !realm.allObjects(BarCodeServerDB.class).isEmpty();
//        }
//
//        //query example
//        public RealmResults<BarCodeServerDB> queryedBooks() {
//
//            return realm.where(BarCodeServerDB.class)
//                    .contains("author", "Author 0")
//                    .or()
//                    .contains("title", "Realm")
//                    .findAll();
//
//        }
//

}
