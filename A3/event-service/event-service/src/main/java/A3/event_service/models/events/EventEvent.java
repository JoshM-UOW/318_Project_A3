package A3.event_service.models.events;

import jakarta.persistence.*;

@Entity
public class EventEvent {
    @Id
    @GeneratedValue
    private long id;

    //event name
    @Column
    private String eventName;

    //class "event" name
    @Column
    private String name;

    //date
    @Column
    private String date;

    //location
    @Column
    private String location;

    public EventEvent(String eventName, String name, String date, String location){
        this.eventName = eventName;
        this.name = name;
        this.date = date;
        this.location = location;
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

    //name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //date
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    //location
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public String toString() {
        return "EventEvent{" +
                "event_name='" + getEventName() + '\'' +
                ", name='" + getName() + '\'' +
                ", date='" + getDate() + '\'' +
                ", location=" + getLocation() +
                '}';
    }
}
