package com.test.demo.common;

import com.test.demo.controller.EventController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.*;

public class LogFileReader {
    private final Map<String, Object> eventsMapping = new WeakHashMap<>();
    ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public void readLogFile(String path, EventController eventController) {
        File theFile = new File(path);
        int MAX_THREADS = 10;
        int threadCount = 0;
        List<Callable<String>> tasksToSubmit = new ArrayList<>();
        try(LineIterator it = FileUtils.lineIterator(theFile, "UTF-8")) {
            while(it.hasNext()){
                String line = it.nextLine();
                if(!line.trim().isEmpty() && LogLine.isValid(line)){
                    LogLine logLine = LogLine.fromJson(line);
                    if(eventsMapping.containsKey(logLine.getId())){
                        System.out.println("Duplicate event id: " + logLine.getId());
                        LogLine oldLogLine = (LogLine) eventsMapping.get(logLine.getId());
                        eventsMapping.put(logLine.getId(), null);
                        eventsMapping.remove(logLine.getId());
                        RegisterLogTask task = new RegisterLogTask( new LogPair(logLine,oldLogLine), eventController);
                        tasksToSubmit.add(task);
                        threadCount++;
                    }else {
                        eventsMapping.put(logLine.getId(), logLine);
                    }
                }
                if(threadCount >= MAX_THREADS){
                    List<Future<String>> futures = executorService.invokeAll(tasksToSubmit);
                    for (Future<String> future : futures) {
                        String result = future.get();
                    }
                    tasksToSubmit.clear();
                    threadCount = 0;
                }
            }
            if(threadCount > 0){
                List<Future<String>> futures = executorService.invokeAll(tasksToSubmit);
                for (Future<String> future : futures) {
                     String result = future.get();
                }
                System.out.println(futures.size());
                tasksToSubmit.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
