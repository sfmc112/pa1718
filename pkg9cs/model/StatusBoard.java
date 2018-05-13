/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import java.io.Serializable;
import pkg9cs.model.boardspaces.Castle;
import pkg9cs.model.boardspaces.EnemyLine;
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
    private boolean soldiersHaveBeenOnEnemyLines;
    private int supplyCount;

    public StatusBoard() {
        wall = new StatusTrack(new Wall());
        morale = new StatusTrack(new Morale());
        supplies = new StatusTrack(new Supply());

        soldierLine = new InvadingTrack(new Soldiers());
        soldiersHaveBeenOnEnemyLines = false;
        supplyCount = 0; //TODO
    }

    public int getSupplyCount() {
        return supplyCount;
    }

    public boolean isSoldiersHaveBeenOnEnemyLines() {
        return soldiersHaveBeenOnEnemyLines;
    }

    public void addSupplyCount(int quantity) {
        if (supplyCount + quantity > 2) {
            supplyCount = 2;
        } else {
            supplyCount += quantity;
        }
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

    public void moveSoldiersTowardsEnemyLines() {
        soldierLine.retreatElement();
    }

    public void moveSoldiersTowardsCastle() {
        soldierLine.advanceElement();
    }

    public void moveSoldiersThroughTunnel() {
        if (soldiersHaveBeenOnEnemyLines) {
            moveSoldiersTowardsCastle();
            if (checkSoldiersInCastle()) {
                soldiersArrivedAtCastle();
            }
        } else {
            moveSoldiersTowardsEnemyLines();
            if (checkSoldiersOnEnemyLine()) {
                soldiersArrivedAtEnemyLines();
            }
        }
    }

    private void replenishSupplies() {
        while (supplyCount-- > 0) {
            supplies.retreatElement();
        }
        supplyCount = 0;
    }

    private void soldiersArrivedAtCastle() {
        soldiersHaveBeenOnEnemyLines = false;
        replenishSupplies();
    }
    
    private void soldiersArrivedAtEnemyLines(){
        soldiersHaveBeenOnEnemyLines = true;
    }

    public void moveSoldiersFast() {
        if (soldiersHaveBeenOnEnemyLines) {
            moveDirectlyToCastle();
        } else {
            moveDirectlyToEnemyLines();
        }
    }

    private void moveDirectlyToCastle() {
        while (!(soldierLine.getBoardspaceType() instanceof Castle)) {
            soldierLine.advanceElement();
        }
        soldiersArrivedAtCastle();
    }

    private void moveDirectlyToEnemyLines() {
        while (!(soldierLine.getBoardspaceType() instanceof EnemyLine)) {
            soldierLine.retreatElement();
        }
        soldiersArrivedAtEnemyLines();
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

    public boolean checkSoldiersInCastle() {
        return soldierLine.onStartingSpace();
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
        str.append("Supplies raided = ").append(supplyCount).append("\n");
        return str.toString();
    }

}
