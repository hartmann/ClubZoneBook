package com.bekk.wa.webframework;

import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.service.EventService;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 */
public class SortableEventDataProvider extends SortableDataProvider<Event> {
    private EventService eventService;

    public SortableEventDataProvider(EventService eventService) {
        this.eventService = eventService;
    }

    public Iterator<? extends Event> iterator(int first, int count) {
        List<Event> events = eventService.getEvents();
        if (first > 0) {
            events = events.subList(first, first + count);
        }

        SortParam sp = getSort();

        if (sp != null) {
            final String sortColumn = sp.getProperty();
            Comparator<Event> comparator = new Comparator<Event>() {
                public int compare(Event e1, Event e2) {
                    if (sortColumn.equalsIgnoreCase("description")) {
                        return e1.getDescription().compareTo(e2.getDescription());
                    }
                    if (sortColumn.equalsIgnoreCase("location")) {
                        return e1.getLocation().getName().compareTo(e2.getLocation().getName());
                    }
                    if (sortColumn.equalsIgnoreCase("organizer")) {
                        return e1.getOrganizer().getName().compareTo(e2.getOrganizer().getName());
                    } else {
                        return e1.getId().compareTo(e2.getId());
                    }
                }
            };


            if (!sp.isAscending()) {
                comparator = new ReverseComparator(comparator);
            }

            Collections.sort(events, comparator);
        }

        return events.iterator();
    }

    public int size() {
        return eventService.getEvents().size();
    }

    public IModel<Event> model(Event event) {
        return new DetachableEventModel(event, eventService);
    }
}
