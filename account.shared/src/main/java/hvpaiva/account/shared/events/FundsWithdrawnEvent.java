package hvpaiva.account.shared.events;

import hvpaiva.cqrs.core.events.BaseEvent;

import java.time.Instant;

public class FundsWithdrawnEvent extends BaseEvent {
    private final double amount;

    public FundsWithdrawnEvent(String id, int version, Instant occurredOn, double amount) {
        super(id, version, occurredOn);
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}
