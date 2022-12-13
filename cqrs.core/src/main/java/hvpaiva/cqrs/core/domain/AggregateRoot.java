package hvpaiva.cqrs.core.domain;

import hvpaiva.cqrs.core.events.BaseEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<BaseEvent> uncommittedChanges = new ArrayList<>();
    protected String id;
    protected int version;

    protected AggregateRoot() {
    }

    public String id() {
        return id;
    }

    public int version() {
        return version;
    }

    public List<BaseEvent> uncommittedChanges() {
        return uncommittedChanges;
    }

    public void commitChanges() {
        uncommittedChanges.clear();
    }

    // TODO: Not liking this.
    public void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            var method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);

            method.invoke(this, event);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (isNewEvent) {
                uncommittedChanges.add(event);
            }
        }
    }


    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvent(BaseEvent event) {
        applyChange(event, false);
    }
}
