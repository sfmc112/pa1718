/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.util.ArrayList;
import java.util.List;
import pkg9cs.model.GameData;
import pkg9cs.model.events.Event;

/**
 *
 * @author sarah
 */
public class CardDayEvent {

    private String name;
    private String description;
    private List<Event> events;

    public CardDayEvent(ArrayList<Event> events) {
        this.events = new ArrayList<>(events);
    }

    public void executeCardDayEvents(GameData game) {
        for (Event evento : events) {
            evento.executeEvent(game);
        }
    }

    @Override
    public String toString() {
        //TODO
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
