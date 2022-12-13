package hvpaiva.account.shared.events;

import hvpaiva.account.shared.dtos.AccountType;
import hvpaiva.cqrs.core.events.BaseEvent;

import java.time.Instant;
import java.time.LocalDateTime;

public class AccountOpenedEvent extends BaseEvent {
    private final String accountHolder;
    private final AccountType accountType;
    private final LocalDateTime openedAt;
    private final double initialBalance;

    public AccountOpenedEvent(
            String id,
            int version,
            Instant occurredOn,
            String accountHolder,
            AccountType accountType,
            LocalDateTime openedAt,
            double initialBalance
    ) {
        super(id, version, occurredOn);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.openedAt = openedAt;
        this.initialBalance = initialBalance;
    }

    public String accountHolder() {
        return accountHolder;
    }

    public AccountType accountType() {
        return accountType;
    }

    public LocalDateTime openedAt() {
        return openedAt;
    }

    public double initialBalance() {
        return initialBalance;
    }
}
