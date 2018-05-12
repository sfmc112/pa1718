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
import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.Event;
import pkg9cs.model.events.LadderPlusOneDRM;
import pkg9cs.model.events.MoralePlusOneDRM;
import pkg9cs.model.events.RamMinusOneDRM;
import pkg9cs.model.events.RamPlusOneDRM;
import pkg9cs.model.events.SiegeTowerMinusOneDRM;

/**
 *
 * @author sarah
 */
public class CardSeven extends Card {

    public CardSeven() {
        cardNumber = 7;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new RamAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new RamMinusOneDRM());

        days.add(new CardDay(w, 2, e, "Determined Enemy", "-1 to attacks on the Battering Ram"));

        w.clear();
        e.clear();

        w.add(new SiegeTowerAttack());

        e.add(new SiegeTowerMinusOneDRM());

        days.add(new CardDay(w, 2, e, "Iron Shields", "-1 to attacks on the Siege Tower"));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());
        w.add(new SiegeTowerAttack());

        e.add(new RamPlusOneDRM());
        e.add(new LadderPlusOneDRM());
        e.add(new MoralePlusOneDRM());

        days.add(new CardDay(w, 3, e, "Faith", "+1 to attacks on the Battering Ram, Ladders, and Morale action"));
    }
}
