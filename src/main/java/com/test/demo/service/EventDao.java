package com.test.demo.service;

import com.test.demo.entity.EventEntity;

import java.util.List;

public interface EventDao {
     List<EventEntity> getEvents();
     EventEntity addEvent(EventEntity event);

     void removeEvent(String eventId);
}
