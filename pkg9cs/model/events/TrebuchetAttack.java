/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.events;

import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class TrebuchetAttack extends Event {

    @Override
    public void executeEvent(GameData game) {

        //System.out.println("Atacou os trebuchets");
        switch (game.getEnemyB().getTrebuchetCount()) {
            case 3:
                game.getStatusB().advanceWall();
                game.getStatusB().advanceWall();
                break;
            case 2:
                game.getStatusB().advanceWall();
                break;
            default:
                switch (GameData.Die.rollDie()) {
                    case 1:
                    case 2:
                    case 3:
                        break;
                    default:
                        game.getStatusB().advanceWall();
                        break;
                }
        }
    }
}
