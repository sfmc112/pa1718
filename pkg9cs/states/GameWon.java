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
public class GameWon extends StateAdapter {

    public GameWon(GameData game) {
        super(game);
    }

    @Override
    public IState endGame() {
        return new StartGame(new GameData());
    }

}
