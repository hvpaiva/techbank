package hvpaiva.account.shared.events;

import hvpaiva.cqrs.core.events.BaseEvent;

import java.math.BigDecimal;

public class FundsWithdrawnEvent extends BaseEvent {
    private final BigDecimal amount;

    public FundsWithdrawnEvent(String id, int version, BigDecimal amount) {
        super(id, version);
        this.amount = amount;
    }

    public BigDecimal amount() {
        return amount;
    }
}
