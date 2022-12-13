package hvpaiva.account.command.api.commands;

import hvpaiva.account.shared.dtos.AccountType;
import hvpaiva.cqrs.core.commands.BaseCommand;

import java.math.BigDecimal;

public class OpenAccountCommand extends BaseCommand {
    private final String accountHolder;
    private final AccountType accountType;
    private final BigDecimal initialBalance;

    public OpenAccountCommand(String id, String accountHolder, AccountType accountType, BigDecimal initialBalance) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
    }

    public String accountHolder() {
        return accountHolder;
    }

    public AccountType accountType() {
        return accountType;
    }

    public BigDecimal initialBalance() {
        return initialBalance;
    }
}
