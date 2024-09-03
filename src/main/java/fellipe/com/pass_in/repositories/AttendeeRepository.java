package fellipe.com.pass_in.repositories;

import fellipe.com.pass_in.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, String> {
    List<Attendee> findByEventId(String eventId);
    Optional<Attendee> findByEventIdAndEmail(String eventId, String Email);
}
