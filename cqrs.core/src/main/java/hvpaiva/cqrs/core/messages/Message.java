package hvpaiva.cqrs.core.messages;

public abstract class Message {
    private final String id;

    protected Message(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
