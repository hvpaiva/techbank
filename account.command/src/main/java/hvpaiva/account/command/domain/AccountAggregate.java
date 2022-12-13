package hvpaiva.account.command.domain;

import hvpaiva.account.command.api.commands.OpenAccountCommand;
import hvpaiva.account.shared.events.AccountClosedEvent;
import hvpaiva.account.shared.events.AccountOpenedEvent;
import hvpaiva.account.shared.events.FundsDepositedEvent;
import hvpaiva.account.shared.events.FundsWithdrawnEvent;
import hvpaiva.cqrs.core.domain.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountAggregate extends AggregateRoot {
    private boolean active;
    private BigDecimal balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(new AccountOpenedEvent(
                command.id(),
                1,
                command.accountHolder(),
                command.accountType(),
                LocalDateTime.now(),
                command.initialBalance()
        ));
    }

    public void apply(AccountOpenedEvent event) {
        id = event.id();
        active = true;
        balance = event.initialBalance();
    }

    public void deposit(BigDecimal amount) {
        if (!active) {
            throw new IllegalStateException("Account is not active");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        raiseEvent(new FundsDepositedEvent(id, version, amount));
    }

    public void apply(FundsDepositedEvent event) {
        id = event.id();
        balance = balance.add(event.amount());
    }

    public void withdraw(BigDecimal amount) {
        if (!active) {
            throw new IllegalStateException("Account is not active");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        raiseEvent(new FundsWithdrawnEvent(id, version, amount));
    }

    public void apply(FundsWithdrawnEvent event) {
        id = event.id();
        balance = balance.subtract(event.amount());
    }

    public void close() {
        if (!active) {
            throw new IllegalStateException("Account is not active");
        }

        raiseEvent(new AccountClosedEvent(id, version));
    }

    public void apply(AccountClosedEvent event) {
        id = event.id();
        active = false;
    }
}
