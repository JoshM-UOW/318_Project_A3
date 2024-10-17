package A3.subscription_service.kafka;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class SubscriptionEventPublisherService {
    private final StreamBridge streamBridge;

    public SubscriptionEventPublisherService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @TransactionalEventListener
    public void handleSubscriptionEventKafka(SubscriptionEventKafka subscriptionEventKafka) {
        streamBridge.send("subscriptionEventChannel", subscriptionEventKafka);
    }
}
