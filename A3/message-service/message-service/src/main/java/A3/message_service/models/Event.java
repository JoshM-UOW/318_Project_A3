package A3.message_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Event {

    //id
    @Id
    @GeneratedValue
    private long id;

    //event name
    @Column
    private String name;

    //date
    @Column
    private String date;

    //location
    @Column
    private String location;

    //price
    @Column
    private long ticketPrice;

    //arraylist for attendees
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "event")
    private static ArrayList<Attendee> attendees = new ArrayList<Attendee>();

    //hold organiser key
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "organiser_id")
    @JsonIgnore
    private Organiser organiser;

    public Event() {
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

    //price
    public long getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(long price) {
        this.ticketPrice = price;
    }

    //attendees
    public ArrayList<Attendee> getAttendees(){
        return attendees;
    }
    public void addAttendee(Attendee attendeeToAdd){
        attendees.add(attendeeToAdd);
    }
    public void removeAttendee(Attendee attendeeToRemove){
        //iterates through each element in events. When a match is found, remove it
        Iterator itr = attendees.iterator();
        while (itr.hasNext()){
            Attendee temp = (Attendee)itr.next();
            if (temp.getId() == attendeeToRemove.getId()){
                System.out.println("REMOVED: attendeeToRemove.getId = " + attendeeToRemove.getId());
                itr.remove();
            }
        }
    }

    //organiser
    public Organiser getOrganiser(){
        return organiser;
    }
    public void setOrganiser(Organiser organiser){
        this.organiser = organiser;
    }
}
