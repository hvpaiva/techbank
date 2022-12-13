package hvpaiva.cqrs.core.events;

import java.util.List;

public interface EventStore {
    void save(String aggregateIdentifier, Iterable<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getEvents(String aggregateIdentifier);
}
