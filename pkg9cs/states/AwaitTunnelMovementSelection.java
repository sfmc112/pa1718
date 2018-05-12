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
        //TODO
        return super.moveInTunnel(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public IState returnToMenu() {
        return new AwaitAction(getGame());
    }
}
