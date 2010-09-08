package com.bekk.wa.webframework.database.service;

import java.util.List;

import com.bekk.wa.webframework.database.domain.Event;

public interface EventService {

	public Event getEvent(long id);
	
	public List<Event> getEvents();
	
	public long addEvent(Event event);
	
	public void updateEvent(Event event);
	
	public void removeEvent(long id);
	
	
}
