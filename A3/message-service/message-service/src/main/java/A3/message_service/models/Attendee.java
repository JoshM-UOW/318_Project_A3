package A3.message_service.models;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Attendee {
    //id
    @Id
    @GeneratedValue
    private long id;

    //first name
    @Column
    private String fname;

    //last name
    @Column
    private String lname;

    //email
    @Column
    private String email;

    //hold event key
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    //inbox
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "organiserMessage")
    private static ArrayList<OrganiserMessage> inbox = new ArrayList<OrganiserMessage>();

    //subscription
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "subscription_id")
    @JsonIgnore
    private Subscription subscription;


    public Attendee() {};

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //first name
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname = fname;
    }

    //last name
    public String getLname(){
        return lname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }

    //email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //event
    public Event getEvent(){
        return event;
    }
    public void setEvent(Event event){
        this.event = event;
    }

    //inbox
    public ArrayList<OrganiserMessage> getInbox(){
        return inbox;
    }
    public void addMessage(OrganiserMessage messageToAdd){
        inbox.add(messageToAdd);
    }
    public void removeMessage(OrganiserMessage messageToRemove){
        //iterates through each element in events. When a match is found, remove it
        Iterator itr = inbox.iterator();
        while (itr.hasNext()){
            OrganiserMessage temp = (OrganiserMessage) itr.next();
            if (temp.getId() == messageToRemove.getId()){
                System.out.println("REMOVED: messageToRemove.getId = " + messageToRemove.getId());
                itr.remove();
            }
        }
    }

    //subscription
    public Subscription getSubscription(){
        return subscription;
    }
    public void setSubscription(Subscription subscription){
        this.subscription = subscription;
    }
}
