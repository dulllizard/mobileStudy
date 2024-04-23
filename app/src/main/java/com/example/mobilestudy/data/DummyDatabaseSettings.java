package com.example.mobilestudy.data;


import java.util.ArrayList;
import java.util.List;

public class DummyDatabaseSettings {

    private String city;
    private String eventType;

    private String[] availableCities;

    private String[] availableEventTypes;

    DummyDatabaseSettings() {
        setCity("Красноярск");
        setEventType("Выставки");

        availableCities = new String[]{"Красноярск", "Москва", "Новосибирск"};
        availableEventTypes = new String[]{"Выставки", "Образование"};

    }

    private static DummyDatabaseSettings instance;

    public static DummyDatabaseSettings getInstance() {
        if (instance == null) {
            instance = new DummyDatabaseSettings();
        }
        return instance;
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

    public String[] getAvailableCities() {
        return availableCities;
    }

    public void setAvailableCities(String[] availableCities) {
        this.availableCities = availableCities;
    }

    public String[] getAvailableEventTypes() {
        return availableEventTypes;
    }

    public void setAvailableEventTypes(String[] availableEventTypes) {
        this.availableEventTypes = availableEventTypes;
    }
}
