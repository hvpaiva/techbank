package hvpaiva.account.command.infrastructure;

public class CommandHandlerException extends RuntimeException {
    public CommandHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandHandlerException(String message) {
        super(message);
    }
}
