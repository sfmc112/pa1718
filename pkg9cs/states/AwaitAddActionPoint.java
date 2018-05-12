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
public class AwaitAddActionPoint extends StateAdapter{
    
    public AwaitAddActionPoint(GameData game) {
        super(game);
    }

    @Override
    public IState endOfTurn() {
        if (getGame().endOfTurnLossCheck()) {
            return new GameLost(getGame());
        }
        if (getGame().deckHasCards()) {
            getGame().newTurnSetup();
            return new AwaitDrawCard(getGame());
        }
        if (getGame().checkIfWon()) { //End of Day procedures in this method
            return new GameLost(getGame());
        }
        //Next Day
        return new AwaitDrawCard(getGame());
    }

    @Override
    public IState buyActionPoint(Element e) {
        if(getGame().addActionPoint(e)){
            
        }
        
        if(getGame().twoEnemiesOnCloseCombat())
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        return new AwaitAction(getGame());
    }

    @Override
    public IState returnToMenu() {
        if(getGame().twoEnemiesOnCloseCombat())
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        return new AwaitAction(getGame());
    }
    
    
    
}
