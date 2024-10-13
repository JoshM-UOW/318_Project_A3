package A3.message_service.services;

import A3.message_service.models.events.MessageEvent;
import A3.message_service.repositories.MessageEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MessageEventHandler {
    private final MessageEventRepository messageEventRepository;

    MessageEventHandler(MessageEventRepository messageEventRepository) {
        this.messageEventRepository = messageEventRepository;
    }

    @EventListener
    public void handleMessageEvent(MessageEvent messageEvent){
        messageEventRepository.save(messageEvent);
        System.out.println(messageEvent);
    }
}
