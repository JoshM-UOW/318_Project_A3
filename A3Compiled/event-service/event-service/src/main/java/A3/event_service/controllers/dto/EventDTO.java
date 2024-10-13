package A3.event_service.controllers.dto;

import A3.event_service.models.Attendee;
import java.util.ArrayList;

public class EventDTO {
    //id
    private long id;

    //event name
    private String name;

    //date
    private String date;

    //location
    private String location;

    //arraylist for attendees
    private static ArrayList<Attendee> attendees = new ArrayList<Attendee>();

    public EventDTO() {
    };

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
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

    //attendees
    public ArrayList<Attendee> getAttendees(){
        return attendees;
    }
    public void setAttendees(ArrayList<Attendee> attendees){
        this.attendees = attendees;
    }
}
