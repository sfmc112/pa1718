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
public class StartGame extends StateAdapter {

    public StartGame(GameData game) {
        super(game);
    }

    @Override
    public IState startGame() {
        getGame().shuffleDeck();
        return new AwaitDrawCard(getGame());
    }

}
