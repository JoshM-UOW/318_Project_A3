package A3.user_service.controllers;

import org.springframework.web.bind.annotation.*;
import A3.user_service.models.Organiser;
import A3.user_service.services.OrganiserService;
import java.util.List;

@RestController
public class OrganiserController {
    private final OrganiserService organiserService;

    OrganiserController(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    //return all organisers
    @GetMapping("/organisers")
    List<Organiser> findAllOrganisers() {
        return organiserService.findAllOrganisers();
    }

    //return organiser of X id
    @GetMapping("/organisers/{id}")
    Organiser getOrganisersById(@PathVariable Long id) {
        return organiserService.getOrganisersById(id);
    }

    //create organiser
    @PostMapping("/organisers")
    Organiser createOrganiser(@RequestBody Organiser newOrganiser) {
        return organiserService.saveOrganiser(newOrganiser);
    }

    //put request to add an event
    @PutMapping("/organisers/{id}/events/{eventId}")
    Organiser updateOrganiserEvents(@PathVariable Long id, @PathVariable Long eventId) {
        return organiserService.updateOrganiserEvents(id, eventId);
    }

    //put request to remove an event
    @PutMapping("/organisers/{id}/events/{eventId}/remove")
    Organiser removeOrganiserEvent(@PathVariable Long id, @PathVariable Long eventId) {
        return organiserService.removeOrganiserEvent(id, eventId);
    }

    //put request to add an attendee message
    @PutMapping("/organisers/{id}/inbox/{attendeeMessageId}")
    Organiser updateOrganiserInbox(@PathVariable Long id, @PathVariable Long attendeeMessageId) {
        return organiserService.updateOrganiserInbox(id, attendeeMessageId);
    }

    //put request to remove an attendee message
    @PutMapping("/organisers/{id}/inbox/{attendeeMessageId}/remove")
    Organiser removeOrganiserAttendeeMessage(@PathVariable Long id, @PathVariable Long attendeeMessageId) {
        return organiserService.removeOrganiserAttendeeMessage(id, attendeeMessageId);
    }
}
