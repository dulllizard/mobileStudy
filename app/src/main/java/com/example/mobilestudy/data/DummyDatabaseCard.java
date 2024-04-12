package com.example.mobilestudy.data;

import com.example.mobilestudy.dto.Event;

import java.util.ArrayList;
import java.util.List;

public class DummyDatabaseCard {
    private static DummyDatabaseCard instance;
    private List<Event> events = new ArrayList<>();

    DummyDatabaseCard() {
        String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA_%D0%A1%D1%82%D1%80%D0%B5%D0%BB%D0%BA%D0%B0_%D1%81_%D0%B2%D1%8B%D1%81%D0%BE%D1%82%D1%8B.jpg/266px-%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA_%D0%A1%D1%82%D1%80%D0%B5%D0%BB%D0%BA%D0%B0_%D1%81_%D0%B2%D1%8B%D1%81%D0%BE%D1%82%D1%8B.jpg";
        String url2 = "https://www.admkrsk.ru/assets/%D0%9C%D0%B5%D0%B4%D0%B8%D0%B0%D1%84%D0%B0%D0%B9%D0%BB%D1%8B/%D0%96%D0%94%20%D0%B2%D0%BE%D0%BA%D0%B7%D0%B0%D0%BB.jpg";

        events.add(new Event(1, "Qwerty 1", "Place 1", "Description 1", url1, "Красноярск", "Выставки", false));
        events.add(new Event(2, "Event 2", "Place 2", "Description 2", url2, "Красноярск", "Выставки", false));
        events.add(new Event(3, "Event 3", "Place 3", "Description 3", url1, "Новосибирск", "Выставки", true));
        events.add(new Event(4, "Event 4", "Place 4", "Description 4", url2, "Новосибирск", "Концерты", true));
    }

    public static DummyDatabaseCard getInstance() {
        if (instance == null) {
            instance = new DummyDatabaseCard();
        }
        return instance;
    }

    public void addCard(Event event) {
        events.add(event);
    }

    public Event getCardById(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public List<Event> getAllCards() {
        return events;
    }

    public List<Event> getCardsByCity(String city) {
        List<Event> filteredList = new ArrayList<>();
        for (Event event : events) {
            if (event.getCity().equals(city)) {
                filteredList.add(event);
            }
        }
        return filteredList;
    }

    public List<Event> getCardsByEventType(String eventType) {
        List<Event> filteredList = new ArrayList<>();
        for (Event event : events) {
            if (event.getEventType().equals(eventType)) {
                filteredList.add(event);
            }
        }
        return filteredList;
    }

    public List<Event> getCardsByCityAndEventType(String city, String eventType) {
        List<Event> filteredList = new ArrayList<>();
        for (Event event : events) {
            if (event.getEventType().equals(eventType) && event.getCity().equals(city)) {
                filteredList.add(event);
            }
        }
        return filteredList;
    }

    public List<Event> getFavoriteCards() {
        List<Event> favoriteEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getIsFavorite()) {
                favoriteEvents.add(event);
            }
        }
        return favoriteEvents;
    }

    public void updateIsFavoriteById(int id, boolean isFavorite) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setIsFavorite(isFavorite);
                break;
            }
        }
    }
}
