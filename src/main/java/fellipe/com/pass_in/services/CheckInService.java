package fellipe.com.pass_in.services;

import fellipe.com.pass_in.domain.attendee.Attendee;
import fellipe.com.pass_in.domain.checkIn.CheckIn;
import fellipe.com.pass_in.domain.checkIn.exceptions.CheckInAlreadyExistsException;
import fellipe.com.pass_in.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkInRepository.save(newCheckIn);
    }

    public Optional<CheckIn> getCheckIn(String attendeeId) {
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);

        if (isCheckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in");
    }
}
