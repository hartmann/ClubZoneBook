package com.bekk.wa.webframework.database.service;

import java.util.List;

import com.bekk.wa.webframework.database.domain.Organizer;

public interface OrganizerService {

	public Organizer getOrganizer(long id);
	
	public List<Organizer> getOrganizers();
	
	public long addOrganizer(Organizer organizer);
	
	public void updateOrganizer(Organizer organizer);
	
	public void removeOrganizer(long id);
	
	
	
}
