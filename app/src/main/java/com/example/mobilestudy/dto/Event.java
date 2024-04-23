package com.example.mobilestudy.dto;

/**
 * События
 */
public class Event {

    /**
     * id события
     */
    private int id;

    /**
     * Название события
     */
    private String eventName;

    /**
     * Место проведения события
     */
    private String eventPlace;

    /**
     * Описание события
     */
    private String description;

    /**
     * Город, в котором проводится событие
     */
    private String city;

    /**
     * Тип события
     */
    private String eventType;

    /**
     * Добавлено ли событие в избранное
     */
    private boolean isFavorite;

    /**
     * Превью события
     */
    private String imagePreview;

    private boolean createdByUser;

    public Event(String eventName, String description) {
        this.eventName = eventName;
        this.description = description;
    }

    /**
     * Конструктор события
     * @param id id события
     * @param eventName Название события
     * @param eventPlace Место проведения события
     * @param description Описание события
     * @param imagePreview Превью события
     * @param city Город, в котором проводится событие
     * @param eventType Тип события
     * @param isFavorite Добавлено ли событие в избранное
     */
    public Event(int id, String eventName, String eventPlace, String description,
                 String imagePreview, String city, String eventType, boolean isFavorite,
                 boolean createdByUser) {
        this.id = id;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.description = description;
        this.imagePreview = imagePreview;
        this.city = city;
        this.eventType = eventType;
        this.isFavorite = isFavorite;
        this.createdByUser = createdByUser;
    }

    public Event(String eventName, String eventPlace, String description,
                 String imagePreview, String city, String eventType, boolean isFavorite,
                 boolean createdByUser) {
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.description = description;
        this.imagePreview = imagePreview;
        this.city = city;
        this.eventType = eventType;
        this.isFavorite = isFavorite;
        this.createdByUser = createdByUser;
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

    public boolean isCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(boolean createdByUser) {
        this.createdByUser = createdByUser;
    }
}
