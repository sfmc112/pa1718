/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import pkg9cs.model.enemyAttacks.RamAttack;
import pkg9cs.model.enemyAttacks.SwordAttack;
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.enemyAttacks.LadderAttack;
import pkg9cs.model.enemyAttacks.SiegeTowerAttack;
import pkg9cs.model.events.ReduceSupplies;
import pkg9cs.model.events.TrebuchetAttack;
import pkg9cs.model.events.ReduceMorale;
import pkg9cs.model.events.Event;
import java.util.ArrayList;

/**
 *
 * @author sarah
 */
public class CardTwo extends Card {

    public CardTwo() {
        cardNumber = 2;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new SiegeTowerAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new ReduceMorale());
        e.add(new ReduceSupplies());

        days.add(new CardDay(w, 2, e));
        
        w.clear();
        e.clear();

        w.add(new SwordAttack());
        //TODO acrescentar eventos
        days.add(new CardDay(w, 2, e));
        
        w.clear();
        e.clear();
        
        w.add(new LadderAttack());
        w.add(new RamAttack());
        
        e.add(new TrebuchetAttack());
        
        days.add(new CardDay(w, 1, e));
    }

}
