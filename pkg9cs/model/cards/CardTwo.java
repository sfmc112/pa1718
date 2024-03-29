/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import pkg9cs.model.enemyAttacks.SwordAttack;
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.enemyAttacks.SiegeTowerAttack;
import pkg9cs.model.events.ReduceSupplies;
import pkg9cs.model.events.TrebuchetAttack;
import pkg9cs.model.events.ReduceMorale;
import pkg9cs.model.events.Event;
import java.util.ArrayList;
import pkg9cs.model.events.MoralePlusOneDRM;
import pkg9cs.model.events.SabotagePlusOneDRM;

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

        days.add(new CardDay(w, 2, e, "Illness", "Reduce morale by 1\nReduce supplies by 1"));

        w.clear();
        e.clear();

        w.add(new SwordAttack());

        e.add(new SabotagePlusOneDRM());
        e.add(new MoralePlusOneDRM());

        days.add(new CardDay(w, 2, e, "Guards Distracted", "+1 to sabotage action\n+1 to morale action"));

        w.clear();
        e.clear();

        e.add(new TrebuchetAttack());

        days.add(new CardDay(w, 1, e, "Trebuchet Attack", ""));
    }

}
