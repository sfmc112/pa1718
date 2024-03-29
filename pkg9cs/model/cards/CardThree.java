/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import java.util.ArrayList;
import pkg9cs.model.enemyAttacks.LadderAttack;
import pkg9cs.model.enemyAttacks.RamAttack;
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.CircleSpacePlusTwoDRM;
import pkg9cs.model.events.Event;
import pkg9cs.model.events.OnlyRaidAndSabotage;
import pkg9cs.model.events.ReduceSupplies;

/**
 *
 * @author sarah
 */
public class CardThree extends Card {

    public CardThree() {
        cardNumber = 3;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new LadderAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new ReduceSupplies());

        days.add(new CardDay(w, 2, e, "Supplies Spoiled", "Reduce supplies by 1"));

        w.clear();
        e.clear();

        e.add(new OnlyRaidAndSabotage());
        days.add(new CardDay(w, 2, e, "Bad Weather", "Only raid and sabotage actions allowed this turn"));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());
        
        e.add(new CircleSpacePlusTwoDRM());

        days.add(new CardDay(w, 2, e, "Boiling Oil", "+2 to attacks on enemy units in circle spaces"));
    }

}
