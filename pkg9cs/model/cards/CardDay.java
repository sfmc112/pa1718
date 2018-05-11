/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.Event;
import java.util.ArrayList;
import java.util.List;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class CardDay {

    private List<WeaponAttack> enemyAttacks;
    private final int numActions;
    private List<Event> events;

    public CardDay(ArrayList<WeaponAttack> enemyAttacks, int numActions, ArrayList<Event> events) {
        this.enemyAttacks = new ArrayList<>(enemyAttacks);
        this.numActions = numActions;
        this.events = new ArrayList<>(events);
    }

    public void executeDayActions(GameData game) {

        for (Event evento : events) {
            evento.executeEvent(game);
        }

        for (WeaponAttack enemyAttack : enemyAttacks) {
            enemyAttack.attack(game.getEnemyB());
        }

        game.setNumberOfActions(numActions);

    }

    @Override
    public String toString() {
        //TODO
        StringBuilder str= new StringBuilder();
        str.append("Number of Actions = ").append(numActions).append("\n");
        str.append(enemyAttacks.isEmpty()?"Enemy Attacks: NONE\n":"Enemy Attacks:\n");
        for (int i = 0; i < enemyAttacks.size(); i++) {
            str.append(enemyAttacks.get(i)).append("\n");
        }
        str.append("Event:\n");
        for (int i = 0; i < events.size(); i++) {
            str.append(events.get(i)).append("\n");
        }
        return str.toString();
        
    }
    

}
