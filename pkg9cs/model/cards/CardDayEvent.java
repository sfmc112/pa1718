/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pkg9cs.model.GameData;
import pkg9cs.model.events.*;

/**
 *
 * @author sarah
 */
public class CardDayEvent implements Serializable {

    private String name;
    private String description;
    private List<Event> events;

    public CardDayEvent(String name, String description, ArrayList<Event> events) {
        this.name = name;
        this.description = description;
        this.events = new ArrayList<>(events);
    }    

    public void executeCardDayEvents(GameData game) {
        for (Event evento : events) {
            evento.executeEvent(game);
        }
    }

    public int searchForSabotageDRM() {
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getSabotageDRM();
        }
        return sumDRM;
    }
    
    public int searchForMoraleDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getMoraleDRM();
        }
        return sumDRM;
    }
    
    public int searchForCircleSpaceDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getCircleSpaceDRM();
        }
        return sumDRM;
    }
    
    public int searchForCloseCombatSpaceDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getCloseCombatSpaceDRM();
        }
        return sumDRM;
    }
    
    public int searchForSupplyRaidDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getSupplyRaidDRM();
        }
        return sumDRM;
    }
    
    public int searchForCoupureDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getCoupureDRM();
        }
        return sumDRM;
    }
    
    public int searchForLadderDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getLadderDRM();
        }
        return sumDRM;
    }
    
    public int searchForBatteringRamDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getRamDRM();
        }
        return sumDRM;
    }
    
    public int searchForSiegeTowerDRM(){
        int sumDRM = 0;

        for (Event event : events) {
            sumDRM += event.getSiegeTowerDRM();
        }
        return sumDRM;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name).append("\n").append(description).append("\n");
        return str.toString();
    }

}
