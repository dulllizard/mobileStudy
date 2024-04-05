package com.example.mobilestudy.dto;

public class Detail {
    private int id;
    private String eventName;
    private String description;

    public Detail(String eventName, String description) {
        this.eventName = eventName;
        this.description = description;
    }

    public Detail(int id, String eventName, String description) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
