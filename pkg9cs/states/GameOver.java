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
public class GameOver extends StateAdapter{
    
    public GameOver(GameData game) {
        super(game);
    }

    @Override
    public IState endGame() {
        if(getGame().getDayNumber()>=4){
            System.out.println("GameWon!");
            //return new GameWon(getGame());
        }
        return new StartGame(new GameData());
    }
    
    
    
}
