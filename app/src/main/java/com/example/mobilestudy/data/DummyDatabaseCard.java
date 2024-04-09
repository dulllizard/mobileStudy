package com.example.mobilestudy.data;

import com.example.mobilestudy.dto.Card;

import java.util.ArrayList;
import java.util.List;

public class DummyDatabaseCard {
    private static DummyDatabaseCard instance;
    private List<Card> cards = new ArrayList<>();

    DummyDatabaseCard() {
        String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA_%D0%A1%D1%82%D1%80%D0%B5%D0%BB%D0%BA%D0%B0_%D1%81_%D0%B2%D1%8B%D1%81%D0%BE%D1%82%D1%8B.jpg/266px-%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA_%D0%A1%D1%82%D1%80%D0%B5%D0%BB%D0%BA%D0%B0_%D1%81_%D0%B2%D1%8B%D1%81%D0%BE%D1%82%D1%8B.jpg";
        String url2 = "https://www.admkrsk.ru/assets/%D0%9C%D0%B5%D0%B4%D0%B8%D0%B0%D1%84%D0%B0%D0%B9%D0%BB%D1%8B/%D0%96%D0%94%20%D0%B2%D0%BE%D0%BA%D0%B7%D0%B0%D0%BB.jpg";

        cards.add(new Card(1, "Qwerty 1", "Place 1", "Description 1", "Красноярск", "Выставки", url1, false));
        cards.add(new Card(2, "Event 2", "Place 2", "Description 2", url2, "Красноярск", "Выставки", false));
        cards.add(new Card(3, "Event 3", "Place 3", "Description 3", url1, "Новосибирск", "Выставки", true));
        cards.add(new Card(4, "Event 4", "Place 4", "Description 4", url2, "Новосибирск", "Концерты", true));
    }

    public static DummyDatabaseCard getInstance() {
        if (instance == null) {
            instance = new DummyDatabaseCard();
        }
        return instance;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCardById(int id) {
        for (Card card : cards) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    public List<Card> getAllCards() {
        return cards;
    }

    public List<Card> getCardsByCity(String city) {
        List<Card> filteredList = new ArrayList<>();
        for (Card card : cards) {
            if (card.getCity().equals(city)) {
                filteredList.add(card);
            }
        }
        return filteredList;
    }

    public List<Card> getCardsByEventType(String eventType) {
        List<Card> filteredList = new ArrayList<>();
        for (Card card : cards) {
            if (card.getEventType().equals(eventType)) {
                filteredList.add(card);
            }
        }
        return filteredList;
    }

    public List<Card> getCardsByCityAndEventType(String city, String eventType) {
        List<Card> filteredList = new ArrayList<>();
        for (Card card : cards) {
            if (card.getEventType().equals(eventType) && card.getCity().equals(city)) {
                filteredList.add(card);
            }
        }
        return filteredList;
    }

    public List<Card> getFavoriteCards() {
        List<Card> favoriteCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.getIsFavorite()) {
                favoriteCards.add(card);
            }
        }
        return favoriteCards;
    }

    public void updateIsFavoriteById(int id, boolean isFavorite) {
        for (Card card : cards) {
            if (card.getId() == id) {
                card.setIsFavorite(isFavorite);
                break;
            }
        }
    }
}
