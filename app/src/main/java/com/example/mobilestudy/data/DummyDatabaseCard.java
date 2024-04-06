package com.example.mobilestudy.data;

import com.example.mobilestudy.dto.Card;

import java.util.ArrayList;
import java.util.List;

public class DummyDatabaseCard {
    private List<Card> cards = new ArrayList<>();

    DummyDatabaseCard() {
        cards.add(new Card(1, "Event 1", "Place 1", false));
        cards.add(new Card(1, "Event 2", "Place 2", false));
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
