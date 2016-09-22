package raduga.duga.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Teacher on 15.09.2016.
 */
public class BarCodeServerDB extends RealmObject {
    @PrimaryKey
    private String     eventId;

    private String  eventName;
    private String  dateBegin;
    private String  dateEnd;
    private int letIn;
    private int letOut;
    private String  lastDate;
    private int     lastId;
    private String  denidName;

    public BarCodeServerDB(){}



    public String getEventId(){
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public int getLetIn() {
        return letIn;
    }

    public int getLetOut() {
        return letOut;
    }

    public String getLastDate() {
        return lastDate;
    }

    public int getLastId() {
        return lastId;
    }

    public String getDenidName() {
        return denidName;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public void setLetIn(int letIn) {
        this.letIn = letIn;
    }

    public void setLetOut(int letOut) {
        this.letOut = letOut;
    }

    public void setDenidName(String denidName) {
        this.denidName = denidName;
    }

}
