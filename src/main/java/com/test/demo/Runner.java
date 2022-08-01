package com.test.demo;

import com.test.demo.common.LogFileReader;
import com.test.demo.controller.EventController;
import com.test.demo.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    @Autowired
    EventController eventController;

    @Override
    public void run(String... args) throws Exception {
    LogFileReader logFileReader = new LogFileReader();
        if (args.length == 0) {
            logFileReader.readLogFile("src/main/resources/logs/logfile.txt", eventController);
        } else {
            logFileReader.readLogFile(args[0], eventController);
        }
        //crudTest();
        for (EventEntity event : eventController.getEvents()) {
            System.out.println(event.toString());
        }

    }


    void crudTest(){
        System.out.println("test command line");
        eventController.removeEvent("asdas");
        EventEntity newEvent = new EventEntity.EventBuilder("asdas",1324L,true)
                .setEventType("test")
                .setEventHost("test")
                .build();
        eventController.addEvent(newEvent);
        for (EventEntity event : eventController.getEvents()) {
            System.out.println(event.toString());
        }
        System.out.println("end test command line");
    }
}
