/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;
import pkg9cs.model.elements.Weapon;

/**
 *
 * @author sarah
 */
public class AwaitEnemySelectionBoilingWaterAttack extends StateAdapter {

    public AwaitEnemySelectionBoilingWaterAttack(GameData game) {
        super(game);
    }

    @Override
    public IState boilingWaterAttack(Weapon weapon) {
        if (getGame().boilingWaterAttack(weapon)) {
            getGame().subtractActionPoint();
            
            if (getGame().immediateLossCheck()) {
                return new GameLost(getGame());
            } else {
                return new AwaitAction(getGame());
            }
        }

        return this;
    }

    @Override
    public IState returnToMenu() {
        return new AwaitAction(getGame());
    }

}
