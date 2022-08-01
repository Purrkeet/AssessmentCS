package com.test.demo.common;

import com.test.demo.controller.EventController;
import com.test.demo.entity.EventEntity;

import java.util.concurrent.Callable;


public class RegisterLogTask implements Callable<String> {

    LogPair logPair;

    EventController eventController;
    public RegisterLogTask(LogPair logPair, EventController eventController) {
        this.logPair = logPair;
        this.eventController = eventController;
    }

    @Override
    public String call() throws Exception {
        long diff = logPair.second.getTimestamp().getTime() - logPair.first.getTimestamp().getTime();
        Boolean isLongerThan4ms = diff > 4;
        EventEntity newEvent = new EventEntity.EventBuilder(logPair.first.getId(),diff,isLongerThan4ms)
                .setEventType(logPair.first.getType())
                .setEventHost(logPair.first.getHost())
                .build();
        eventController.addEvent(newEvent);
        return "Done";
    }
}
