package fellipe.com.pass_in.repositories;

import fellipe.com.pass_in.domain.checkIn.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
