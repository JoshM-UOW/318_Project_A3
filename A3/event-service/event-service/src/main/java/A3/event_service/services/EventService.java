package A3.event_service.services;

import A3.event_service.models.Attendee;
import A3.event_service.models.Event;
import A3.event_service.models.events.EventEvent;
import A3.event_service.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RestTemplate restTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    EventService(EventRepository eventRepository, RestTemplate restTemplate) {
        this.eventRepository = eventRepository;
        this.restTemplate = restTemplate;
    }

    //return all events
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    //return event of X id
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    //save event
    public String saveEvent(Event newEvent) {
        //save event
        eventPublisher.publishEvent(new EventEvent("Create Event", newEvent.getName(), newEvent.getDate(), newEvent.getLocation(), newEvent.getTicketPrice()));

        newEvent.completedObj(); //create kafka event
        eventRepository.save(newEvent);
        return "Event " + newEvent.getId() + " + Kafka BookingEvent saved!";
    }

    //to add an attendee
    //need to access attendeeReposity from user-service
    public Event updateEventAttendees(Long id, Long attendeeId) {
        Event event = eventRepository.findById(id).orElseThrow(RuntimeException::new);
        Attendee attendee = getAttendee(attendeeId);
        event.addAttendee(attendee);
        attendee.setEvent(event);
        //save event
        eventPublisher.publishEvent(new EventEvent("Added Attendee id(" + attendeeId + ") to Event id(" + id + ")", event.getName(), event.getDate(), event.getLocation(), event.getTicketPrice()));

        return eventRepository.save(event);
    }

    //remove an attendee
    //need to access attendeeReposity from user-service
    public Event removeEventAttendee(Long id, Long attendeeId) {
        Event event = eventRepository.findById(id).orElseThrow(RuntimeException::new);
        Attendee attendee = getAttendee(attendeeId);
        event.removeAttendee(attendee);
        attendee.setEvent(null);
        //save event
        eventPublisher.publishEvent(new EventEvent("Removed Attendee id(" + attendeeId + ") from Event id(" + id + ")", event.getName(), event.getDate(), event.getLocation(), event.getTicketPrice()));

        return eventRepository.save(event);
    }

    //endpoint to attendees. Call this function to GET the attendees, which can then be added/removed
    public Attendee getAttendee(Long id) {
        final String url = "http://localhost:8080/attendees/";
        //temp attendee
        Attendee toReturn;
        //attendee = message from message-service repository
        toReturn = restTemplate.getForObject(url + id, Attendee.class);
        return toReturn;
    }

    //edit event
    public Event editEvent(Long id, String name, String date, String location, Long price){
        Event event = eventRepository.findById(id).orElseThrow(RuntimeException::new);
        event.setName(name);
        event.setDate(date);
        event.setLocation(location);
        event.setTicketPrice(price);
        //save event
        eventPublisher.publishEvent(new EventEvent("Edited Event id(" + id + ")", event.getName(), event.getDate(), event.getLocation(), event.getTicketPrice()));

        return eventRepository.save(event);
    }
}
