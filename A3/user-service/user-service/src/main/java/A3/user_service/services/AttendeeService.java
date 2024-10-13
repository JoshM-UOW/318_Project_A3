package A3.user_service.services;

import A3.user_service.models.OrganiserMessage;
import A3.user_service.models.events.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;
import A3.user_service.models.Attendee;
import A3.user_service.repositories.AttendeeRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    private final RestTemplate restTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AttendeeService(AttendeeRepository attendeeRepository, RestTemplate restTemplate) {
        this.attendeeRepository = attendeeRepository;
        this.restTemplate = restTemplate;
    }

    //return all
    public List<Attendee> getAllAttendees(){
        return attendeeRepository.findAll();
    }

    //return one
    public Attendee getAttendee(long id) {
        return attendeeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    //save one
    public Attendee saveAttendee(Attendee newAttendee) {
        //save event
        eventPublisher.publishEvent(new UserEvent("Create Attendee", newAttendee.getFname()+" "+newAttendee.getLname(), newAttendee.getEmail(), "attendee"));

        return attendeeRepository.save(newAttendee);
    }

    //add message to inbox
    //need to access orgMsg from message-service
    public Attendee updateAttendeeInbox(Long id, Long organiserMessageId) {
        OrganiserMessage organiserMessage = getOrganiserMessage(organiserMessageId);
        Attendee attendee = attendeeRepository.findById(id).orElseThrow(RuntimeException::new);
        organiserMessage.setToAttendee(attendee);
        attendee.addMessage(organiserMessage);
        //save event
        eventPublisher.publishEvent(new UserEvent("Add orgMsg id(" + organiserMessageId + ") to Attendee id(" + id + ") inbox", attendee.getFname()+" "+attendee.getLname(), attendee.getEmail(), "attendee"));

        return attendeeRepository.save(attendee);
    }

    //remove message from inbox
    //need to access orgMsg from message-service
    public Attendee removeAttendeeOrganiserMessage(Long id, Long organiserMessageId) {
        OrganiserMessage organiserMessage = getOrganiserMessage(organiserMessageId);
        Attendee attendee = attendeeRepository.findById(id).orElseThrow(RuntimeException::new);
        attendee.removeMessage(organiserMessage);
        organiserMessage.setToAttendee(null);
        //save event
        eventPublisher.publishEvent(new UserEvent("Remove orgMsg id(" + organiserMessageId + ") from Attendee id(" + id + ") inbox", attendee.getFname()+" "+attendee.getLname(), attendee.getEmail(), "attendee"));

        return attendeeRepository.save(attendee);
    }

    //endpoint to orgMsg. Call this function to GET the orgMsg, which can then be added/removed
    public OrganiserMessage getOrganiserMessage(Long id) {
        final String url = "http://localhost:8081/organiserMessages/";
        //temp orgMsg
        OrganiserMessage toReturn;
        //orgMsg = message from message-service repository
        toReturn = restTemplate.getForObject(url + id, OrganiserMessage.class);
        return toReturn;
    }
}
