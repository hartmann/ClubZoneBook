package com.bekk.wa.webframework;

import com.bekk.wa.webframework.database.domain.Event;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {

    public BasePage() {
        add(new Link("linkToEventList"){
            @Override
            public void onClick() {
                setResponsePage(ListEvents.class);
            }
        });
        add(new Link("addEvent") {
            @Override
            public void onClick() {
                setRedirect(true);
                setResponsePage(new EventForm(new Event()));
            }
        });
        add(new Label("footer", "JavaZoneBook ª"));

    }
}
