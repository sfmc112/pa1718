/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;
import pkg9cs.model.elements.Element;

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
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IState rallyTroops(Element e) { //get opt?
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IState returnToMenu() {
        return new AwaitAction(getGame());
    }
}
