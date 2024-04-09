package com.example.mobilestudy.dto;

import android.graphics.Bitmap;

public class Card {
    private int id;
    private String eventName;
    private String eventPlace;
    private String description;

    private String city;

    private String eventType;

    private boolean isFavorite;

    private String imagePreview;

    public Card(int id, String eventName, String eventPlace, String description,
                String imagePreview, String city, String eventType, boolean isFavorite) {
        this.id = id;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.description = description;
        this.imagePreview = imagePreview;
        this.city = city;
        this.eventType = eventType;
        this.isFavorite = isFavorite;
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

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
