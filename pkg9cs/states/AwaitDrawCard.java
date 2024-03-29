/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.RaidAndSabotageException;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class AwaitDrawCard extends StateAdapter {

    public AwaitDrawCard(GameData game) {
        super(game);
    }

    @Override
    public IState executeCard() {
        int dieResult;

        if (getGame().checkSoldiersOnEnemyLine()) {
            dieResult = GameData.Die.rollDie();
            getGame().setDie(dieResult);
            
            if (dieResult == 1) {
                getGame().captureSoldiers();
            }
        }

        try {
            getGame().executeCard();
        } catch (RaidAndSabotageException ex) {
            return new RaidAndSabotageActionsOnly(getGame());
        }

        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        } else if (getGame().twoEnemiesOnCloseCombat()) {
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        }
        return new AwaitAction(getGame());
    }

}
