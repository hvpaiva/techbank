package hvpaiva.cqrs.core.events;

import hvpaiva.cqrs.core.messages.Message;

import java.time.Instant;

public abstract class BaseEvent extends Message {
    private final int version;
    private final Instant occurredOn;

    protected BaseEvent(String id, int version, Instant occurredOn) {
        super(id);
        this.version = version;
        this.occurredOn = occurredOn;
    }

    public int version() {
        return version;
    }

    public Instant occurredOn() {
        return occurredOn;
    }
}
