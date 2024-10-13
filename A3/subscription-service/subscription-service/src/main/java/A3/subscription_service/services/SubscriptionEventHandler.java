package A3.subscription_service.services;

import A3.subscription_service.models.events.SubscriptionEvent;
import A3.subscription_service.repositories.SubscriptionEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventHandler {
    private final SubscriptionEventRepository subscriptionEventRepository;

    SubscriptionEventHandler(SubscriptionEventRepository subscriptionEventRepository) {
        this.subscriptionEventRepository = subscriptionEventRepository;
    }

    @EventListener
    public void handleSubscriptionEvent(SubscriptionEvent subscriptionEvent){
        subscriptionEventRepository.save(subscriptionEvent);
        System.out.println(subscriptionEvent);
    }
}
