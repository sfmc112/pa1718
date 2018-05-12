/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import java.io.Serializable;
import pkg9cs.model.elements.SiegeTower;
import pkg9cs.model.elements.Ladder;
import pkg9cs.model.elements.Ram;

/**
 *
 * @author sarah
 */
public class EnemyBoard implements Serializable {

    private EnemyTrack ladders;
    private EnemyTrack rams;
    private EnemyTrack towers;

    private boolean towerPresent;

    private int trebuchetCount;

    public EnemyBoard() {

        ladders = new EnemyTrack(new Ladder());
        rams = new EnemyTrack(new Ram());
        towers = new EnemyTrack(new SiegeTower());
        towerPresent = true;
        trebuchetCount = 3;
    }

    public int getLadderPos() {
        return ladders.whereIsElement();
    }

    public int getRamPos() {
        return rams.whereIsElement();
    }

    public int getTowerPos() {
        if (towerPresent) {
            return towers.whereIsElement();
        } else {
            //TODO exception
            return -1;
        }
    }

    public boolean isTowerPresent() {
        return towerPresent;
    }

    public void advanceLadder() {
        ladders.advanceElement();
    }

    public void retreatLadder() {
        ladders.retreatElement();
    }

    public void advanceRam() {
        rams.advanceElement();
    }

    public void retreatRam() {
        rams.retreatElement();
    }

    public void advanceTower() {
        if (towerPresent) {
            towers.advanceElement();
        }
    }

    public void retreatTower() {
        if (towerPresent) {
            towers.retreatElement();
        }
    }

    /**
     * Verifica as posições dos inimigos mais recuados e fá-los avançar
     *
     * @see Track
     */
    public void advanceSlowestEnemies() {
        int[] indexes = new int[3];

        boolean[] toAdvance = new boolean[3];
        for (int i = 0; i < toAdvance.length; i++) {
            toAdvance[i] = false;
        } //array de booleans para indicar quais os inimogos que vão avançar

        int maxIndex;

        indexes[0] = maxIndex = getLadderPos();
        indexes[1] = getRamPos();
        indexes[2] = getTowerPos();

        // 1º - Verificar o índice do array da Track mais elevado (pode haver vários inimigos numa posição de track semelhante)
        for (int i = 1; i < indexes.length; i++) {
            if (indexes[i] > maxIndex) {
                maxIndex = indexes[i];
            }
        }

        //2º - Verificar os inimigos que vão avançar
        for (int i = 0; i < toAdvance.length; i++) {
            if (indexes[i] == maxIndex) {
                toAdvance[i] = true;
            }
        }

        // 3º - Fazer avançar os inimigos
        if (toAdvance[0] == true) {
            advanceLadder();
        }
        if (toAdvance[1] == true) {
            advanceRam();
        }
        if (toAdvance[2] == true) {
            advanceTower();
        }
    }

    public int getTrebuchetCount() {
        return trebuchetCount;
    }

    public void setTrebuchetCount(int trebuchetCount) {
        this.trebuchetCount = trebuchetCount;
    }

    public void addTrebuchetCount() {
        if (getTrebuchetCount() < 3) {
            setTrebuchetCount(trebuchetCount + 1);
        }
    }

    public void subtractTrebuchetCount() {
        if (getTrebuchetCount() > 0) {
            setTrebuchetCount(trebuchetCount - 1);
        }
    }

    public boolean isTowerOnStartingSpace() {
        return towers.onStartingSpace();
    }

    public void removeSiegeTower() {
        towers.removeElement();
        towerPresent = false;
    }

    public int countEnemiesOnCloseCombat() {
        int numEnemies = 0;
        if (ladders.getEnemyOnCloseCombatSpace()) {
            numEnemies++;
        }
        if (rams.getEnemyOnCloseCombatSpace()) {
            numEnemies++;
        }
        if (towerPresent && towers.getEnemyOnCloseCombatSpace()) {
            numEnemies++;
        }

        return numEnemies;
    }

    public int countEnemiesOnCircleSpace() {
        int numEnemies = 0;
        if (ladders.getEnemyOnCircleSpace()) {
            numEnemies++;
        }
        if (rams.getEnemyOnCircleSpace()) {
            numEnemies++;
        }
        if (towerPresent && towers.getEnemyOnCircleSpace()) {
            numEnemies++;
        }

        return numEnemies;
    }

    public int countEnemiesOnStartingPosition() {
        int numEnemies = 0;

        if (ladders.onStartingSpace()) {
            numEnemies++;
        }
        if (rams.onStartingSpace()) {
            numEnemies++;
        }
        if (towerPresent && towers.onStartingSpace()) {
            numEnemies++;
        }

        return numEnemies;
    }

    /**
     *
     * @return true se existem mais que dois inimigos no CloseCombat
     */
    public boolean checkImmediateLossOnCloseCombat() {
        return countEnemiesOnCloseCombat() >= 3;
    }

    public boolean checkEndOfTurnLossOnCloseCombat() {
        return countEnemiesOnCloseCombat() >= 2;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(ladders);
        str.append(rams);
        str.append(towerPresent ? towers : "");
        str.append("Trebuchet = ").append(trebuchetCount);
        return str.toString();
    }

    public boolean isLadderOnStartingSpace() {
        return ladders.onStartingSpace();
    }

    public boolean isBatteringRamOnStartingSpace() {
        return rams.onStartingSpace();
    }

    public boolean isSiegeTowerOnStartingSpace() {
        return towers.onStartingSpace();
    }

    public boolean isLadderOnCloseCombatSpace() {
        return ladders.getEnemyOnCloseCombatSpace();
    }

    public boolean isBatteringRamOnCloseCombatSpace() {
        return rams.getEnemyOnCloseCombatSpace();
    }

    public boolean isSiegeTowerOnCloseCombatSpace() {
        return towers.getEnemyOnCloseCombatSpace();
    }

    public boolean isLadderOnCircleSpace() {
        return ladders.getEnemyOnCircleSpace();
    }

    public boolean isBatteringRamOnCircleSpace() {
        return rams.getEnemyOnCircleSpace();
    }

    public boolean isSiegeTowerOnCircleSpace() {
        return towers.getEnemyOnCircleSpace();
    }

}
