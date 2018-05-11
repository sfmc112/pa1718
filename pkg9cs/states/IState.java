/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.elements.Weapon;

/**
 *
 * @author sarah
 */
public interface IState {

    public IState startGame();

    public IState executeCard();

    public IState checkEnemiesCloseCombat();

    public IState checkEnemiesArchers();

    public IState checkEnemiesBoilingWater();

    public IState archersAttack(Weapon weapon);

    public IState closeCombatAttack();

    public IState boilingWaterAttack();

    public IState selectTunnelMov();

    public IState moveInTunnel();

    public IState askUseOfSupply();

    public IState rallyTroops();

    public IState askAddActionPoint();
    
    public IState buyActionPoint();

    public IState endOfTurn();

    public IState coupure();

    public IState supplyRaid();

    public IState sabotage();

    public IState endOfDay();

}
