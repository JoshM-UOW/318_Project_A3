package A3.message_service.services;

import A3.message_service.models.OrganiserMessage;
import A3.message_service.models.events.MessageEvent;
import A3.message_service.repositories.OrganiserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganiserMessageService {
    private final OrganiserMessageRepository organiserMessageRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    OrganiserMessageService(OrganiserMessageRepository organiserMessageRepository) {
        this.organiserMessageRepository = organiserMessageRepository;
    }

    //return all organiser messages
    public List<OrganiserMessage> findAllOrganiserMessages() {
        return organiserMessageRepository.findAll();
    }

    //return organiser message of X id
    public OrganiserMessage getOrganiserMessageById(Long id) {
        return organiserMessageRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    //create an attendee message
    public OrganiserMessage createOrganiserMessage(OrganiserMessage newOrganiserMessage) {
        //save event
        eventPublisher.publishEvent(new MessageEvent("Create OrganiserMsg", newOrganiserMessage.getFromOrganiser(), newOrganiserMessage.getDate(), newOrganiserMessage.getContent(), "organiserMsg"));

        return organiserMessageRepository.save(newOrganiserMessage);
    }
}
