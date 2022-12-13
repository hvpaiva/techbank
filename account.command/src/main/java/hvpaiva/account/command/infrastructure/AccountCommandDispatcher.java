package hvpaiva.account.command.infrastructure;

import hvpaiva.cqrs.core.commands.Command;
import hvpaiva.cqrs.core.commands.CommandDispatcher;
import hvpaiva.cqrs.core.commands.CommandHandlerMethod;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<Command>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public void registerHandler(Class<Command> commandType, CommandHandlerMethod handler) {
        var handlers = routes.computeIfAbsent(commandType, k -> new LinkedList<>());

        handlers.add(handler);
    }

    @Override
    public void dispatch(Command command) {
        var handlers = routes.get(command.getClass());

        if (handlers == null) {
            throw new CommandHandlerException("No handlers registered for command " + command.getClass().getName());
        }

        if (handlers.size() != 1) {
            throw new CommandHandlerException("Multiple handlers registered for command " + command.getClass().getName());
        }

        handlers.get(0).handle(command);
    }
}
