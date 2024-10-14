package A3.user_service.models;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Subscription {
    //id
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String type; //can be gold/platium   the price for tickets is less for each upgrade

    @Column
    private String expiry;

    //given to (attendees)  list all attendees with a subscription
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "subscription")
    private static ArrayList<Attendee> attendees = new ArrayList<Attendee>();

    public Subscription() {};

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //type
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    //expiry
    public String getExpiry() {return expiry;}
    public void setExpiry(String expiry) {this.expiry = expiry;}

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
}