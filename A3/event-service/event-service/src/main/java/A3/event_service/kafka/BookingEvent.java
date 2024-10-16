package A3.event_service.kafka;

public class BookingEvent {
    BookingEventData bookingEventData;
    public BookingEvent(){}
    public BookingEvent(BookingEventData bookingEventData){
        this.bookingEventData = bookingEventData;
    }

    public void setBookingEventData(BookingEventData bookingEventData){this.bookingEventData = bookingEventData;}
    public BookingEventData getBookingEventData(){
        return bookingEventData;
    }

    @Override
    public String toString() {
        return "BookingEvent{" +
                "bookingEventData =" + bookingEventData +
                '}';
    }
}
