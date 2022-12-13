package hvpaiva.cqrs.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod {
    void handle(Command command);
}
