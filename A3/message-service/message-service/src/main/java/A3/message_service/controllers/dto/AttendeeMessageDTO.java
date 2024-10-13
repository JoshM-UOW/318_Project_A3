package A3.message_service.controllers.dto;

public class AttendeeMessageDTO {
    //id
    private long id;

    //from  (attendee name)
    private String fromAttendee;

    private String date;

    private String content;

    public AttendeeMessageDTO() {};

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
