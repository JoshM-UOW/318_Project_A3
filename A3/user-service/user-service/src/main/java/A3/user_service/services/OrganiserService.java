package A3.user_service.services;

import A3.user_service.models.AttendeeMessage;
import A3.user_service.models.Event;
import A3.user_service.models.Organiser;
import A3.user_service.models.events.UserEvent;
import A3.user_service.repositories.OrganiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrganiserService {
    private final OrganiserRepository organiserRepository;
    private final RestTemplate restTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    OrganiserService(OrganiserRepository organiserRepository, RestTemplate restTemplate) {
        this.organiserRepository = organiserRepository;
        this.restTemplate = restTemplate;
    }

    //return all organisers
    public List<Organiser> findAllOrganisers() {
        return organiserRepository.findAll();
    }

    //return organiser of X id
    public Organiser getOrganisersById(Long id) {
        return organiserRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    //create organiser
    public Organiser saveOrganiser(Organiser newOrganiser) {
        //save event
        eventPublisher.publishEvent(new UserEvent("Create Organiser", newOrganiser.getName(), newOrganiser.getContactInfo(), "organiser"));

        return organiserRepository.save(newOrganiser);
    }

    //add an event
    //need to access event from event-service
    public Organiser updateOrganiserEvents(Long id, Long eventId) {
        Organiser organiser = organiserRepository.findById(id).orElseThrow(RuntimeException::new);
        Event event = getEvent(eventId);
        organiser.addEvent(event);
        event.setOrganiser(organiser);
        //save event
        eventPublisher.publishEvent(new UserEvent("Add event id(" + eventId + ") to Organiser id(" + id + ")", organiser.getName(), organiser.getContactInfo(), "organiser"));

        return organiserRepository.save(organiser);
    }

    //remove an event
    //need to access event from event-service
    public Organiser removeOrganiserEvent(Long id, Long eventId) {
        Organiser organiser = organiserRepository.findById(id).orElseThrow(RuntimeException::new);
        Event event = getEvent(eventId);
        //remove event from organiser
        organiser.removeEvent(event);
        //remove organiser from event
        event.setOrganiser(null);
        //save event
        eventPublisher.publishEvent(new UserEvent("Remove event id(" + eventId + ") from Organiser id(" + id + ")", organiser.getName(), organiser.getContactInfo(), "organiser"));

        return organiserRepository.save(organiser);
    }

    //add an attendee message
    //need to access attendeeMsg from message-service
    public Organiser updateOrganiserInbox(Long id, Long attendeeMessageId) {
        AttendeeMessage attendeeMessage = getAttendeeMessage(attendeeMessageId);
        Organiser organiser = organiserRepository.findById(id).orElseThrow(RuntimeException::new);
        attendeeMessage.setToOrganiser(organiser);
        organiser.addMessage(attendeeMessage);
        //save event
        eventPublisher.publishEvent(new UserEvent("Add attendeeMsg id(" + attendeeMessageId + ") to Organiser id(" + id + ") inbox", organiser.getName(), organiser.getContactInfo(), "organiser"));

        return organiserRepository.save(organiser);
    }

    //remove an attendee message
    //need to access attendeeMsg from message-service
    public Organiser removeOrganiserAttendeeMessage(Long id, Long attendeeMessageId) {
        AttendeeMessage attendeeMessage = getAttendeeMessage(attendeeMessageId);
        Organiser organiser = organiserRepository.findById(id).orElseThrow(RuntimeException::new);
        organiser.removeMessage(attendeeMessage);
        attendeeMessage.setToOrganiser(null);
        //save event
        eventPublisher.publishEvent(new UserEvent("Remove attendeeMsg id(" + attendeeMessageId + ") from Organiser id(" + id + ") inbox", organiser.getName(), organiser.getContactInfo(), "organiser"));

        return organiserRepository.save(organiser);
    }

    //endpoint to attendeeMsg. Call this function to GET the attendeeMsg, which can then be added/removed
    public AttendeeMessage getAttendeeMessage(Long id) {
        final String url = "http://localhost:8081/attendeeMessages/";
        //temp attendeeMsg
        AttendeeMessage toReturn;
        //attendeeMsg = message from message-service repository
        toReturn = restTemplate.getForObject(url + id, AttendeeMessage.class);
        return toReturn;
    }

    //endpoint to event. Call this function to GET the event, which can then be added/removed
    public Event getEvent(Long id) {
        // WHAT port actually is:
        final String url = "http://localhost:8082/events/";
        //final String url = "http://localhost:8080/events/";
        //temp event
        Event toReturn;
        //event = event from event-service repository
        toReturn = restTemplate.getForObject(url + id, Event.class);
        return toReturn;
    }
}
