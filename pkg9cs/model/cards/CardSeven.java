/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.util.ArrayList;
import pkg9cs.model.enemyAttacks.LadderAttack;
import pkg9cs.model.enemyAttacks.RamAttack;
import pkg9cs.model.enemyAttacks.SiegeTowerAttack;
import pkg9cs.model.enemyAttacks.SwordAttack;
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.Event;

/**
 *
 * @author sarah
 */
public class CardSeven extends Card{
    
    public CardSeven() {
        cardNumber = 7;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new RamAttack());

        ArrayList<Event> e = new ArrayList<>();
        //TODO -1 DRM Ram

        days.add(new CardDay(w, 2, e));

        w.clear();
        e.clear();

        w.add(new SiegeTowerAttack());
        //TODO -1 DRM Siege Tower
        days.add(new CardDay(w, 2, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());
        w.add(new SiegeTowerAttack());

        //TODO add +1 DRM Ram, Ladder and Morale
        days.add(new CardDay(w, 3, e));
    }
}
