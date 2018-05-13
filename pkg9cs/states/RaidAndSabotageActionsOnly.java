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
public class RaidAndSabotageActionsOnly extends StateAdapter {

    public RaidAndSabotageActionsOnly(GameData game) {
        super(game);
    }

    @Override
    public IState supplyRaid() {
        if (getGame().checkAP() && getGame().checkSoldiersOnEnemyLine() && !getGame().suppliesFull()) {
            getGame().addSupplyCount();
            getGame().subtractActionPoint();
            return this;
        }
        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        }
        return this;
    }

    @Override
    public IState sabotage() {
        if (getGame().checkAP() && getGame().checkSoldiersOnEnemyLine() && getGame().existsTrebuchets()) {
            getGame().sabotage();
            getGame().subtractActionPoint();
            return this;
        }
        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        }
        return this;
    }

    @Override
    public IState askAddActionPoint() {
        if (!getGame().checkAP() && !getGame().isUsedExtraAP() && getGame().checkAvailableResources()) {
            return new AwaitAddActionPoint(getGame());
        }
        return this;
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
    
    

}
