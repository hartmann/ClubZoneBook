package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.dao.BaseDao;
import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.service.EventService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional
public class EventServiceImpl implements EventService {

    private BaseDao<Event, Long> eventDao;

    public EventServiceImpl() {
    }

    public BaseDao<Event, Long> getEventDao() {
        return eventDao;
    }

    @Resource
    public void setEventDao(BaseDao<Event, Long> eventDao) {
        this.eventDao = eventDao;
    }

    public long addEvent(Event event) {
        Event newEvent = eventDao.update(event);
        return newEvent.getId();
    }

    public Event getEvent(long id) {
        return eventDao.read(id);
    }

    public List<Event> getEvents() {
        return eventDao.find(new HashMap<String, Object>(), "name", true);
    }

    public void removeEvent(long id) {
        eventDao.delete(id);
    }

    public void updateEvent(Event event) {
        eventDao.update(event);
    }

}
