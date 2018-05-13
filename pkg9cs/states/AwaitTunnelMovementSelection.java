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
public class AwaitTunnelMovementSelection extends StateAdapter {

    public AwaitTunnelMovementSelection(GameData game) {
        super(game);
    }

    @Override
    public IState moveInTunnel() {
        if (getGame().checkAP() && (getGame().checkSoldiersInCastle() || getGame().checkSoldiersOnEnemyLine())) {
            getGame().moveInTunnel();
            getGame().subtractActionPoint();
            return new AwaitAction(getGame());
        }
        return this;
    }

    @Override
    public IState freeMovement() {
        if (getGame().isCanUseFreeMovement() && !getGame().checkSoldiersInCastle() && !getGame().checkSoldiersOnEnemyLine()) {
            getGame().freeTunnelMovement();
            return new AwaitAction(getGame());
        }
        return this;
    }

    @Override
    public IState fastMovement() {
        if (getGame().checkAP() && !getGame().checkSoldiersInCastle() && !getGame().checkSoldiersOnEnemyLine()) {
            getGame().fastTunnelMovement();
            getGame().subtractActionPoint();
            return new AwaitAction(getGame());
        }
        return this;
    }

    @Override
    public IState returnToMenu() {
        return new AwaitAction(getGame());
    }
}
