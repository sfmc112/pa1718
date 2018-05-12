/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import java.io.Serializable;
import pkg9cs.model.elements.Supply;
import pkg9cs.model.elements.Morale;
import pkg9cs.model.elements.Soldiers;
import pkg9cs.model.elements.Wall;

/**
 *
 * @author sarah
 */
public class StatusBoard implements Serializable {

    private StatusTrack wall;
    private StatusTrack morale;
    private StatusTrack supplies;

    private InvadingTrack soldierLine;
    private int supplyCount;

    public StatusBoard() {
        wall = new StatusTrack(new Wall());
        morale = new StatusTrack(new Morale());
        supplies = new StatusTrack(new Supply());
        
        soldierLine = new InvadingTrack(new Soldiers());
        supplyCount = 0; //TODO
    }

    public int getWallPos() {
        return wall.whereIsElement();
    }

    public int getMoralePos() {
        return morale.whereIsElement();
    }

    public int getSupplyPos() {
        return supplies.whereIsElement();
    }

    public void advanceWall() {
        wall.advanceElement();
    }

    public void retreatWall() {
        wall.retreatElement();
    }

    public void advanceMorale() {
        morale.advanceElement();
    }

    public void retreatMorale() {
        morale.retreatElement();
    }

    public void advanceSupply() {
        supplies.advanceElement();
    }

    public void retreatSupply() {
        supplies.retreatElement();
    }

    public void captureSoldiers() {
        supplyCount = 0;
        soldierLine.captureSoldiers();
    }

    public int countStatusOnSurrender() {
        int numStatus = 0;

        if (wall.checkSurrender()) {
            numStatus++;
        }
        if (morale.checkSurrender()) {
            numStatus++;
        }
        if (supplies.checkSurrender()) {
            numStatus++;
        }

        return numStatus;
    }

    public boolean checkImmediateLossOnSurrender() {
        return countStatusOnSurrender() >= 2;
    }

    public boolean checkEndOfTurnLossOnSurrender() {
        return countStatusOnSurrender() >= 1;
    }

    public boolean checkAvailableResources() {
        return (checkAvailableMorale() || checkAvailableSupplies());
    }

    public boolean checkAvailableMorale() {
        return morale.whereIsElement() > 1;
    }

    public boolean checkAvailableSupplies() {
        return supplies.whereIsElement() > 1;
    }

    public boolean checkSoldiersOnEnemyLine() {
        return soldierLine.areSoldiersOnEnemyLines();
    }

    public boolean wallOnStartingSpace() {
        return wall.onStartingSpace();
    }

    public boolean moraleOnStartingSpace() {
        return morale.onStartingSpace();
    }

    public boolean suppliesOnStartingSpace() {
        return supplies.onStartingSpace();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(wall);
        str.append(morale);
        str.append(supplies);
        str.append(soldierLine);
        str.append("Supplies raider = ").append(supplyCount).append("\n");
        return str.toString();
    }

}
