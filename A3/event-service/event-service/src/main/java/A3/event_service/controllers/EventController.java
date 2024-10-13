package A3.event_service.controllers;

import A3.event_service.controllers.dto.EventDTO;
import A3.event_service.models.AttendeeMessage;
import org.springframework.web.bind.annotation.*;
import A3.event_service.models.Event;
import A3.event_service.services.EventService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {
    private final EventService eventService;

    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //return all events
    @GetMapping("/events")
    List<EventDTO> findAllEvents() {
        return eventService.findAllEvents().stream()
                .map(event -> {
                    EventDTO eventDTO = new EventDTO();
                    eventDTO.setId(event.getId());
                    eventDTO.setName(event.getName());
                    eventDTO.setDate(event.getDate());
                    eventDTO.setLocation(event.getLocation());
                    eventDTO.setAttendees(event.getAttendees());
                    return eventDTO;
                }).collect(Collectors.toList());
    }

    //return event of X id
    @GetMapping("/events/{id}")
    EventDTO getEventById(@PathVariable Long id) {
        EventDTO eventDTO = new EventDTO();
        Event event = eventService.getEventById(id);
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setAttendees(event.getAttendees());
        return eventDTO;
    }

    //create event
    @PostMapping("/events")
    Event createEvent(@RequestBody Event newEvent) {
        return eventService.saveEvent(newEvent);
    }

    //put request to add an attendee
    @PutMapping("/events/{id}/attendees/{attendeeId}")
    Event updateEventAttendees(@PathVariable Long id, @PathVariable Long attendeeId) {
        return eventService.updateEventAttendees(id, attendeeId);
    }

    //put request to remove an attendee
    @PutMapping("/events/{id}/attendees/{attendeeId}/remove")
    Event removeEventAttendee(@PathVariable Long id, @PathVariable Long attendeeId) {
        return eventService.removeEventAttendee(id, attendeeId);
    }

    //edit event details
    @PutMapping("/events/{id}/edit/{name},{date},{location}")
    Event editDetails(@PathVariable Long id, @PathVariable String name, @PathVariable String date, @PathVariable String location) {
        return eventService.editEvent(id, name, date, location);
    }
}
