package com.test.demo.common;

import java.io.Serializable;

public class LogPair implements Serializable {
    LogLine first;
    LogLine second;
    public LogPair(LogLine first, LogLine second) {
        if(first.getTimestamp().after(second.getTimestamp())) {
            this.first = second;
            this.second = first;
        } else {
            this.first = first;
            this.second = second;
        }
    }
}
