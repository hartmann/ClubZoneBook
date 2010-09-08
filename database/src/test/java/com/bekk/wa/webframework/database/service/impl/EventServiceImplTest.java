package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.domain.Location;
import com.bekk.wa.webframework.database.domain.Organizer;
import com.bekk.wa.webframework.database.service.EventService;
import com.bekk.wa.webframework.database.utils.DbUnitTestBase;
import static org.junit.Assert.*;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class EventServiceImplTest extends DbUnitTestBase {

    private EventService eventService;

    public EventService getEventService() {
        return eventService;
    }

    @Resource
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Test
    public void testDeleteEvent() {
        Event event = eventService.getEvent(1);
        Long id = event.getId();
        eventService.removeEvent(id);
        assertTrue(null == eventService.getEvent(id));
    }

    @Test
    public void testAddEvent() {
        Event firstEvent = eventService.getEvents().iterator().next();
        Location location = firstEvent.getLocation();
        Organizer organizer = firstEvent.getOrganizer();

        Event event = new Event();
        event.setDescription("my desc");
        event.setName("my name");
        event.setOrganizer(organizer);
        event.setLocation(location);

        long newId = eventService.addEvent(event);
        Event eventFromDb = eventService.getEvent(newId);
        assertNotNull(eventFromDb);
        assertTrue(eventFromDb.getDescription().equals("my desc"));
    }

    @Test
    public void shouldGetAllEvents() {
        List<Event> events = eventService.getEvents();
        assertFalse(events.isEmpty());
    }

    @Override
    public String getInitializationDataSetPath() {
        return "events.db.xml";
    }

}
