/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.enemyAttacks;

import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class SwordAttack implements WeaponAttack{

    @Override
    public void attack(GameData game) {
        //System.out.println("Atacaram os mais atrasados.");
        game.getEnemyB().advanceSlowestEnemies(game);
    }
     @Override
    public String toString() {
        return "Slowest enemies advanced!";
    }
    
}
