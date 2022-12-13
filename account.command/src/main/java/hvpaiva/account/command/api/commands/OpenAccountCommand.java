package hvpaiva.account.command.api.commands;

import hvpaiva.account.shared.dtos.AccountType;
import hvpaiva.cqrs.core.commands.BaseCommand;

public class OpenAccountCommand extends BaseCommand {
    private final String accountHolder;
    private final AccountType accountType;
    private final double initialBalance;

    public OpenAccountCommand(String id, String accountHolder, AccountType accountType, double initialBalance) {
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

    public double initialBalance() {
        return initialBalance;
    }
}
