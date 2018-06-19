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
        if (getGame().canDoBuyActionPoint()) {
            return new AwaitAddActionPoint(getGame());
        }
        return this;
    }

    @Override
    public IState askUseOfSupply() {
        if (getGame().canDoRallyTroops()) {
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
        if (getGame().canDoCloseCombat()) {
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesBoilingWater() {
        if (getGame().canDoBoiling()) {
            return new AwaitEnemySelectionBoilingWaterAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesArchers() {
        if (getGame().canDoArchers()) {
            return new AwaitEnemySelectionArchersAttack(getGame());
        }
        return this;
    }

    @Override
    public IState coupure() {
        if (getGame().canDoCoupure()) {
            getGame().coupure();
            getGame().subtractActionPoint();
        }
        return this;
    }

    @Override
    public IState sabotage() {
        if (getGame().canDoSabotage()) {
            getGame().sabotage();
            getGame().subtractActionPoint();

            if (getGame().immediateLossCheck()) {
                return new GameLost(getGame());
            }
        }

        return this;
    }

    @Override
    public IState supplyRaid() {
        if (getGame().canDoSupplyRaid()) {
            getGame().addSupplyCount();
            getGame().subtractActionPoint();
            if (getGame().immediateLossCheck()) {
                return new GameLost(getGame());
            }
        }
        return this;
    }
}
