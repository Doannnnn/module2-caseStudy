package org.example.service;

import org.example.model.Card;
import org.example.model.GameHistory;
import org.example.model.Schedule;
import org.example.model.User;
import org.example.utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CardService {
    public final String fileCard = "./data/card.csv";
    public List<Card> getAllCard() {
        return FileUtils.readData(fileCard, Card.class);
    }
    public List<Card> getAllCardByTournamentsId(long idTournaments) {
        List<Card> cards = getAllCard();
        List<Card> results = cards.stream()
                .filter(card -> card.getIdTournaments() == idTournaments)
                .collect(Collectors.toList());
        return results;
    }
    public Card findCard(long id) {
        List<Card> users = getAllCard();
        Card c = users.stream().filter(card -> card.getId() == id).findFirst().orElseThrow(null);
        return c;
    }

    public void updateCard(long id, Card card) {
        List<Card> cards = getAllCard();
        for (Card c : cards) {
            if (c.getId() == id) {
                c.setIdTournaments(card.getIdTournaments());
                c.setIdTeam(card.getIdTeam());
                c.setIdSchedule(card.getIdSchedule());
                c.setIdPlayer(card.getIdPlayer());
                c.setCard(card.getCard());
                c.setQuantity(card.getQuantity());
            }
        }
        FileUtils.writeData(fileCard, cards);
    }

    public void deleteCard(long id) {
        List<Card> cards = getAllCard();
        Card card = null;
        for (Card c : cards) {
            if (c.getId() == id) {
                card = c;
            }
        }
        cards.remove(card);
        FileUtils.writeData(fileCard, cards);
    }

    public void createCard(Card card) {
        List<Card> cards = getAllCard();
        card.setId(cards.size() + 1);
        cards.add(card);

        FileUtils.writeData(fileCard, cards);
    }

}
