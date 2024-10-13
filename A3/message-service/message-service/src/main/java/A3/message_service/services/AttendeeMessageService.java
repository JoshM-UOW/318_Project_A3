package A3.message_service.services;

import A3.message_service.models.AttendeeMessage;
import A3.message_service.models.events.MessageEvent;
import A3.message_service.repositories.AttendeeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeMessageService {
    private final AttendeeMessageRepository attendeeMessageRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    AttendeeMessageService(AttendeeMessageRepository attendeeMessageRepository) {
        this.attendeeMessageRepository = attendeeMessageRepository;
    }

    //return all attendees messages
    public List<AttendeeMessage> findAllAttendeeMessages() {
        return attendeeMessageRepository.findAll();
    }

    //return attendee message of X id
    public AttendeeMessage getAttendeeMessageById(Long id) {
        return attendeeMessageRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    //create an attendee message
    public AttendeeMessage createAttendeeMessage(AttendeeMessage newAttendeeMessage) {
        //save event
        eventPublisher.publishEvent(new MessageEvent("Create AttendeeMsg", newAttendeeMessage.getFromAttendee(), newAttendeeMessage.getDate(), newAttendeeMessage.getContent(), "attendeeMsg"));

        return attendeeMessageRepository.save(newAttendeeMessage);
    }
}
