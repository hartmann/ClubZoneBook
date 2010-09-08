package com.bekk.wa.webframework.test;

import com.bekk.wa.webframework.ListEvents;
import com.bekk.wa.webframework.database.util.DbLoader;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class RepopulateDatabase extends WebPage {

    @SpringBean
    private DbLoader dbLoader;

    public RepopulateDatabase() {
        add(new Link("repopulateDatabase") {
            @Override
            public void onClick() {
                try {
                    dbLoader.loadData();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        add(new Link("linkToEventList"){
            @Override
            public void onClick() {
                setResponsePage(ListEvents.class);
            }
        });
    }
}
