package fellipe.com.pass_in.controllers;

import fellipe.com.pass_in.dto.attendee.AttendeeBadgeResponseDTO;
import fellipe.com.pass_in.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity<Void> registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        this.attendeeService.checkInAttendee(attendeeId);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

        return ResponseEntity.created(uri).build();
    }
}
