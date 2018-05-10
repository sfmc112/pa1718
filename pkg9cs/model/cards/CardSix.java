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
public class CardSix extends Card{
    
    public CardSix() {
        cardNumber = 6;

        ArrayList<WeaponAttack> w = new ArrayList<>();
        w.add(new SwordAttack());

        ArrayList<Event> e = new ArrayList<>();
        //TODO +1 DRM Raid e Sabotage

        days.add(new CardDay(w, 3, e));

        w.clear();
        e.clear();

        w.add(new LadderAttack());
        //TODO +1 DRM Coupure, Raid e Sabotage
        days.add(new CardDay(w, 3, e));

        w.clear();
        e.clear();

        w.add(new RamAttack());
        w.add(new SiegeTowerAttack());


        // add +1 DRM CloseCombat and Circle Spaces
        days.add(new CardDay(w, 3, e));
    }
}
