package A3.message_service.repositories;

import A3.message_service.models.events.MessageEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEventRepository extends JpaRepository<MessageEvent, Long> {
}
