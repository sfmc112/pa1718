/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.controller;

import java.io.Serializable;
import pkg9cs.model.EnemyBoard;
import pkg9cs.model.GameData;
import pkg9cs.model.StatusBoard;
import pkg9cs.model.elements.*;
import pkg9cs.states.IState;
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class GameController implements Serializable, GameEnums {

    private GameData game;
    private IState state;

    public GameController() {
        game = new GameData();
        setState(new StartGame(game));
    }

    public GameData getGame() {
        return game;
    }

    public void setGame(GameData game) {
        this.game = game;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    /**
     * ****************************************************************************
     */
    public void newGame() {
        setState(state.startGame());
    }

    public void endGame() {
        setState(state.endGame());
        game = state.getGame();
    }

    public void drawCard() {
        setState(state.executeCard());
    }

    public void checkEnemiesArchers() {
        setState(state.checkEnemiesArchers());
    }

    public void checkEnemiesBoilingWater() {
        setState(state.checkEnemiesBoilingWater());
    }

    public void checkEnemiesCloseCombat() {
        setState(state.checkEnemiesCloseCombat());
    }

    public void endTurn() {
        setState(state.endOfTurn());
    }

    public void coupure() {
        setState(state.coupure());
    }

    public void askUseOfSupply() {
        setState(state.askUseOfSupply());
    }

    public void selectTunnelMov() {
        setState(state.selectTunnelMov());
    }

    public void moveInTunnel() {
        setState(state.moveInTunnel());
    }

    public void freeMovement() {
        setState(state.freeMovement());
    }

    public void fastMovement() {
        setState(state.fastMovement());
    }

    public void supplyRaid() {
        setState(state.supplyRaid());
    }

    public void archersAttack(Enemies weapon) {
        setState(state.archersAttack(weapon));
    }

    public void boilingWaterAttack(Enemies weapon) {
        setState(state.boilingWaterAttack(weapon));
    }

    public void closeCombatAttack(Enemies weapon) {
        setState(state.closeCombatAttack(weapon));
    }

    public void sabotage() {
        setState(state.sabotage());
    }

    public void returnToMenu() {
        setState(state.returnToMenu());
    }

    public void buyActionPoint(Status element) {
        setState(state.buyActionPoint(element));
    }

    public void rallyTroops(Status supply) {
        setState(state.rallyTroops(supply));
    }

    public void rallyTroops() {
        setState(state.rallyTroops());
    }

    public void askAddActionPoint() {
        setState(state.askAddActionPoint());
    }

    public String statusBoard() {
        return game.getEnemyAndStatusBoard();
    }

    public boolean canDoArchers() {
        return game.canDoArchers();
    }

    public boolean canDoBoiling() {
        return game.canDoBoiling();
    }

    public boolean canDoCloseCombat() {
        return game.canDoCloseCombat();
    }

    public boolean canDoCoupure() {
        return game.canDoCoupure();
    }

    public boolean canDoRallyTroops() {
        return game.canDoRallyTroops();
    }

    public boolean canDoSupplyRaid() {
        return game.canDoSupplyRaid();
    }

    public boolean canDoSabotage() {
        return game.canDoSabotage();
    }

    public boolean canMoveIntoTunnel() {
        return game.canMoveIntoTunnel();
    }

    public boolean canDoFreeMovement() {
        return game.canDoFreeMovement();
    }

    public boolean canDoFastMovement() {
        return game.canDoFastMovement();
    }

    public boolean canDoBuyActionPoint() {
        return game.canDoBuyActionPoint();
    }

    public boolean checkAP() {
        return game.checkAP();
    }

    public EnemyBoard getEnemyB() {
        return game.getEnemyB();
    }

    public StatusBoard getStatusB() {
        return game.getStatusB();
    }
   
    public boolean isLadderOnStartingSpace() {
        return game.isLadderOnStartingSpace();
    }

    public boolean isBatteringRamOnStartingSpace() {
        return game.isBatteringRamOnStartingSpace();
    }

    public boolean isSiegeTowerOnStartingSpace() {
        return game.isSiegeTowerOnStartingSpace();
    }

    public boolean isLadderOnCloseCombatSpace() {
        return game.isLadderOnCloseCombatSpace();
    }

    public boolean isBatteringRamOnCloseCombatSpace() {
        return game.isBatteringRamOnCloseCombatSpace();
    }

    public boolean isSiegeTowerOnCloseCombatSpace() {
        return game.isSiegeTowerOnCloseCombatSpace();
    }

    public boolean isLadderOnCircleSpace() {
        return game.isLadderOnCircleSpace();
    }

    public boolean isBatteringRamOnCircleSpace() {
        return game.isBatteringRamOnCircleSpace();
    }

    public boolean isSiegeTowerOnCircleSpace() {
        return game.isSiegeTowerOnCircleSpace();
    }

    public boolean checkAvailableSupplies() {
        return game.checkAvailableSupplies();
    }

    public boolean checkAvailableMorale() {
        return game.checkAvailableMorale();
    }
    

    public String getEnemyAndStatusBoard() {
        return game.getEnemyAndStatusBoard();
    }

    public int getActiveCardNumber() {
        return game.getActiveCardNumber();
    }

    public int getTurnNumber() {
        return game.getTurnNumber();
    }
    
    

    public String printMSG() {
        return game.printMSG();
    }

    
}
