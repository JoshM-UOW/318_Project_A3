package A3.message_service.controllers;

import A3.message_service.controllers.dto.OrganiserMessageDTO;
import A3.message_service.models.Attendee;
import A3.message_service.models.AttendeeMessage;
import org.springframework.web.bind.annotation.*;
import A3.message_service.models.OrganiserMessage;
import A3.message_service.services.OrganiserMessageService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrganiserMessageController {
    private final OrganiserMessageService organiserMessageService;

    OrganiserMessageController(OrganiserMessageService organiserMessageService) {
        this.organiserMessageService = organiserMessageService;
    }

    //return all organiser messages
    @GetMapping("/organiserMessages")
    List<OrganiserMessageDTO> findAllOrganiserMessages() {
        return organiserMessageService.findAllOrganiserMessages().stream()
                .map(organiserMessage -> {
                    OrganiserMessageDTO organiserMessageDTO = new OrganiserMessageDTO();
                    organiserMessageDTO.setId(organiserMessage.getId());
                    organiserMessageDTO.setFromOrganiser(organiserMessage.getFromOrganiser());
                    organiserMessageDTO.setDate(organiserMessage.getDate());
                    organiserMessageDTO.setContent(organiserMessage.getContent());
                    return organiserMessageDTO;
                }).collect(Collectors.toList());
    }

    //return organiser message of X id
    @GetMapping("/organiserMessages/{id}")
    OrganiserMessageDTO getOrganiserMessageById(@PathVariable Long id) {
        //return organiserMessageService.getOrganiserMessageById(id);
        OrganiserMessageDTO organiserMessageDTO = new OrganiserMessageDTO();
        OrganiserMessage organiserMessage = organiserMessageService.getOrganiserMessageById(id);
        organiserMessageDTO.setId(organiserMessage.getId());
        organiserMessageDTO.setFromOrganiser(organiserMessage.getFromOrganiser());
        organiserMessageDTO.setDate(organiserMessage.getDate());
        organiserMessageDTO.setContent(organiserMessage.getContent());
        return organiserMessageDTO;
    }

    //create an attendee message
    @PostMapping("/organiserMessages")
    OrganiserMessage createOrganiserMessage(@RequestBody OrganiserMessage newOrganiserMessage) {
        return organiserMessageService.createOrganiserMessage(newOrganiserMessage);
    }
}
