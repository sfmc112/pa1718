/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.util.ArrayList;
import java.util.List;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public abstract class Card {

    protected int cardNumber;
    protected List<CardDay> days;

    public Card() {
        days = new ArrayList<>();
    }
    
    public void executeCard(GameData game, int dayNumber){
        days.get(dayNumber-1).executeDayActions(game);
    }

    @Override
    public String toString() {
        return "Card{" + "cardNumber=" + cardNumber + '}';
    }

    public String printDay(int dayNumber) {
        StringBuilder str = new StringBuilder();
        str.append("CARD ").append( cardNumber).append("\n");
        str.append(days.get(dayNumber));
        return str.toString();
    }
    
    

}
