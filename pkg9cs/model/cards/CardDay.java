/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.io.Serializable;
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.Event;
import java.util.ArrayList;
import java.util.List;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class CardDay implements Serializable {

    private List<WeaponAttack> enemyAttacks;
    private final int numActions;
    private CardDayEvent cardEvent;

    public CardDay(ArrayList<WeaponAttack> enemyAttacks, int numActions, ArrayList<Event> events, String eventName, String eventDescription) {
        this.enemyAttacks = new ArrayList<>(enemyAttacks);
        this.numActions = numActions;
        cardEvent = new CardDayEvent(eventName, eventDescription, events);
    }

    public void executeDayActions(GameData game) {

        cardEvent.executeCardDayEvents(game);

        for (WeaponAttack enemyAttack : enemyAttacks) {
            enemyAttack.attack(game.getEnemyB());
        }

        game.setNumberOfActions(numActions);

    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("Number of Actions = ").append(numActions).append("\n");
        str.append(enemyAttacks.isEmpty() ? "Enemy Attacks: NONE\n" : "Enemy Attacks:\n");
        for (int i = 0; i < enemyAttacks.size(); i++) {
            str.append(enemyAttacks.get(i)).append("\n");
        }
        str.append("Event:\n");
        str.append(cardEvent);
        return str.toString();

    }

}
