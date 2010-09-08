package com.bekk.wa.webframework.database.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "location_id")
    private Long id;

    @NotBlank(errorCode="location.name.blank")
    @Length(max=50, errorCode="location.name.toolong")
    private String name;

    @NotBlank(errorCode="location.description.blank")
    @Length(max=100, errorCode="location.description.toolong")
    private String description;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> event) {
        this.events = event;
    }
}
