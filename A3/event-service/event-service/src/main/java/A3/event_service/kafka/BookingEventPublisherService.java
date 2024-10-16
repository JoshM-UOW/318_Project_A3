package A3.event_service.kafka;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class BookingEventPublisherService {
    private final StreamBridge streamBridge;

    public BookingEventPublisherService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @TransactionalEventListener
    public void handleBookingEvent(BookingEvent bookingEvent) {
        streamBridge.send("bookingEventChannel", bookingEvent);
    }
}
