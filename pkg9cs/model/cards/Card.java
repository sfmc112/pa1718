/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pkg9cs.RaidAndSabotageException;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public abstract class Card implements Serializable {

    protected int cardNumber;
    protected List<CardDay> days;

    public Card() {
        days = new ArrayList<>();
    }

    public void executeCard(GameData game, int dayNumber) throws RaidAndSabotageException {
        try {
            days.get(dayNumber - 1).executeDayActions(game);
        } catch (RaidAndSabotageException ex) {
            throw ex;
        }

        CardDayEvent cardEvent = days.get(dayNumber - 1).getCardEvent();

        game.setDayEvent(cardEvent);
    }

    @Override
    public String toString() {
        return "Card{" + "cardNumber=" + cardNumber + "}\n";
    }

    public String printDay(int dayNumber) {
        StringBuilder str = new StringBuilder();
        str.append("CARD ").append(cardNumber).append("\n");
        str.append(days.get(dayNumber));
        return str.toString();
    }

}
