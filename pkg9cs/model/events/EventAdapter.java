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
public class EventAdapter implements Event{

    @Override
    public void executeEvent(GameData game) {
        
    }

    @Override
    public int getSabotageDRM() {
        return 0;
    }

    @Override
    public int getMoraleDRM() {
        return 0;
    }

    @Override
    public int getCircleSpaceDRM() {
        return 0;
    }

    @Override
    public int getCloseCombatSpaceDRM() {
        return 0;
    }

    @Override
    public int getSupplyRaidDRM() {
        return 0;
    }

    @Override
    public int getCoupureDRM() {
        return 0;
    }

    @Override
    public int getLadderDRM() {
        return 0;
    }

    @Override
    public int getRamDRM() {
        return 0;
    }

    @Override
    public int getSiegeTowerDRM() {
        return 0;
    }
    
}
