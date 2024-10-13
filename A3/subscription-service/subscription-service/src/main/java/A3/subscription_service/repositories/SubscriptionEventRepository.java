package A3.subscription_service.repositories;

import A3.subscription_service.models.events.SubscriptionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionEventRepository extends JpaRepository<SubscriptionEvent, Long> {
}
