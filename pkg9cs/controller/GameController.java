/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.controller;

import java.io.Serializable;
import java.util.Observable;
import pkg9cs.model.GameData;
import pkg9cs.model.elements.*;
import pkg9cs.states.IState;
import pkg9cs.states.StartGame;
import pkg9cs.ui.TextUI;

/**
 *
 * @author sarah
 */
public class GameController extends Observable implements Serializable {

    private GameData game;
    private IState state;
    private TextUI textUI;

    public GameController(TextUI textUI) {
        this.textUI = textUI;
        addObserver(this.textUI);
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
        setChanged();
        notifyObservers();
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
        setChanged();
        notifyObservers();
    }

    public void boilingWaterAttack(Weapon weapon) {
        setState(state.boilingWaterAttack(weapon));
        setChanged();
        notifyObservers();
    }

    public void closeCombatAttack(Weapon weapon) {
        setState(state.closeCombatAttack(weapon));
        setChanged();
        notifyObservers();
    }

    public void sabotage() {
        setState(state.sabotage());
        setChanged();
        notifyObservers();
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

    /**
     * Start Game Menu
     *
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    public String startGameMenu() {
        StringBuilder str = new StringBuilder();
        str.append("\t1- New Game\n");
        str.append("\t2- Load Game\n");
        str.append("\t3- Quit Game\n");
        return str.toString();
    }

    /**
     * Draw Card Menu
     *
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    public String drawCardMenu() {
        StringBuilder str = new StringBuilder();
        str.append("\t1- Draw Card\n");
        str.append("\t2- Save Game\n");
        str.append("\t3- Load Game\n");
        str.append("\t4- Show Game Status\n");
        str.append("\t5- Quit Game\n");
        return str.toString();
    }

    /**
     * Await Action Menu
     *
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    public String awaitActionMenu() {

        StringBuilder str = new StringBuilder();
        str.append(game);
        str.append("\nAvailable actions:\n");
        if (canDoArchers()) {
            str.append("\t1- Archers\n");
        }
        if (canDoBoiling()) {
            str.append("\t2- Boiling\n");
        }
        if (canDoCloseCombat()) {
            str.append("\t3- Close Combat\n");
        }
        if (canDoCoupure()) {
            str.append("\t4- Coupure\n");
        }
        if (canDoRallyTroops()) {
            str.append("\t5- Rally Troops\n");
        }
        str.append("\t6- Tunnel Movement\n");
        if (canDoSupplyRaid()) {
            str.append("\t7- Supply Raid\n");
        }
        if (canDoSabotage()) {
            str.append("\t8- Sabotage\n");
        }
        if (!game.checkAP() && !game.isUsedExtraAP()) {
            str.append("\t9- Buy Action Point\n");
        }
        str.append("\t10- End Turn\n");
        str.append("\t11- Save Game\n");
        str.append("\t12- Load Game\n");
        str.append("\t13- Quit Game\n");

        return str.toString();

    }

    public String archersMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(getGame().getEnemyB().isLadderOnStartingSpace() ? "" : "\t1- Ladder\n");
        str.append(getGame().getEnemyB().isBatteringRamOnStartingSpace() ? "" : "\t2- Battering Ram\n");
        str.append(getGame().getEnemyB().isSiegeTowerOnStartingSpace() ? "" : "\t3- Siege Tower\n");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String boilingAttackMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(getGame().getEnemyB().isLadderOnCircleSpace() ? "\t1- Ladder\n" : "");
        str.append(getGame().getEnemyB().isBatteringRamOnCircleSpace() ? "\t2- Battering Ram\n" : "");
        str.append(getGame().getEnemyB().isSiegeTowerOnCircleSpace() ? "\t3- Siege Tower\n" : "");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String closeCombatAttackMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(getGame().getEnemyB().isLadderOnCloseCombatSpace() ? "\t1- Ladder\n" : "");
        str.append(getGame().getEnemyB().isBatteringRamOnCloseCombatSpace() ? "\t2- Battering Ram\n" : "");
        str.append(getGame().getEnemyB().isSiegeTowerOnCloseCombatSpace() ? "\t3- Siege Tower\n" : "");
        str.append(getGame().checkAP() && getGame().isUsedExtraAP() ? "" : "\t4- Buy action point\n");
        str.append("\t5- Return to menu\n");
        return str.toString();
    }

    public String tunnelMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(canMoveIntoTunnel() ? "\t1- Move into Tunnel\n" : "");
        str.append(canDoFreeMovement() ? "\t2- Free Movement\n" : "");
        str.append(canDoFastMovement() ? "\t3- Fast Movement\n" : "");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String winMenu() {
        StringBuilder str = new StringBuilder();
        str.append(statusBoard());
        str.append("\nCongratulations! You have repelled the invaders!\n");
        str.append("\t1- Start New Game\n");
        str.append("\t2- Quit Game\n");
        return str.toString();
    }

    public String lostMenu() {
        StringBuilder str = new StringBuilder();
        str.append("\nYou couldn't resist the invasion!\n");
        str.append("\t1- Start New Game\n");
        str.append("\t2- Quit Game\n");
        return str.toString();
    }

    public String addActionPointMenu() {
        StringBuilder str = new StringBuilder();
        str.append(statusBoard());
        str.append(game.checkAvailableSupplies() ? "\t1- Use one Supply\n" : "");
        str.append(game.checkAvailableMorale() ? "\t2- Use one Morale\n" : "");
        str.append("\t3- End Turn\n");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String rallyTroopsMenu() {
        StringBuilder str = new StringBuilder();
        str.append(game.checkAvailableSupplies() ? "\t1- Use one supply\n" : "");
        str.append("\t2- Rally troops\n\t3- Return to menu\n");
        return str.toString();
    }

    public String raidAndSabotageMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(canDoSupplyRaid() ? "\t1- Supply Raid\n" : "");
        str.append(canDoSabotage() ? "\t2- Sabotage\n" : "");
        str.append("\t3- End Turn\n");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String statusBoard() {
        StringBuilder str = new StringBuilder();
        str.append(game.getEnemyB()).append("\n\n").append(game.getStatusB()).append("\n");
        return str.toString();
    }

    private boolean canDoArchers() {
        return (getGame().checkAP() && getGame().enemiesAttackable());
    }

    private boolean canDoBoiling() {
        return (getGame().checkAP() && getGame().enemiesOnCircleSpace() && !getGame().isUsedBoiling());
    }

    private boolean canDoCloseCombat() {
        return (getGame().checkAP() && getGame().enemiesOnCloseCombat());
    }

    private boolean canDoCoupure() {
        return (getGame().checkAP()) && (!(getGame().wallOnStartingSpace()));
    }

    private boolean canDoRallyTroops() {
        return (getGame().checkAP()) && (!(getGame().moraleOnStartingSpace()));
    }

    private boolean canDoSupplyRaid() {
        return (getGame().checkAP()) && (getGame().checkSoldiersOnEnemyLine());
    }

    private boolean canDoSabotage() {
        return (getGame().checkAP()) && (getGame().checkSoldiersOnEnemyLine());
    }

    private boolean canMoveIntoTunnel() {
        return getGame().checkAP() && (getGame().checkSoldiersInCastle() || getGame().checkSoldiersOnEnemyLine());
    }

    private boolean canDoFreeMovement() {
        return getGame().isCanUseFreeMovement() && !getGame().checkSoldiersInCastle() && !getGame().checkSoldiersOnEnemyLine();
    }

    private boolean canDoFastMovement() {
        return getGame().checkAP() && !getGame().checkSoldiersInCastle() && !getGame().checkSoldiersOnEnemyLine();
    }

    public String printMSG() {
        return game.printMSG();
    }

}
