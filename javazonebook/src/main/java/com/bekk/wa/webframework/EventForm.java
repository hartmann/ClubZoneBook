package com.bekk.wa.webframework;

import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.domain.Location;
import com.bekk.wa.webframework.database.domain.Organizer;
import com.bekk.wa.webframework.database.service.EventService;
import com.bekk.wa.webframework.database.service.LocationService;
import com.bekk.wa.webframework.database.service.OrganizerService;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 */
public class EventForm extends BasePage {
    @SpringBean
    private EventService eventService;

    @SpringBean
    private LocationService locationService;

    @SpringBean
    private OrganizerService organizerService;
    
    /**
     * Constructor used to edit an event
     *
     * @param event event to edit
     */
    public EventForm(Event event) {
        // Create and add the form
        final Form<Event> form = new Form<Event>("event-form", new CompoundPropertyModel<Event>(event));

        form.add(new TextField("description"));
        form.add(new Button("save", new Model<String>("Save")) {
            public void onSubmit() {
                onSaveEvent(form.getModelObject());
            }


        });

        DropDownChoice<Location> locations = new DropDownChoice<Location>("location", getLocations(), new ChoiceRenderer<Location>("name", "id"));
        locations.setRequired(true);
        form.add(locations);

        DropDownChoice<Organizer> organizations = new DropDownChoice<Organizer>("organizer", getOrganizers(), new ChoiceRenderer<Organizer>("name", "id"));
        organizations.setRequired(true);
        form.add(organizations);

        add(form);

                FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true).setMarkupId("feedback");
        add(feedback);
    }

    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    public List<Organizer> getOrganizers() {
        return organizerService.getOrganizers();
    }

    /**
     * Listener method for save action
     *
     * @param event event bean
     */
    protected void onSaveEvent(Event event) {
        String message = "";
        if (null == event.getId()) {
            eventService.addEvent(event);
            message = "Added event";
        } else {
            eventService.updateEvent(event);
            message = "Saved event";
        }

        getSession().info(message);
        setRedirect(true);
        setResponsePage(ListEvents.class);
    }

}
