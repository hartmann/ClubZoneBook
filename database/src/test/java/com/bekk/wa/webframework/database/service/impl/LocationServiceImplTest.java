package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.domain.Location;
import com.bekk.wa.webframework.database.service.LocationService;
import com.bekk.wa.webframework.database.utils.DbUnitTestBase;
import static org.junit.Assert.*;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class LocationServiceImplTest extends DbUnitTestBase {

    private LocationService locationService;

    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setDescription("my desc");
        location.setName("my name");
        long newId = locationService.addLocation(location);
        Location locationFromDB = locationService.getLocation(newId);
        assertTrue(locationFromDB.getDescription().equals("my desc"));
    }

    @Test
    public void testDeleteLocation() {
        Location location = locationService.getLocation(1);
        Long id = location.getId();
        locationService.removeLocation(id);
        assertTrue(null == locationService.getLocation(id));
    }

    @Test
    public void shouldUpdateLocation() {
        List<Location> locationList = null;
        locationList = locationService.getLocations();
        Location location = locationList.get(0);
        String locationOldName = location.getName();
        location.setName("New name");
        locationService.updateLocation(location);
        locationList = locationService.getLocations();
        String locationNewName = locationList.get(0).getName();
        assertNotNull(locationOldName);
        assertNotNull(locationNewName);
        assertEquals(locationNewName, "New name");
    }

    public String getInitializationDataSetPath() {
        return "events.db.xml";
    }

    @Resource
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }


}
