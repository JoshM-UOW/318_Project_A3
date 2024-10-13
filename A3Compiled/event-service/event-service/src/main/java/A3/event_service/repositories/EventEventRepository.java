package A3.event_service.repositories;

import A3.event_service.models.events.EventEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventEventRepository extends JpaRepository<EventEvent, Long> {
}
