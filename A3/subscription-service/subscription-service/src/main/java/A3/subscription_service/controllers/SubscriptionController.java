package A3.subscription_service.controllers;

import A3.subscription_service.controllers.dto.SubscriptionDTO;
import A3.subscription_service.models.Subscription;
import A3.subscription_service.services.SubscriptionService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    //return all subs
    @GetMapping("/subscriptions")
    @ResponseBody
    List<SubscriptionDTO> findAllSubscriptions() {
        return subscriptionService.findAllSubscriptions().stream()
                .map(subscription -> {
                    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
                    subscriptionDTO.setId(subscription.getId());
                    subscriptionDTO.setType(subscription.getType());
                    subscriptionDTO.setExpiry(subscription.getExpiry());
                    subscriptionDTO.setAttendees(subscription.getAttendees());
                    return subscriptionDTO;
                }).collect(Collectors.toList());
    }

    //return subs
    @GetMapping("/subscriptions/{id}")
    @ResponseBody
    SubscriptionDTO getSubscriptionById(@PathVariable Long id) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setType(subscription.getType());
        subscriptionDTO.setExpiry(subscription.getExpiry());
        subscriptionDTO.setAttendees(subscription.getAttendees());
        return subscriptionDTO;
    }

    //create a sub
    @PostMapping("/subscriptions")
    @ResponseBody
    String createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    //put request to add an attendee
    @PutMapping("/subscriptions/{id}/attendees/{attendeeId}")
    Subscription updateSubscriptionAttendees(@PathVariable Long id, @PathVariable Long attendeeId) {
        return subscriptionService.updateSubscriptionAttendees(id, attendeeId);
    }

    //put request to remove an attendee
    @PutMapping("/subscriptions/{id}/attendees/{attendeeId}/remove")
    Subscription removeSubscriptionAttendee(@PathVariable Long id, @PathVariable Long attendeeId) {
        return subscriptionService.removeSubscriptionAttendee(id, attendeeId);
    }

    //edit sub details
    @PutMapping("/subscriptions/{id}/edit/{type},{expiry}")
    Subscription editDetails(@PathVariable Long id, @PathVariable String type, @PathVariable String expiry) {
        return subscriptionService.editSubscription(id, type, expiry);
    }
}
