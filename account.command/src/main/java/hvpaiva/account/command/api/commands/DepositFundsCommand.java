package hvpaiva.account.command.api.commands;

import hvpaiva.cqrs.core.commands.BaseCommand;

public class DepositFundsCommand extends BaseCommand {
    private final double amount;

    public DepositFundsCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}
