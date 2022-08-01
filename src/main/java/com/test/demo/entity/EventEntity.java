package com.test.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "event")
public class EventEntity {

    public EventEntity(String eventId, Long eventDuration, String eventType, String eventHost, boolean eventAlert) {
        this.eventId = eventId;
        this.eventDuration = eventDuration;
        this.eventType = eventType;
        this.eventHost = eventHost;
        this.eventAlert = eventAlert;
    }

    public EventEntity() {

    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "eventId='" + eventId + '\'' +
                ", eventDuration=" + eventDuration +
                ", eventType='" + eventType + '\'' +
                ", eventHost='" + eventHost + '\'' +
                ", eventAlert=" + eventAlert +
                '}';
    }

    @Id
    @Column
    private String eventId;
    @Column
    private Long eventDuration;
    @Column
    private String eventType;
    @Column
    private String eventHost;
    @Column
    private boolean eventAlert;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Long eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public boolean isEventAlert() {
        return eventAlert;
    }

    public void setEventAlert(boolean eventAlert) {
        this.eventAlert = eventAlert;
    }
    private  EventEntity(EventBuilder builder){
        this.eventId = builder.eventId;
        this.eventDuration = builder.eventDuration;
        this.eventType = builder.eventType;
        this.eventHost = builder.eventHost;
        this.eventAlert = builder.eventAlert;
    }

    public static class EventBuilder {
        //required parameters
        private String eventId;
        private Long eventDuration;
        private boolean eventAlert;
        //optional parameters
        private String eventType;
        private String eventHost;
        public EventBuilder(String eventId, Long eventDuration, boolean eventAlert) {
            this.eventId = eventId;
            this.eventDuration = eventDuration;
            this.eventAlert = eventAlert;
        }
        public EventBuilder setEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }
        public EventBuilder setEventHost(String eventHost) {
            this.eventHost = eventHost;
            return this;
        }

        public EventEntity build() {
            return new EventEntity(this);
        }
    }


}
