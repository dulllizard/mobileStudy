package com.example.mobilestudy.dto;

public class Card {
    private int id;
    private String eventName;
    private String eventPlace;
    private byte[] eventPreview;

    public Card(String eventName, String eventPlace, byte[] eventPreview) {
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventPreview = eventPreview;
    }

    public Card(int id, String eventName, String eventPlace, byte[] eventPreview) {
        this.id = id;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventPreview = eventPreview;
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

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public byte[] getEventPreview() {
        return eventPreview;
    }

    public void setEventPreview(byte[] eventPreview) {
        this.eventPreview = eventPreview;
    }
}
