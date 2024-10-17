package A3.subscription_service.kafka;

public class SubscriptionEventKafka {
    SubscriptionEventData subscriptionEventData;
    public SubscriptionEventKafka(){}
    public SubscriptionEventKafka(SubscriptionEventData subscriptionEventData){
        this.subscriptionEventData = subscriptionEventData;
    }

    public void setBookingEventData(SubscriptionEventData subscriptionEventData){this.subscriptionEventData = subscriptionEventData;}
    public SubscriptionEventData getBookingEventData(){
        return subscriptionEventData;
    }

    @Override
    public String toString() {
        return "SubscriptionEvent (kafka){" +
                "subscriptionEventData =" + subscriptionEventData +
                '}';
    }
}
