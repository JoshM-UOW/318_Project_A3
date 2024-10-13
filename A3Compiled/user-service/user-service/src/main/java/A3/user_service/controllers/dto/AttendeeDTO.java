package A3.user_service.controllers.dto;

import A3.user_service.models.OrganiserMessage;

import java.util.ArrayList;

public class AttendeeDTO {
    //id
    private long id;

    //first name
    private String fname;

    //last name
    private String lname;

    //email
    private String email;

    //inbox
    private static ArrayList<OrganiserMessage> inbox = new ArrayList<OrganiserMessage>();

    public AttendeeDTO() {};

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

    //inbox
    public ArrayList<OrganiserMessage> getInbox(){
        return inbox;
    }
    public void setInbox(ArrayList<OrganiserMessage> inbox){
        this.inbox = inbox;
    }
}
