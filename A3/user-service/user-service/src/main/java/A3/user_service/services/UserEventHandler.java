package A3.user_service.services;

import A3.user_service.models.events.UserEvent;
import A3.user_service.repositories.UserEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventHandler {
    private final UserEventRepository userEventRepository;

    UserEventHandler(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    @EventListener
    public void handleUserEvent(UserEvent userEvent){
        userEventRepository.save(userEvent);
        System.out.println(userEvent);
    }
}
