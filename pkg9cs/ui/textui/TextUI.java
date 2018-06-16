/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.textui;

import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import pkg9cs.controller.ObservableGame;
import pkg9cs.model.elements.*;
import pkg9cs.states.*;

/**
 *
 * @author sarah
 */
public class TextUI implements Observer, Serializable, GameEnums {

    ObservableGame observableGame;
    boolean run;

    public TextUI(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        this.run = true;
    }
    

    public void setObservableGame(ObservableGame observableGame) {
        this.observableGame = observableGame;
    }

    public void run() {
        IState state = null;
        while (run) {
            state = observableGame.getState();
            if (state instanceof StartGame) {
                startGameUi();
            } else if (state instanceof AwaitAction) {
                awaitActionUi();
            } else if (state instanceof AwaitAddActionPoint) {
                addActionPointMenuUi();
            } else if (state instanceof AwaitAddSupplyRallyTroops) {
                rallyTroopsMenuUi();
            } else if (state instanceof AwaitDrawCard) {
                drawCardUi();
            } else if (state instanceof AwaitEnemySelectionArchersAttack) {
                enemySelectionArchersUi();
            } else if (state instanceof AwaitEnemySelectionBoilingWaterAttack) {
                enemySelectionBoilingAttackUi();
            } else if (state instanceof AwaitEnemySelectionCloseCombatAttack) {
                enemySelectionCloseCombatUi();
            } else if (state instanceof AwaitTunnelMovementSelection) {
                tunnelMenuUi();
            } else if (state instanceof GameLost) {
                lostMenuUi();
            } else if (state instanceof GameWon) {
                winMenuUi();
            } else if (state instanceof RaidAndSabotageActionsOnly) {
                raidAndSabotageMenuUi();
            }
        }
    }

    private void startGameUi() {
        System.out.println(startGameMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.newGame();
                break;
            case 2:
                loadText();
                break;
            case 3:
                run = false;
        }
    }

    private void drawCardUi() {
        System.out.println(drawCardMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.drawCard();
                break;
            case 2:
                saveText();
                break;
            case 3:
                loadText();
                break;
            case 4:
                System.out.println(observableGame.getEnemyAndStatusBoard());
                break;
            case 5:
                run = false;
        }
    }

    private void awaitActionUi() {
        System.out.println(awaitActionMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.checkEnemiesArchers();
                break;
            case 2:
                observableGame.checkEnemiesBoilingWater();
                break;
            case 3:
                observableGame.checkEnemiesCloseCombat();
                break;
            case 4:
                observableGame.coupure();
                break;
            case 5:
                observableGame.askUseOfSupply();
                break;
            case 6:
                observableGame.selectTunnelMov();
                break;
            case 7:
                observableGame.supplyRaid();
                break;
            case 8:
                observableGame.sabotage();
                break;
            case 9:
                observableGame.askAddActionPoint();
                break;
            case 10:
                observableGame.endTurn();
                break;
            case 11:
                saveText();
                break;
            case 12:
                loadText();
                break;
            case 13:
                run = false;

        }
    }

    private void enemySelectionArchersUi() {
        System.out.println(archersMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.archersAttack(Enemies.LADDER);
                break;
            case 2:
                observableGame.archersAttack(Enemies.BATTERING_RAM);
                break;
            case 3:
                observableGame.archersAttack(Enemies.SIEGE_TOWER);
                break;
            case 4:
                observableGame.returnToMenu();
                break;
        }
    }

    private void tunnelMenuUi() {
        System.out.println(tunnelMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.moveInTunnel();
                break;
            case 2:
                observableGame.freeMovement();
                break;
            case 3:
                observableGame.fastMovement();
                break;
            case 4:
                observableGame.returnToMenu();
                break;
        }
    }

    private void enemySelectionBoilingAttackUi() {
        System.out.println(boilingAttackMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.boilingWaterAttack(Enemies.LADDER);
                break;
            case 2:
                observableGame.boilingWaterAttack(Enemies.BATTERING_RAM);
                break;
            case 3:
                observableGame.boilingWaterAttack(Enemies.SIEGE_TOWER);
                break;
            case 4:
                observableGame.returnToMenu();
                break;
        }
    }

    private void enemySelectionCloseCombatUi() {
        System.out.println(closeCombatAttackMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.closeCombatAttack(Enemies.LADDER);
                break;
            case 2:
                observableGame.closeCombatAttack(Enemies.BATTERING_RAM);
                break;
            case 3:
                observableGame.closeCombatAttack(Enemies.SIEGE_TOWER);
                break;
            case 4:
                observableGame.askAddActionPoint();
                break;
            case 5:
                observableGame.returnToMenu();
                break;

        }
    }

    private void winMenuUi() {
        System.out.println(winMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.endGame();
                break;
            case 2:
                run = false;
        }
    }

    private void lostMenuUi() {
        System.out.println(lostMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.endGame();
                break;
            case 2:
                run = false;
        }
    }

    private void addActionPointMenuUi() {
        System.out.println(addActionPointMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.buyActionPoint(Status.SUPPLY);
                break;
            case 2:
                observableGame.buyActionPoint(Status.MORALE);
                break;
            case 3:
                observableGame.endTurn();
                break;
            case 4:
                observableGame.returnToMenu();
        }
    }

    private void rallyTroopsMenuUi() {
        System.out.println(rallyTroopsMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.rallyTroops(Status.SUPPLY);
                break;
            case 2:
                observableGame.rallyTroops();
                break;
            case 3:
                observableGame.returnToMenu();
        }
    }

    private void raidAndSabotageMenuUi() {
        System.out.println(raidAndSabotageMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                observableGame.supplyRaid();
                break;
            case 2:
                observableGame.sabotage();
                break;
            case 3:
                observableGame.askAddActionPoint();
                break;
            case 4:
                observableGame.endTurn();
        }
    }

    private int readOption() {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            in.next();
        }
        return in.nextInt();
    }

    private void loadText() {
        System.out.print("\nName of the file to load:  ");
        String filename = readFileName();
        if (filename == null || filename.isEmpty()) {
            return;
        }

        if (observableGame.loadGame(filename)) {
            System.out.println("\nGame loaded.\n");
        }
    }

    private void saveText() {
        System.out.println("\nName of the file to save: ");
        String filename = readFileName();
        if (filename == null || filename.isEmpty()) {
            return;
        }

        if (observableGame.saveGame(filename)) {
            System.out.println("\nGame saved\n");
        }
    }
    
    private String readFileName() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(observableGame.printMSG());
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
        str.append(observableGame.getGameData());
        str.append("\nAvailable actions:\n");
        if (observableGame.canDoArchers()) {
            str.append("\t1- Archers\n");
        }
        if (observableGame.canDoBoiling()) {
            str.append("\t2- Boiling\n");
        }
        if (observableGame.canDoCloseCombat()) {
            str.append("\t3- Close Combat\n");
        }
        if (observableGame.canDoCoupure()) {
            str.append("\t4- Coupure\n");
        }
        if (observableGame.canDoRallyTroops()) {
            str.append("\t5- Rally Troops\n");
        }
        if (observableGame.canMoveIntoTunnel() || observableGame.canDoFastMovement() || observableGame.canDoFreeMovement()) {
            str.append("\t6- Tunnel Movement\n");
        }
        if (observableGame.canDoSupplyRaid()) {
            str.append("\t7- Supply Raid\n");
        }
        if (observableGame.canDoSabotage()) {
            str.append("\t8- Sabotage\n");
        }
        if (observableGame.canDoBuyActionPoint()) {
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
        str.append(observableGame.getEnemyB()).append("\n");
        str.append(observableGame.isLadderOnStartingSpace() ? "" : "\t1- Ladder\n");
        str.append(observableGame.isBatteringRamOnStartingSpace() ? "" : "\t2- Battering Ram\n");
        str.append(observableGame.isSiegeTowerOnStartingSpace() ? "" : "\t3- Siege Tower\n");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String boilingAttackMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getEnemyB()).append("\n");
        str.append(observableGame.isLadderOnCircleSpace() ? "\t1- Ladder\n" : "");
        str.append(observableGame.isBatteringRamOnCircleSpace() ? "\t2- Battering Ram\n" : "");
        str.append(observableGame.isSiegeTowerOnCircleSpace() ? "\t3- Siege Tower\n" : "");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String closeCombatAttackMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getGameC()).append("\n");
        str.append(observableGame.isLadderOnCloseCombatSpace() ? "\t1- Ladder\n" : "");
        str.append(observableGame.isBatteringRamOnCloseCombatSpace() ? "\t2- Battering Ram\n" : "");
        str.append(observableGame.isSiegeTowerOnCloseCombatSpace() ? "\t3- Siege Tower\n" : "");
        str.append(observableGame.canDoBuyActionPoint() ? "" : "\t4- Buy action point\n");
        str.append("\t5- Return to menu\n");
        return str.toString();
    }

    public String tunnelMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getStatusB()).append("\n");
        str.append(observableGame.canMoveIntoTunnel() ? "\t1- Move into Tunnel\n" : "");
        str.append(observableGame.canDoFreeMovement() ? "\t2- Free Movement\n" : "");
        str.append(observableGame.canDoFastMovement() ? "\t3- Fast Movement\n" : "");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String winMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getEnemyAndStatusBoard());
        str.append("\nCongratulations! You have repelled the invaders!\n");
        str.append("\t1- Start New Game\n");
        str.append("\t2- Quit Game\n");
        return str.toString();
    }

    public String lostMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getEnemyAndStatusBoard());
        str.append("\nYou couldn't resist the invasion!\n");
        str.append("\t1- Start New Game\n");
        str.append("\t2- Quit Game\n");
        return str.toString();
    }

    public String addActionPointMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getEnemyAndStatusBoard());
        str.append(observableGame.canDoBuyActionPoint() && observableGame.checkAvailableSupplies() ? "\t1- Use one Supply\n" : "");
        str.append(observableGame.canDoBuyActionPoint() && observableGame.checkAvailableMorale() ? "\t2- Use one Morale\n" : "");
        str.append("\t3- End Turn\n");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public String rallyTroopsMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.canDoRallyTroops() && observableGame.checkAvailableSupplies() ? "\t1- Use one supply\n" : "");
        str.append(observableGame.canDoRallyTroops() ? "\t2- Rally troops\n" : "").append("\t3- Return to menu\n");
        return str.toString();
    }

    public String raidAndSabotageMenu() {
        StringBuilder str = new StringBuilder();
        str.append(observableGame.getGameC()).append("\n");
        str.append(observableGame.canDoSupplyRaid() ? "\t1- Supply Raid\n" : "");
        str.append(observableGame.canDoSabotage() ? "\t2- Sabotage\n" : "");
        str.append(observableGame.canDoBuyActionPoint() ? "\t3- Buy Action Point\n" : "");
        str.append("\t4- End Turn\n");
        return str.toString();
    }

    
}
