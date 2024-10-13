package A3.user_service.models;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

//sent by attendee to organiser inbox
@Entity
public class AttendeeMessage {
    //id
    @Id
    @GeneratedValue
    private long id;

    //from  (attendee name)
    @Column
    private String fromAttendee;

    @Column
    private String date;

    @Column
    private String content;

    //to (organiser)
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "attendeeMessage_id")
    @JsonIgnore
    private Organiser toOrganiser;

    public AttendeeMessage() {};

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //attendee
    public String getFromAttendee() {
        return fromAttendee;
    }
    public void setFromAttendee(String from) {
        this.fromAttendee = from;
    }

    //organiser
    public Organiser getToOrganiser() {
        return toOrganiser;
    }
    public void setToOrganiser(Organiser organiser) {
        this.toOrganiser = organiser;
    }

    //date
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //content
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
