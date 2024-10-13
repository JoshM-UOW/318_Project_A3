package A3.subscription_service.services;

import A3.subscription_service.models.Subscription;
import A3.subscription_service.models.Attendee;
import A3.subscription_service.models.events.SubscriptionEvent;
import A3.subscription_service.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private final RestTemplate restTemplate;

    SubscriptionService(SubscriptionRepository subscriptionRepository, RestTemplate restTemplate) {
        this.subscriptionRepository = subscriptionRepository;
        this.restTemplate = restTemplate;
    }

    //return all attendees messages
    public List<Subscription> findAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    //return attendee message of X id
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    //create an attendee message
    public Subscription createSubscription(Subscription newSub) {
        //save event
        eventPublisher.publishEvent(new SubscriptionEvent("Create Subscription", newSub.getType(), newSub.getExpirary()));

        return subscriptionRepository.save(newSub);
    }


    //to add an attendee
    //need to access attendeeReposity from user-service
    public Subscription updateSubscriptionAttendees(Long id, Long attendeeId) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(RuntimeException::new);
        Attendee attendee = getAttendee(attendeeId);
        subscription.addAttendee(attendee);
        attendee.setSubscription(subscription);
        //save event
        eventPublisher.publishEvent(new SubscriptionEvent("Added Attendee id(" + attendeeId + ") to Sub id(" + id + ")", subscription.getType(), subscription.getExpirary()));

        return subscriptionRepository.save(subscription);
    }

    //remove an attendee
    //need to access attendeeReposity from user-service
    public Subscription removeSubscriptionAttendee(Long id, Long attendeeId) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(RuntimeException::new);
        Attendee attendee = getAttendee(attendeeId);
        subscription.removeAttendee(attendee);
        attendee.setSubscription(null);
        //save event
        eventPublisher.publishEvent(new SubscriptionEvent("Removed Attendee id(" + attendeeId + ") from Sub id(" + id + ")", subscription.getType(), subscription.getExpirary()));

        return subscriptionRepository.save(subscription);
    }

    //endpoint to attendees. Call this function to GET the attendees, which can then be added/removed
    public Attendee getAttendee(Long id) {
        final String url = "http://localhost:8080/attendees/";
        //temp attendee
        Attendee toReturn;
        //attendee = message from message-service repository
        toReturn = restTemplate.getForObject(url + id, Attendee.class);
        return toReturn;
    }

    //edit event
    public Subscription editSubscription(Long id, String type, String expirary){
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(RuntimeException::new);
        subscription.setType(type);
        subscription.setExpirary(expirary);
        //save event
        eventPublisher.publishEvent(new SubscriptionEvent("Edited Sub id(" + id + ")", subscription.getType(), subscription.getExpirary()));

        return subscriptionRepository.save(subscription);
    }
}
