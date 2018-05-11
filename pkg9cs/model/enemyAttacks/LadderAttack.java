/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.enemyAttacks;

import pkg9cs.model.EnemyBoard;

/**
 *
 * @author sarah
 */
public class LadderAttack implements WeaponAttack{
    

    @Override
    public void attack(EnemyBoard eb) {
        //System.out.println("Atacou a escada.");
        eb.advanceLadder();
    }

    @Override
    public String toString() {
        return "Ladder has advanced!";
    }
    
}
