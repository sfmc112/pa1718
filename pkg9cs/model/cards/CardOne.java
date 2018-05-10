/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.cards;

import pkg9cs.model.enemyAttacks.WeaponAttack;
import pkg9cs.model.events.TrebuchetAttack;
import pkg9cs.model.events.Event;
import java.util.ArrayList;

/**
 *
 * @author sarah
 */
public class CardOne extends Card {

    public CardOne() {
        cardNumber = 1;
        
        ArrayList<WeaponAttack> w = new ArrayList<>();
        ArrayList<Event> e = new ArrayList<>();
        e.add(new TrebuchetAttack());
        
        days.add(new CardDay(w, 3, e));
        days.add(new CardDay(w, 2, e));
        days.add(new CardDay(w, 1, e));
    }

}
