package com.bekk.wa.webframework;

import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.service.EventService;
import org.apache.commons.lang.Validate;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 */
public class DetachableEventModel extends LoadableDetachableModel<Event> {
    private long id;
    private EventService eventService;

    public DetachableEventModel(Event event, EventService eventService) {
        super(event);
        id = event.getId();
        this.eventService = eventService;
    }

    protected Event load() {
        Validate.notNull(id, "Id cannot be null");
        return eventService.getEvent(id);
    }
}
