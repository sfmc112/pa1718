/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;
import pkg9cs.model.elements.Supply;

/**
 *
 * @author sarah
 */
public class AwaitAddSupplyRallyTroops extends StateAdapter {

    public AwaitAddSupplyRallyTroops(GameData game) {
        super(game);
    }

    @Override
    public IState rallyTroops() {
        if (getGame().checkAP()) {
            getGame().rallyTroops(0);
            getGame().subtractActionPoint();
            return new AwaitAction(getGame());
        }
        return this;
    }

    @Override
    public IState rallyTroops(Supply supp) {
        if (getGame().checkAP() && getGame().checkAvailableSupplies()) {
            getGame().removeOneSupplyForRallyTroops();
            getGame().rallyTroops(1);
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
