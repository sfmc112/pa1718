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
import pkg9cs.model.events.AddTrebuchet;
import pkg9cs.model.events.CoupurePlusOneDRM;
import pkg9cs.model.events.Event;
import pkg9cs.model.events.LadderPlusOneDRM;
import pkg9cs.model.events.RamPlusOneDRM;
import pkg9cs.model.events.RemoveSiegeTower;
import pkg9cs.model.events.SiegeTowerPlusOneDRM;

/**
 *
 * @author sarah
 */
public class CardFive extends Card {

    public CardFive() {
        cardNumber = 5;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new RamAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new LadderPlusOneDRM());
        e.add(new RamPlusOneDRM());
        e.add(new SiegeTowerPlusOneDRM());

        days.add(new CardDay(w, 3, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        w.add(new RamAttack());

        e.add(new RemoveSiegeTower());

        days.add(new CardDay(w, 2, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());

        e.add(new AddTrebuchet());
        e.add(new CoupurePlusOneDRM());

        days.add(new CardDay(w, 2, e));
    }
}
