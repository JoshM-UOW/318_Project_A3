package A3.subscription_service.controllers.dto;

import A3.subscription_service.models.Attendee;
import java.util.ArrayList;

public class SubscriptionDTO {
    //id
    private long id;

    private String type; //can be gold/platium   the price for tickets is less for each upgrade

    private String expiry;

    //given to (attendees)  list all attendees with a subscription
    private static ArrayList<Attendee> attendees = new ArrayList<Attendee>();

    public SubscriptionDTO() {};

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

    //expirary
    public String getExpiry() {return expiry;}
    public void setExpiry(String expiry) {this.expiry = expiry;}

    //attendees
    public ArrayList<Attendee> getAttendees(){
        return attendees;
    }
    public void setAttendees(ArrayList<Attendee> attendees){
        this.attendees = attendees;
    }
}
