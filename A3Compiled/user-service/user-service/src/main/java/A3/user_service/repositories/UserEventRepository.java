package A3.user_service.repositories;

import A3.user_service.models.events.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, Long>{
}