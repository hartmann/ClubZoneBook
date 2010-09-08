package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.dao.BaseDao;
import com.bekk.wa.webframework.database.domain.Location;
import com.bekk.wa.webframework.database.service.LocationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional
public class LocationServiceImpl implements LocationService {

    private BaseDao<Location, Long> locationDao;


    public BaseDao<Location, Long> getLocationDao() {
        return locationDao;
    }

    @Resource
    public void setLocationDao(BaseDao<Location, Long> locationDao) {
        this.locationDao = locationDao;
    }


    public long addLocation(Location location) {
        Location newLocation = locationDao.update(location);
        return newLocation.getId();
    }

    public Location getLocation(long id) {
        return locationDao.read(id);
    }

    public List<Location> getLocations() {
        return locationDao.find(new HashMap<String, Object>(), "name", true);
    }


    public void removeLocation(long id) {
        locationDao.delete(id);
    }

    public void updateLocation(Location location) {
        locationDao.update(location);
    }

}
