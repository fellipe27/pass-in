package fellipe.com.pass_in.dto.event;

public record EventRequestDTO(
        String title,
        String details,
        Integer maximumAttendees
) {}
