package A3.message_service.repositories;

import A3.message_service.models.OrganiserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganiserMessageRepository extends JpaRepository<OrganiserMessage, Long> {
}