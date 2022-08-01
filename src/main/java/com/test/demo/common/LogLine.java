package com.test.demo.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.Objects;

public class LogLine {
    private String id;
    private String state;
    private String type;
    private String host;
    private Timestamp timestamp;

    @JsonCreator
    public LogLine(@JsonProperty("id") String id,
                   @JsonProperty("state") String state,
                   @JsonProperty("type") String type,
                   @JsonProperty("host") String host,
                   @JsonProperty("timestamp") Timestamp timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                (type!=null ? ", type='" + type + '\'' :"") +
                (host!=null ? ", host='" + host + '\'': "") +
                ", timestamp=" + timestamp +
                '}';
    }

    public LogLine() {
    }

    public static LogLine fromJson(String line) {
        ObjectMapper mapper = new ObjectMapper();
        LogLine logLine;
        try {
            logLine = mapper.readValue(line, LogLine.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return logLine;
    }
    public static boolean isValid(String line){
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.readTree(line);
        }catch (JacksonException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogLine logLine = (LogLine) o;
        return Objects.equals(id, logLine.id) && Objects.equals(state, logLine.state) && Objects.equals(type, logLine.type) && Objects.equals(host, logLine.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, type, host);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
