package A3.subscription_service.kafka;

public class SubscriptionEventData {
    //id
    private long id;

    private String type; //can be gold/platium   the price for tickets is less for each upgrade

    private String expiry;

    public SubscriptionEventData(){}
    public SubscriptionEventData(Long id, String type, String expiry) {
        this.id = id;
        this.type = type;
        this.expiry = expiry;
    };

    //id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //type
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    //expiry
    public String getExpiry() {return expiry;}
    public void setExpiry(String expiry) {this.expiry = expiry;}

    @Override
    public String toString() {
        return "SubscriptionEventData{" +
                "id='" + getId() + '\'' +
                ", type=" + getType() +
                ", expiry='" + getExpiry() + '\'' +
                '}';
    }
}
