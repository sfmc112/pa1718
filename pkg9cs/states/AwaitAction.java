/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class AwaitAction extends StateAdapter {

    public AwaitAction(GameData game) {
        super(game);
    }

    @Override
    public IState endOfTurn() {
        if (getGame().endOfTurnLossCheck()) {
            return new GameLost(getGame());
        }
        if (getGame().isNotTheLastCard()) {
            getGame().moveCardToDiscardedPile();
            getGame().newTurnSetup();
            return new AwaitDrawCard(getGame());
        }
        if (getGame().checkIfWon()) { //End of Day procedures in this method
            return new GameWon(getGame());
        }
        //Next Day
        return new AwaitDrawCard(getGame());
    }

    @Override
    public IState askAddActionPoint() {
        if (!getGame().checkAP() && !getGame().isUsedExtraAP() && getGame().checkAvailableResources()) {
            return new AwaitAddActionPoint(getGame());
        }
        return this;
    }

    @Override
    public IState askUseOfSupply() {
        if (getGame().checkAP() && getGame().checkAvailableSupplies()) {
            return new AwaitAddSupplyRallyTroops(getGame());
        }
        return this;
    }

    @Override
    public IState selectTunnelMov() {
        return new AwaitTunnelMovementSelection(getGame());
    }

    @Override
    public IState checkEnemiesCloseCombat() {
        if (getGame().checkAP() && getGame().enemiesOnCloseCombat()) {
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesBoilingWater() {
        if (getGame().checkAP() && getGame().enemiesOnCircleSpace() && !getGame().isUsedBoiling()) {
            return new AwaitEnemySelectionBoilingWaterAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesArchers() {
        if (getGame().checkAP() && getGame().enemiesAttackable()) {
            return new AwaitEnemySelectionArchersAttack(getGame());
        }
        return this;
    }

    @Override
    public IState coupure() {
        if (getGame().checkAP() && !getGame().wallOnStartingSpace()) {
            getGame().coupure();
            getGame().subtractActionPoint();
        }
        return this;
    }

    @Override
    public IState sabotage() {
        if (getGame().checkAP() && getGame().checkSoldiersOnEnemyLine() && getGame().existsTrebuchets()) {
            getGame().sabotage();
            getGame().subtractActionPoint();
        }
        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        }
        return this;
    }

    @Override
    public IState supplyRaid() {
        if (getGame().checkAP() && getGame().checkSoldiersOnEnemyLine() && !getGame().suppliesFull()) {
            getGame().addSupplyCount();
            getGame().subtractActionPoint();
        }
        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        }
        return this;
    }

}
