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
public class GameController implements Serializable {

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

    public void archersAttack(Weapon weapon) {
        setState(state.archersAttack(weapon));
    }

    public void boilingWaterAttack(Weapon weapon) {
        setState(state.boilingWaterAttack(weapon));
    }

    public void closeCombatAttack(Weapon weapon) {
        setState(state.closeCombatAttack(weapon));
    }

    public void sabotage() {
        setState(state.sabotage());
    }

    public void returnToMenu() {
        setState(state.returnToMenu());
    }

    public void buyActionPoint(Element element) {
        setState(state.buyActionPoint(element));
    }

    public void rallyTroops(Supply supply) {
        setState(state.rallyTroops(supply));
    }

    public void rallyTroops() {
        setState(state.rallyTroops());
    }

    public void askAddActionPoint() {
        setState(state.askAddActionPoint());
    }

//    /**
//     * Start Game Menu
//     *
//     * @return String com o menu preparado para ser apresentado ao utilizador
//     */
//    public String startGameMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append("\t1- New Game\n");
//        str.append("\t2- Load Game\n");
//        str.append("\t3- Quit Game\n");
//        return str.toString();
//    }
//
//    /**
//     * Draw Card Menu
//     *
//     * @return String com o menu preparado para ser apresentado ao utilizador
//     */
//    public String drawCardMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append("\t1- Draw Card\n");
//        str.append("\t2- Save Game\n");
//        str.append("\t3- Load Game\n");
//        str.append("\t4- Show Game Status\n");
//        str.append("\t5- Quit Game\n");
//        return str.toString();
//    }
//
//    /**
//     * Await Action Menu
//     *
//     * @return String com o menu preparado para ser apresentado ao utilizador
//     */
//    public String awaitActionMenu() {
//
//        StringBuilder str = new StringBuilder();
//        str.append(game);
//        str.append("\nAvailable actions:\n");
//        if (canDoArchers()) {
//            str.append("\t1- Archers\n");
//        }
//        if (canDoBoiling()) {
//            str.append("\t2- Boiling\n");
//        }
//        if (canDoCloseCombat()) {
//            str.append("\t3- Close Combat\n");
//        }
//        if (canDoCoupure()) {
//            str.append("\t4- Coupure\n");
//        }
//        if (canDoRallyTroops()) {
//            str.append("\t5- Rally Troops\n");
//        }
//        if (canMoveIntoTunnel() || canDoFastMovement() || canDoFreeMovement()) {
//            str.append("\t6- Tunnel Movement\n");
//        }
//        if (canDoSupplyRaid()) {
//            str.append("\t7- Supply Raid\n");
//        }
//        if (canDoSabotage()) {
//            str.append("\t8- Sabotage\n");
//        }
//        if (canDoBuyActionPoint()) {
//            str.append("\t9- Buy Action Point\n");
//        }
//        str.append("\t10- End Turn\n");
//        str.append("\t11- Save Game\n");
//        str.append("\t12- Load Game\n");
//        str.append("\t13- Quit Game\n");
//
//        return str.toString();
//
//    }
//
//    public String archersMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getGame().getEnemyB()).append("\n");
//        str.append(getGame().isLadderOnStartingSpace() ? "" : "\t1- Ladder\n");
//        str.append(getGame().isBatteringRamOnStartingSpace() ? "" : "\t2- Battering Ram\n");
//        str.append(getGame().isSiegeTowerOnStartingSpace() ? "" : "\t3- Siege Tower\n");
//        str.append("\t4- Return to menu\n");
//        return str.toString();
//    }
//
//    public String boilingAttackMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getGame().getEnemyB()).append("\n");
//        str.append(getGame().isLadderOnCircleSpace() ? "\t1- Ladder\n" : "");
//        str.append(getGame().isBatteringRamOnCircleSpace() ? "\t2- Battering Ram\n" : "");
//        str.append(getGame().isSiegeTowerOnCircleSpace() ? "\t3- Siege Tower\n" : "");
//        str.append("\t4- Return to menu\n");
//        return str.toString();
//    }
//
//    public String closeCombatAttackMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getGame()).append("\n");
//        str.append(getGame().isLadderOnCloseCombatSpace() ? "\t1- Ladder\n" : "");
//        str.append(getGame().isBatteringRamOnCloseCombatSpace() ? "\t2- Battering Ram\n" : "");
//        str.append(getGame().isSiegeTowerOnCloseCombatSpace() ? "\t3- Siege Tower\n" : "");
//        str.append(canDoBuyActionPoint() ? "" : "\t4- Buy action point\n");
//        str.append("\t5- Return to menu\n");
//        return str.toString();
//    }
//
//    public String tunnelMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getGame().getStatusB()).append("\n");
//        str.append(canMoveIntoTunnel() ? "\t1- Move into Tunnel\n" : "");
//        str.append(canDoFreeMovement() ? "\t2- Free Movement\n" : "");
//        str.append(canDoFastMovement() ? "\t3- Fast Movement\n" : "");
//        str.append("\t4- Return to menu\n");
//        return str.toString();
//    }
//
//    public String winMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getEnemyAndStatusBoard());
//        str.append("\nCongratulations! You have repelled the invaders!\n");
//        str.append("\t1- Start New Game\n");
//        str.append("\t2- Quit Game\n");
//        return str.toString();
//    }
//
//    public String lostMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getEnemyAndStatusBoard());
//        str.append("\nYou couldn't resist the invasion!\n");
//        str.append("\t1- Start New Game\n");
//        str.append("\t2- Quit Game\n");
//        return str.toString();
//    }
//
//    public String addActionPointMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getEnemyAndStatusBoard());
//        str.append(canDoBuyActionPoint() && getGame().checkAvailableSupplies() ? "\t1- Use one Supply\n" : "");
//        str.append(canDoBuyActionPoint() && getGame().checkAvailableMorale() ? "\t2- Use one Morale\n" : "");
//        str.append("\t3- End Turn\n");
//        str.append("\t4- Return to menu\n");
//        return str.toString();
//    }
//
//    public String rallyTroopsMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(canDoRallyTroops() && getGame().checkAvailableSupplies() ? "\t1- Use one supply\n" : "");
//        str.append(canDoRallyTroops() ? "\t2- Rally troops\n" : "").append("\t3- Return to menu\n");
//        return str.toString();
//    }
//
//    public String raidAndSabotageMenu() {
//        StringBuilder str = new StringBuilder();
//        str.append(getGame()).append("\n");
//        str.append(canDoSupplyRaid() ? "\t1- Supply Raid\n" : "");
//        str.append(canDoSabotage() ? "\t2- Sabotage\n" : "");
//        str.append(canDoBuyActionPoint() ? "\t3- Buy Action Point\n" : "");
//        str.append("\t4- End Turn\n");
//        return str.toString();
//    }

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

    public String printMSG() {
        return game.printMSG();
    }

    
}
