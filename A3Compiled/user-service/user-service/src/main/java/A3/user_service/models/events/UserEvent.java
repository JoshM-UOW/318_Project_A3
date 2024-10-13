package A3.user_service.models.events;

import jakarta.persistence.*;

@Entity
public class UserEvent {
    @Id
    @GeneratedValue
    private long id;

    //event name
    @Column
    private String eventName;

    //username
    @Column
    private String fullName;

    //contact
    @Column
    private String contactInfo;

    @Column
    private String userType; //attendee or organiser

    public UserEvent(String eventName, String fullName, String contactInfo, String userType){
        this.eventName = eventName;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.userType = userType;
    }

    //id
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    //name
    public String getEventName(){
        return eventName;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    //name
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    //contact
    public String getContactInfo(){
        return contactInfo;
    }
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

    //user type
    public String getUserType(){
        return userType;
    }
    public void setUserType(String userType){
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "event_name='" + getEventName() + '\'' +
                ", full_name='" + getFullName() + '\'' +
                ", contact_info='" + getContactInfo() + '\'' +
                ", user_type=" + getUserType() +
                '}';
    }
}
