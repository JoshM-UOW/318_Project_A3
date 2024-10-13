package A3.message_service.controllers;

import A3.message_service.controllers.dto.AttendeeMessageDTO;
import A3.message_service.models.Attendee;
import org.springframework.web.bind.annotation.*;
import A3.message_service.models.AttendeeMessage;
import A3.message_service.services.AttendeeMessageService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AttendeeMessageController {
    private final AttendeeMessageService attendeeMessageService;

    AttendeeMessageController(AttendeeMessageService attendeeMessageService) {
        this.attendeeMessageService = attendeeMessageService;
    }

    //return all attendees messages
    @GetMapping("/attendeeMessages")
    List<AttendeeMessageDTO> findAllAttendeeMessages() {
        return attendeeMessageService.findAllAttendeeMessages().stream()
            .map(attendeeMsg -> {
                AttendeeMessageDTO attendeeMessageDTO = new AttendeeMessageDTO();
                attendeeMessageDTO.setId(attendeeMsg.getId());
                attendeeMessageDTO.setFromAttendee(attendeeMsg.getFromAttendee());
                attendeeMessageDTO.setDate(attendeeMsg.getDate());
                attendeeMessageDTO.setContent(attendeeMsg.getContent());
                return attendeeMessageDTO;
            }).collect(Collectors.toList());
    }

    //return attendee message of X id
    @GetMapping("/attendeeMessages/{id}")
    AttendeeMessageDTO getAttendeeMessageById(@PathVariable Long id) {
        AttendeeMessageDTO attendeeMessageDTO = new AttendeeMessageDTO();
        AttendeeMessage attendeeMsg = attendeeMessageService.getAttendeeMessageById(id);
        attendeeMessageDTO.setId(attendeeMsg.getId());
        attendeeMessageDTO.setFromAttendee(attendeeMsg.getFromAttendee());
        attendeeMessageDTO.setDate(attendeeMsg.getDate());
        attendeeMessageDTO.setContent(attendeeMsg.getContent());
        return attendeeMessageDTO;
    }

    //create an attendee message
    @PostMapping("/attendeeMessages")
    AttendeeMessage createAttendeeMessage(@RequestBody AttendeeMessage newAttendeeMessage) {
        return attendeeMessageService.createAttendeeMessage(newAttendeeMessage);
    }
}
