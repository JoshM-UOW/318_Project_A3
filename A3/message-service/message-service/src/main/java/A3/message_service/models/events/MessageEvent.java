package A3.message_service.models.events;

import jakarta.persistence.*;

@Entity
public class MessageEvent {
    @Id
    @GeneratedValue
    private long id;

    //event name
    @Column
    private String eventName;

    //fromUser
    @Column
    private String fromUser;

    //date
    @Column
    private String date;

    //content
    @Column
    private String content;

    //message type (attendeeMsg / organiserMsg)
    @Column
    private String messageType;

    public MessageEvent(String eventName, String fromUser, String date, String content, String messageType){
        this.eventName = eventName;
        this.fromUser = fromUser;
        this.date = date;
        this.content = content;
        this.messageType = messageType;
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

    //fromUser
    public String getFromUser(){
        return fromUser;
    }
    public void getFromUser(String fromUser){
        this.fromUser = fromUser;
    }

    //date
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    //content
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    //message type
    public String getMessageType(){
        return messageType;
    }
    public void setMessageType(String messageType){
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "event_name='" + getEventName() + '\'' +
                ", from='" + getFromUser() + '\'' +
                ", date='" + getDate() + '\'' +
                ", content=" + getContent() + '\'' +
                ", message_type=" + getMessageType() +
                '}';
    }
}
