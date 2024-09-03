package fellipe.com.pass_in.config;

import fellipe.com.pass_in.domain.attendee.exceptions.AttendeeAlreadyRegisteredException;
import fellipe.com.pass_in.domain.attendee.exceptions.AttendeeNotFoundException;
import fellipe.com.pass_in.domain.checkIn.exceptions.CheckInAlreadyExistsException;
import fellipe.com.pass_in.domain.event.exceptions.EventFullException;
import fellipe.com.pass_in.domain.event.exceptions.EventNotFoundException;
import fellipe.com.pass_in.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Void> handleEventNotFound(EventNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventFull(EventFullException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<Void> handleAttendeeNotFound(AttendeeNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyRegisteredException.class)
    public ResponseEntity<Void> handleAttendeeAlreadyExists(AttendeeAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity<Void> handleCheckInAlreadyExists(CheckInAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
