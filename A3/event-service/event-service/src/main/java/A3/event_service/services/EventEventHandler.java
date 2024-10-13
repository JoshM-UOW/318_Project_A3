package A3.event_service.services;

import A3.event_service.models.events.EventEvent;
import A3.event_service.repositories.EventEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventEventHandler {
    private final EventEventRepository eventEventRepository;

    EventEventHandler(EventEventRepository eventEventRepository) {
        this.eventEventRepository = eventEventRepository;
    }

    @EventListener
    public void handleEventEvent(EventEvent eventEvent){
        eventEventRepository.save(eventEvent);
        System.out.println(eventEvent);
    }
}