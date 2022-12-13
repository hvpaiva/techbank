package hvpaiva.account.command.infrastructure;

import hvpaiva.cqrs.core.events.BaseEvent;
import hvpaiva.cqrs.core.events.EventModel;
import hvpaiva.cqrs.core.events.EventStore;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccountEventStore implements EventStore {
    private final EventStoreRepository eventStoreRepository;

    public AccountEventStore(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public void save(String aggregateIdentifier, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateIdentifier);

        if (eventStream.get(eventStream.size() - 1).version() != expectedVersion && expectedVersion != -1) {
            throw new ConcurrencyFailureException("Concurrency error");
        }

        var version = expectedVersion;

        for (var event : events) {
            var eventModel = new EventModel();
            eventModel.setAggregateIdentifier(aggregateIdentifier);
            eventModel.setAggregateType("Account");
            eventModel.setTimestamp(Instant.now());
            eventModel.setEventType(event.getClass().getName());
            eventModel.setEventPayload(event);
            eventModel.setVersion(version++);

            var persisted = eventStoreRepository.save(eventModel);

            if (persisted != null) {
                // TODO: Publish event to Kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateIdentifier) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateIdentifier);

        if (eventStream == null || eventStream.isEmpty()) {
            throw new IllegalArgumentException("Aggregate not found");
        }

        return eventStream.stream().map(EventModel::eventPayload).toList();
    }
}
