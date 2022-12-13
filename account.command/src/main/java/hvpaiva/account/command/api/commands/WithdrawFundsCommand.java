package hvpaiva.account.command.api.commands;

import hvpaiva.cqrs.core.commands.BaseCommand;

public class WithdrawFundsCommand extends BaseCommand {
    private final double amount;

    public WithdrawFundsCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}
