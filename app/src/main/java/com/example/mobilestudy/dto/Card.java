package com.example.mobilestudy.dto;

public class Card {
    private int id;
    private String eventName;
    private String eventPlace;
    private String description;

    private boolean isFavorite;
    private byte[] eventPreview;

    public Card(int id, String eventName, String eventPlace, String description, boolean isFavorite) {
        this.id = id;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.description = description;
        this.isFavorite = isFavorite;
    }


    public Card(int id, String eventName, String eventPlace, boolean isFavorite, byte[] eventPreview) {
        this.id = id;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.isFavorite = isFavorite;
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

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
