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
public class AwaitAddActionPoint extends StateAdapter{
    
    public AwaitAddActionPoint(GameData game) {
        super(game);
    }

    @Override
    public IState endOfTurn() {
        //TODO
        return super.endOfTurn(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IState buyActionPoint() {
        getGame().addActionPoint();
        
        if(getGame().twoEnemiesOnCloseCombat())
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        return new AwaitAction(getGame());
    }
    
    
    
}
