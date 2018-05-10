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
import pkg9cs.model.events.ReduceMorale;
import pkg9cs.model.events.ReduceSupplies;

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

        days.add(new CardDay(w, 2, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());
        //TODO add +1 DRM Ram
        days.add(new CardDay(w, 2, e));

        w.clear();
        e.clear();

        w.add(new SiegeTowerAttack());

        //TODO add +1 DRM Siege Engine
        days.add(new CardDay(w, 3, e));
    }
}
