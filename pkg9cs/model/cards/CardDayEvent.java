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
import pkg9cs.model.events.Event;

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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name).append("\n").append(description).append("\n");
        return str.toString();
    }

}
