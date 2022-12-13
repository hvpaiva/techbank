package hvpaiva.cqrs.core.commands;

import hvpaiva.cqrs.core.messages.Message;

public abstract class BaseCommand extends Message {
    protected BaseCommand(String id) {
        super(id);
    }
}
