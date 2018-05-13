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
public class SiegeTowerAttack implements WeaponAttack {

    @Override
    public void attack(GameData game) {
        if (game.getEnemyB().isSiegeTowerOnCloseCombatSpace()) {
            return;
        }
        game.getEnemyB().advanceTower();
        if (game.getEnemyB().isSiegeTowerOnCloseCombatSpace()) {
            game.getStatusB().advanceMorale();
        }
    }

    @Override
    public String toString() {
        return "Siege tower has advanced!";
    }
}
