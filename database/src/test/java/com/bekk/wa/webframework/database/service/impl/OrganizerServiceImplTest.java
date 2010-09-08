package com.bekk.wa.webframework.database.service.impl;

import com.bekk.wa.webframework.database.domain.Organizer;
import com.bekk.wa.webframework.database.service.OrganizerService;
import com.bekk.wa.webframework.database.utils.DbUnitTestBase;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import javax.annotation.Resource;

public class OrganizerServiceImplTest extends DbUnitTestBase {

    private OrganizerService organizerService;

    public OrganizerService getOrganizerService() {
        return organizerService;
    }

    @Resource
    public void setOrganizerService(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @Test
    public void testDeleteOrganizer() {
        Organizer organizer = organizerService.getOrganizer(1);
        Long id = organizer.getId();
        organizerService.removeOrganizer(id);
        assertTrue(null == organizerService.getOrganizer(id));
    }

    @Test
    public void testInsertGet() {
        Organizer organizer = new Organizer();
        organizer.setName("my name");
        long newId = organizerService.addOrganizer(organizer);
        Organizer organizerFromDB = organizerService.getOrganizer(newId);
        assertTrue(organizerFromDB.getName().equals("my name"));
    }

    @Override
    public String getInitializationDataSetPath() {
        return "events.db.xml";
    }
}
