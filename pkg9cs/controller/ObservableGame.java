/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg9cs.files.FileUtility;
import pkg9cs.model.EnemyBoard;
import pkg9cs.model.GameData;
import pkg9cs.model.StatusBoard;
import pkg9cs.model.elements.GameEnums;
import pkg9cs.states.IState;

/**
 *
 * @author sarah
 */
public class ObservableGame extends Observable implements GameEnums{
    private GameController gameC;

    public ObservableGame(GameController gameC) {
        this.gameC = gameC;
    }

    public GameController getGameC() {
        return gameC;
    }

    public void setGameC(GameController gameC) {
        this.gameC = gameC;
        
        setChanged();
        notifyObservers();
    }

    public GameData getGameData() {
        return gameC.getGame();
    }

    private void setGameData(GameData game) {
        gameC.setGame(game);
        
        setChanged();
        notifyObservers();
    }

    public IState getState() {
        return gameC.getState();
    }

    public void setState(IState state) {
        gameC.setState(state);
    }

    public void newGame() {
        gameC.newGame();
        
        setChanged();
        notifyObservers();
    }

    public void endGame() {
        gameC.endGame();
        
        setChanged();
        notifyObservers();
    }

    public void drawCard() {
        gameC.drawCard();
        
        setChanged();
        notifyObservers();
    }

    public void checkEnemiesArchers() {
        gameC.checkEnemiesArchers();
        
        setChanged();
        notifyObservers();
    }

    public void checkEnemiesBoilingWater() {
        gameC.checkEnemiesBoilingWater();
        
        setChanged();
        notifyObservers();
    }

    public void checkEnemiesCloseCombat() {
        gameC.checkEnemiesCloseCombat();
        
        setChanged();
        notifyObservers();
    }

    public void endTurn() {
        gameC.endTurn();
        
        setChanged();
        notifyObservers();
    }

    public void coupure() {
        gameC.coupure();
        
        setChanged();
        notifyObservers();
    }

    public void askUseOfSupply() {
        gameC.askUseOfSupply();
        
        setChanged();
        notifyObservers();
    }

    public void selectTunnelMov() {
        gameC.selectTunnelMov();
        
        setChanged();
        notifyObservers();
    }

    public void moveInTunnel() {
        gameC.moveInTunnel();
        
        setChanged();
        notifyObservers();
    }

    public void freeMovement() {
        gameC.freeMovement();
        
        setChanged();
        notifyObservers();
    }

    public void fastMovement() {
        gameC.fastMovement();
        
        setChanged();
        notifyObservers();
    }

    public void supplyRaid() {
        gameC.supplyRaid();
        
        setChanged();
        notifyObservers();
    }

    public void archersAttack(Enemies weapon) {
        gameC.archersAttack(weapon);
        
        setChanged();
        notifyObservers();
    }

    public void boilingWaterAttack(Enemies weapon) {
        gameC.boilingWaterAttack(weapon);
        
        setChanged();
        notifyObservers();
    }

    public void closeCombatAttack(Enemies weapon) {
        gameC.closeCombatAttack(weapon);
        
        setChanged();
        notifyObservers();
    }

    public void sabotage() {
        gameC.sabotage();
        
        setChanged();
        notifyObservers();
    }

    public void returnToMenu() {
        gameC.returnToMenu();
        
        setChanged();
        notifyObservers();
    }

    public void buyActionPoint(Status element) {
        gameC.buyActionPoint(element);
        
        setChanged();
        notifyObservers();
    }

    public void rallyTroops(Status supply) {
        gameC.rallyTroops(supply);
        
        setChanged();
        notifyObservers();
    }

    public void rallyTroops() {
        gameC.rallyTroops();
        
        setChanged();
        notifyObservers();
    }

    public void askAddActionPoint() {
        gameC.askAddActionPoint();
        
        setChanged();
        notifyObservers();
    }
    
    public boolean canDoArchers() {
        return gameC.canDoArchers();
    }

    public boolean canDoBoiling() {
        return gameC.canDoBoiling();
    }

    public boolean canDoCloseCombat() {
        return gameC.canDoCloseCombat();
    }

    public boolean canDoCoupure() {
        return gameC.canDoCoupure();
    }

    public boolean canDoRallyTroops() {
        return gameC.canDoRallyTroops();
    }

    public boolean canDoSupplyRaid() {
        return gameC.canDoSupplyRaid();
    }

    public boolean canDoSabotage() {
        return gameC.canDoSabotage();
    }

    public boolean canMoveIntoTunnel() {
        return gameC.canMoveIntoTunnel();
    }

    public boolean canDoFreeMovement() {
        return gameC.canDoFreeMovement();
    }

    public boolean canDoFastMovement() {
        return gameC.canDoFastMovement();
    }

    public boolean canDoBuyActionPoint() {
        return gameC.canDoBuyActionPoint();
    }

    public String getEnemyAndStatusBoard() {
        return gameC.getEnemyAndStatusBoard();
    }

    public boolean checkAvailableSupplies() {
        return gameC.checkAvailableSupplies();
    }

    public boolean checkAvailableMorale() {
        return gameC.checkAvailableMorale();
    }

    public boolean checkAP() {
        return gameC.checkAP();
    }
    

    public boolean isLadderOnStartingSpace() {
        return gameC.isLadderOnStartingSpace();
    }

    public boolean isBatteringRamOnStartingSpace() {
        return gameC.isBatteringRamOnStartingSpace();
    }

    public boolean isSiegeTowerOnStartingSpace() {
        return gameC.isSiegeTowerOnStartingSpace();
    }

    public boolean isLadderOnCloseCombatSpace() {
        return gameC.isLadderOnCloseCombatSpace();
    }

    public boolean isBatteringRamOnCloseCombatSpace() {
        return gameC.isBatteringRamOnCloseCombatSpace();
    }

    public boolean isSiegeTowerOnCloseCombatSpace() {
        return gameC.isSiegeTowerOnCloseCombatSpace();
    }

    public boolean isLadderOnCircleSpace() {
        return gameC.isLadderOnCircleSpace();
    }

    public boolean isBatteringRamOnCircleSpace() {
        return gameC.isBatteringRamOnCircleSpace();
    }

    public boolean isSiegeTowerOnCircleSpace() {
        return gameC.isSiegeTowerOnCircleSpace();
    }
    

    public EnemyBoard getEnemyB() {
        return gameC.getEnemyB();
    }

    public StatusBoard getStatusB() {
        return gameC.getStatusB();
    }

    public int getActiveCardNumber() {
        return gameC.getActiveCardNumber();
    }

    public int getTurnNumber() {
        return gameC.getTurnNumber();
    }

    public boolean twoEnemiesOnCloseCombat() {
        return gameC.twoEnemiesOnCloseCombat();
    }

    public int getDayNumber() {
        return gameC.getDayNumber();
    }

    public int getLadderPos() {
        return gameC.getLadderPos();
    }

    public int getRamPos() {
        return gameC.getRamPos();
    }

    public int getTowerPos() {
        return gameC.getTowerPos();
    }

    public int getTrebuchetCount() {
        return gameC.getTrebuchetCount();
    }

    public int getSupplyCount() {
        return gameC.getSupplyCount();
    }

    public int getWallPos() {
        return gameC.getWallPos();
    }

    public int getMoralePos() {
        return gameC.getMoralePos();
    }

    public int getSupplyPos() {
        return gameC.getSupplyPos();
    }

    public int getSoldiersPos() {
        return gameC.getSoldiersPos();
    }

    public int getDie() {
        return gameC.getDie();
    }
    

    public String printMSG() {
        return gameC.printMSG();
    }
    
    public boolean saveGame(String filename) {
        try {
            FileUtility.saveGameToFile(filename, gameC);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean loadGame(String filename) {
        try {
            setGameC((GameController)FileUtility.retrieveGameFromFile(filename));
            return true;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
