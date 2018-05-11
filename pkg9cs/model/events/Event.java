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
//TODO
public abstract class Event {

    public void executeEvent(GameData game){
        
    }

    public int getSabotageDRM() {
        return 0;
    }

    public int getMoraleDRM() {
        return 0;
    }

    public int getCircleSpaceDRM() {
        return 0;
    }

    public int getCloseCombatSpaceDRM() {
        return 0;
    }

    public int getSupplyRaidDRM() {
        return 0;
    }

    public int getCoupureDRM() {
        return 0;
    }

    public int getLadderDRM() {
        return 0;
    }

    public int getRamDRM() {
        return 0;
    }

    public int getSiegeTowerDRM() {
        return 0;
    }

}
