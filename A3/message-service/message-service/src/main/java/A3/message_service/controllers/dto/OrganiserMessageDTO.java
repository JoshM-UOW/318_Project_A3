package A3.message_service.controllers.dto;

public class OrganiserMessageDTO {
    //id
    private long id;

    //from  (organiser name)
    private String fromOrganiser;

    private String date;

    private String content;

    public OrganiserMessageDTO() {};

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
