package com.bekk.wa.webframework.database.service;

import java.util.List;

import com.bekk.wa.webframework.database.domain.Location;

public interface LocationService {

	public Location getLocation(long id);
	
	public List<Location> getLocations();
	
	public long addLocation(Location location);
	
	public void updateLocation(Location location);
	
	public void removeLocation(long id);
	
}
