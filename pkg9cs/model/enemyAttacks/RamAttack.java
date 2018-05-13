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
public class RamAttack implements WeaponAttack {

    @Override
    public void attack(GameData game) {
        if (game.getEnemyB().isBatteringRamOnCloseCombatSpace()) {
            return;
        }
        game.getEnemyB().advanceRam();
        if (game.getEnemyB().isBatteringRamOnCloseCombatSpace()) {
            game.getStatusB().advanceMorale();
        }
    }

    @Override
    public String toString() {
        return "Battering ram has advanced!";
    }

}
