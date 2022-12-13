package hvpaiva.cqrs.core.commands;

public interface CommandDispatcher {
    void registerHandler(Class<Command> commandType, CommandHandlerMethod handler);

    void dispatch(Command command);
}
