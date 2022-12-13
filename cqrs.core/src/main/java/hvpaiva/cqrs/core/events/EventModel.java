package hvpaiva.cqrs.core.events;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "event_store")
public class EventModel {
    @Id
    private String id;
    private Instant timestamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventPayload;

    public EventModel() {
    }

    public String id() {
        return id;
    }

    public Instant occurredOn() {
        return timestamp;
    }

    public String aggregateIdentifier() {
        return aggregateIdentifier;
    }

    public String aggregateType() {
        return aggregateType;
    }

    public int version() {
        return version;
    }

    public String eventType() {
        return eventType;
    }

    public BaseEvent eventPayload() {
        return eventPayload;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setAggregateIdentifier(String aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventPayload(BaseEvent eventPayload) {
        this.eventPayload = eventPayload;
    }
}
