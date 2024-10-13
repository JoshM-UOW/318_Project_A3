package A3.message_service.repositories;

import A3.message_service.models.AttendeeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeMessageRepository extends JpaRepository<AttendeeMessage, Long> {
}
