package A3.subscription_service.models.events;

import jakarta.persistence.*;

@Entity
public class SubscriptionEvent {
    @Id
    @GeneratedValue
    private long id;

    //event name
    @Column
    private String eventName;

    @Column
    private String type;

    //date
    @Column
    private String expiry;

    public SubscriptionEvent(String eventName, String type, String expiry){
        this.eventName = eventName;
        this.type = type;
        this.expiry = expiry;
    }

    //id
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    //event name
    public String getEventName(){
        return eventName;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    //type
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    //expiry
    public String getExpiry(){
        return expiry;
    }
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }


    @Override
    public String toString() {
        return "EventEvent{" +
                "event_name='" + getEventName() + '\'' +
                ", type='" + getType() + '\'' +
                ", expiry='" + getExpiry() + '\'' +
                '}';
    }
}
