package com.bekk.wa.webframework.database.domain;

import javax.persistence.*;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import java.util.Collection;

@Entity
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "organizer_id")
    private Long id;

    @NotBlank(errorCode="organizer.name.blank")
    @Length(max=50, errorCode="organizer.name.toolong")
    private String name;

    @NotBlank(errorCode="organizer.link.blank")
    @Length(max=100, errorCode="organizer.link.toolong")
    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Event> events;

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> event) {
        this.events = event;
    }
}
