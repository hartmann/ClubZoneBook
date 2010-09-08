package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.dao.BaseDao;
import com.bekk.wa.webframework.database.domain.Organizer;
import com.bekk.wa.webframework.database.service.OrganizerService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

public class OrganizerServiceImpl implements OrganizerService {

    private BaseDao<Organizer, Long> organizerDao;


    public BaseDao<Organizer, Long> getLocationDao() {
        return organizerDao;
    }

    @Resource
    public void setOrganizerDao(BaseDao<Organizer, Long> organizerDao) {
        this.organizerDao = organizerDao;
    }

    public long addOrganizer(Organizer organizer) {
        Organizer newOrganizer = organizerDao.update(organizer);
        return newOrganizer.getId();
    }

    public Organizer getOrganizer(long id) {
        return organizerDao.read(id);
    }

    public List<Organizer> getOrganizers() {
        return organizerDao.find(new HashMap<String, Object>(), "name", true);
    }

    public void removeOrganizer(long id) {
        organizerDao.delete(id);
    }

    public void updateOrganizer(Organizer organizer) {
        organizerDao.update(organizer);
    }

}
