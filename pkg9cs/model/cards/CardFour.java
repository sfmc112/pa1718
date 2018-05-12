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
import pkg9cs.model.events.RamPlusOneDRM;
import pkg9cs.model.events.ReduceMorale;
import pkg9cs.model.events.ReduceSupplies;
import pkg9cs.model.events.SiegeTowerPlusOneDRM;

/**
 *
 * @author sarah
 */
public class CardFour extends Card {

    public CardFour() {
        cardNumber = 4;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new LadderAttack());
        w.add(new SiegeTowerAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new ReduceMorale());

        days.add(new CardDay(w, 2, e, "Death of a Leader", "Reduce morale by 1"));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());

        e.add(new RamPlusOneDRM());

        days.add(new CardDay(w, 2, e, "Gate Fortified", "+1 to attacks on the Battering Ram"));

        w.clear();
        e.clear();

        w.add(new SiegeTowerAttack());

        e.add(new SiegeTowerPlusOneDRM());
        
        days.add(new CardDay(w, 3, e, "Flaming Arrows", "+1 to attacks on the Siege Engine"));
    }
}
