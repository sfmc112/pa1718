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
public class AwaitEnemySelectionArchersAttack extends StateAdapter {

    public AwaitEnemySelectionArchersAttack(GameData game) {
        super(game);
    }

    @Override
    public IState archersAttack(Enemies weapon) {
        if (getGame().archersAttack(weapon)) {
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
