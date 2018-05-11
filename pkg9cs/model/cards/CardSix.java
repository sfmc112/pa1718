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
import pkg9cs.model.events.CircleSpacePlusOneDRM;
import pkg9cs.model.events.CloseCombatSpacePlusOneDRM;
import pkg9cs.model.events.CoupurePlusOneDRM;
import pkg9cs.model.events.Event;
import pkg9cs.model.events.RaidPlusOneDRM;
import pkg9cs.model.events.SabotagePlusOneDRM;

/**
 *
 * @author sarah
 */
public class CardSix extends Card{
    
    public CardSix() {
        cardNumber = 6;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new SwordAttack());

        ArrayList<Event> e = new ArrayList<>();
        e.add(new RaidPlusOneDRM());
        e.add(new SabotagePlusOneDRM());

        days.add(new CardDay(w, 3, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        
        e.add(new CoupurePlusOneDRM());
        e.add(new RaidPlusOneDRM());
        e.add(new SabotagePlusOneDRM());

        days.add(new CardDay(w, 3, e));

        w.clear();
        e.clear();

        w.add(new RamAttack());
        w.add(new SiegeTowerAttack());

        e.add(new CloseCombatSpacePlusOneDRM());
        e.add(new CircleSpacePlusOneDRM());

        days.add(new CardDay(w, 3, e));
    }
}
