package A3.event_service.kafka;

public class BookingEventData {
    //id
    private long id;

    //event name
    private String name;

    //date
    private String date;

    //location
    private String location;

    //price
    private long ticketPrice;


    public BookingEventData() {
    };
    public BookingEventData(Long id, String name, String date, String location, Long ticketPrice) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.ticketPrice = ticketPrice;
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

    //date
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    //location
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    //price
    public long getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(long price) {
        this.ticketPrice = price;
    }

        @Override
    public String toString() {
        return "BookingEventData{" +
                "id='" + getId() + '\'' +
                ", name=" + getName() +
                ", date='" + getDate() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", ticketPrice='" + getTicketPrice() + '\'' +
                '}';
    }
}
