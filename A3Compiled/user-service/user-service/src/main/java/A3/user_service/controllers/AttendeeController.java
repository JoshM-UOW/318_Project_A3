package A3.user_service.controllers;

import A3.user_service.services.AttendeeService;
import org.springframework.web.bind.annotation.*;
import A3.user_service.models.Attendee;
import java.util.List;
import java.util.stream.Collectors;

import A3.user_service.controllers.dto.AttendeeDTO;

@RestController
public class AttendeeController {
    //service
    private final AttendeeService attendeeService;

    AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    //return all attendees
    @GetMapping("/attendees")
    List<AttendeeDTO> findAllAttendees() {
        return attendeeService.getAllAttendees().stream()
            .map(attendee -> {
                AttendeeDTO attendeeDTO = new AttendeeDTO();
                attendeeDTO.setId(attendee.getId());
                attendeeDTO.setFname(attendee.getFname());
                attendeeDTO.setLname(attendee.getLname());
                attendeeDTO.setEmail(attendee.getEmail());
                attendeeDTO.setInbox(attendee.getInbox());
                return attendeeDTO;
            }).collect(Collectors.toList());
    }

    //return attendee of X id
    @GetMapping("/attendees/{id}")
    AttendeeDTO getAttendeeById(@PathVariable Long id) {
        AttendeeDTO attendeeDTO = new AttendeeDTO();
        Attendee attendee = attendeeService.getAttendee(id);
        attendeeDTO.setId(attendee.getId());
        attendeeDTO.setFname(attendee.getFname());
        attendeeDTO.setLname(attendee.getLname());
        attendeeDTO.setEmail(attendee.getEmail());
        attendeeDTO.setInbox(attendee.getInbox());
        return attendeeDTO;
    }

    //create an attendee
    @PostMapping("/attendees")
    Attendee createAttendee(@RequestBody Attendee newAttendee) {
        return attendeeService.saveAttendee(newAttendee);
    }

    //put request to add an organiser message
    @PutMapping("/attendees/{id}/inbox/{organiserMessageId}")
    Attendee updateAttendeeInbox(@PathVariable Long id, @PathVariable Long organiserMessageId) {
        return attendeeService.updateAttendeeInbox(id, organiserMessageId);
    }

    //put request to remove an organiser message
    @PutMapping("/attendees/{id}/inbox/{organiserMessageId}/remove")
    Attendee removeAttendeeOrganiserMessage(@PathVariable Long id, @PathVariable Long organiserMessageId) {
        return attendeeService.removeAttendeeOrganiserMessage(id, organiserMessageId);
    }
}
