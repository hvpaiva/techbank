package hvpaiva.account.shared.events;

import hvpaiva.cqrs.core.events.BaseEvent;

import java.time.Instant;

public class AccountClosedEvent extends BaseEvent {
    public AccountClosedEvent(String id, int version, Instant occurredOn) {
        super(id, version, occurredOn);
    }
}
