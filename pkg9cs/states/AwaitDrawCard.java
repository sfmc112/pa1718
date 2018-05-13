/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import java.util.logging.Level;
import java.util.logging.Logger;
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

        if (getGame().checkSoldiersOnEnemyLine()) {
            if (GameData.Die.rollDie() == 1) {
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
