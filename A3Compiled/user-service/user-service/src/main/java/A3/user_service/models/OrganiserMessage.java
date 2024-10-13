package A3.user_service.models;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

//sent by organiser to attendee inbox
@Entity
public class OrganiserMessage {
    //id
    @Id
    @GeneratedValue
    private long id;

    //from  (organiser name)
    @Column
    private String fromOrganiser;

    @Column
    private String date;

    @Column
    private String content;

    //to (attendee)
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "organiserMessage_id")
    @JsonIgnore
    private Attendee toAttendee;

    public OrganiserMessage() {};

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //organiser
    public String getFromOrganiser() {
        return fromOrganiser;
    }
    public void setFromOrganiser(String from) {
        this.fromOrganiser = from;
    }

    //attendee
    public Attendee getToAttendee() {
        return toAttendee;
    }
    public void setToAttendee(Attendee attendee) {
        this.toAttendee = attendee;
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
