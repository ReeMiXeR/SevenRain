package raduga.duga.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Teacher on 15.09.2016.
 */
public class BarCode extends RealmObject {

    @PrimaryKey
    private int     eventId;
    private String  eventName;
    private String  dateBegin;
    private String  dateEnd;
    private Boolean letIn;
    private Boolean letOut;
    private String  lastDate;
    private int     lastId;
    private String  denidName;

    public int getEventId(){
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

    public Boolean getLetIn() {
        return letIn;
    }

    public Boolean getLetOut() {
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

    public void setEventId(int eventId) {
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

    public void setLetIn(Boolean letIn) {
        this.letIn = letIn;
    }

    public void setLetOut(Boolean letOut) {
        this.letOut = letOut;
    }

    public void setDenidName(String denidName) {
        this.denidName = denidName;
    }
}
