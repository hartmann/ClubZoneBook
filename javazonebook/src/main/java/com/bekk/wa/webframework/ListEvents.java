package com.bekk.wa.webframework;

import com.bekk.wa.webframework.database.domain.Event;
import com.bekk.wa.webframework.database.service.EventService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 */
public class ListEvents extends BasePage {

    @SpringBean
    private EventService eventService;

    public ListEvents() {

        Form form = new Form("form");

        SortableDataProvider<Event> dp = new SortableEventDataProvider(eventService);
        dp.setSort("id", true);

        final DataView<Event> dataView = new DataView<Event>("eventsData", dp) {

            protected void populateItem(final Item<Event> item) {
                final Event event = item.getModelObject();

                Link link = new Link("edit-link") {
                    public void onClick() {
                        editEvent(event);
                    }
                };
                link.add(new Label("event.id", String.valueOf(event.getId())));
                item.add(link);
                item.add(new Label("event.description", event.getDescription()));
                item.add(new Label("event.location", event.getLocation().getName()));
                item.add(new Label("event.organizer", event.getOrganizer().getName()));

                item.add(new Button("edit", new Model<String>("Edit")) {
                    public void onSubmit() {
                        editEvent(event);
                    }
                });
                item.add(new Button("delete", new Model<String>("Delete")) {
                    public void onSubmit() {
                        deleteEvent(event);
                    }
                }.setDefaultFormProcessing(false));


                item.add(new AttributeModifier("class", true, new LoadableDetachableModel() {
                    protected Object load() {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));

            }
        };

        dataView.setItemsPerPage(1000);
        form.add(dataView);
        form.add(new OrderByBorder("orderById", "id", dp));
        form.add(new OrderByBorder("orderByDescription", "description", dp));
        form.add(new OrderByBorder("orderByLocation", "location", dp));
        form.add(new OrderByBorder("orderByOrganizer", "organizer", dp));

        add(form);
    }

    protected void editEvent(Event event) {
        setRedirect(true);
        setResponsePage(new EventForm(event));
    }

    protected void deleteEvent(Event event) {
        eventService.removeEvent(event.getId());
        setRedirect(true);
        setResponsePage(this.getClass());
    }

}
