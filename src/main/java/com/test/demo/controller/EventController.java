package com.test.demo.controller;


import com.test.demo.entity.EventEntity;
import com.test.demo.service.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EventController {
    @Autowired
    private EventDao eventDao;



    public List<EventEntity> getEvents() {
        return eventDao.getEvents();
    }

    public EventEntity addEvent(EventEntity newEvent) {
        return eventDao.addEvent(newEvent);
    }

    public void removeEvent(String eventId) {
        eventDao.removeEvent(eventId);
    }

}
