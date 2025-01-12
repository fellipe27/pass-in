package fellipe.com.pass_in.controllers;

import fellipe.com.pass_in.dto.attendee.AttendeeIdDTO;
import fellipe.com.pass_in.dto.attendee.AttendeeRequestDTO;
import fellipe.com.pass_in.dto.attendee.AttendeesListResponseDTO;
import fellipe.com.pass_in.dto.event.EventIdDTO;
import fellipe.com.pass_in.dto.event.EventRequestDTO;
import fellipe.com.pass_in.dto.event.EventResponseDTO;
import fellipe.com.pass_in.services.AttendeeService;
import fellipe.com.pass_in.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventDetail(@PathVariable String id) {
        EventResponseDTO event = this.eventService.getEventDetail(id);

        return ResponseEntity.ok().body(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListResponseDTO attendeesListResponseDTOS = this.attendeeService.getEventsAttendee(id);

        return ResponseEntity.ok().body(attendeesListResponseDTOS);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }
}
