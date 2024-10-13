package A3.user_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Organiser {
    //id
    @Id
    @GeneratedValue
    private long id;

    //name
    @Column
    private String name;

    //contact
    @Column
    private String contactInfo;

    //events this person has organised (arraylist)
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "organiser")
    private static ArrayList<Event> events = new ArrayList<Event>();

    //inbox
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "attendeeMessage")
    private static ArrayList<AttendeeMessage> inbox = new ArrayList<AttendeeMessage>();



    public Organiser() {
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

    //contact
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    //events
    public ArrayList<Event> getEvents(){
        return events;
    }
    public void addEvent(Event eventToAdd){
        events.add(eventToAdd);
    }
    public void removeEvent(Event eventToRemove){
        //iterates through each element in events. When a match is found, remove it
        Iterator itr = events.iterator();
        while (itr.hasNext()){
            Event temp = (Event)itr.next();
            if (temp.getId() == eventToRemove.getId()){
                System.out.println("REMOVED: eventToRemove.getId = " + eventToRemove.getId());
                itr.remove();
            }
        }
    }

    //inbox
    public ArrayList<AttendeeMessage> getInbox(){
        return inbox;
    }
    public void addMessage(AttendeeMessage messageToAdd){
        inbox.add(messageToAdd);
    }
    public void removeMessage(AttendeeMessage messageToRemove){
        //iterates through each element in events. When a match is found, remove it
        Iterator itr = inbox.iterator();
        while (itr.hasNext()){
            AttendeeMessage temp = (AttendeeMessage) itr.next();
            if (temp.getId() == messageToRemove.getId()){
                System.out.println("REMOVED: messageToRemove.getId = " + messageToRemove.getId());
                itr.remove();
            }
        }
    }
}
