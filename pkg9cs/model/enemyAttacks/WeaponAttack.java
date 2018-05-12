/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.enemyAttacks;

import java.io.Serializable;
import pkg9cs.model.EnemyBoard;

/**
 *
 * @author sarah
 */
public interface WeaponAttack extends Serializable {

    public void attack(EnemyBoard eb);

}
