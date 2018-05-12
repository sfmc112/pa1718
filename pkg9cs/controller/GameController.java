/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.controller;

import java.io.Serializable;
import pkg9cs.model.GameData;
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

    //TODO check game state
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
        //TODO
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
        str.append("\t9- End Turn\n");
        str.append("\t10- Save Game\n");
        str.append("\t11- Load Game\n");
        str.append("\t12- Quit Game\n");

        return str.toString();

    }

    private boolean canDoArchers() {
        return (getGame().checkAP() && getGame().enemiesAttackable());
    }

    private boolean canDoBoiling() {
        return (getGame().checkAP() && getGame().enemiesOnCircleSpace());
    }

    private boolean canDoCloseCombat() {
        return (getGame().checkAP() && getGame().enemiesOnCloseCombat());
    }

    private boolean canDoCoupure() {
        return (getGame().checkAP()) && (!(getGame().getStatusB().wallOnStartingSpace()));
    }

    private boolean canDoRallyTroops() {
        return (getGame().checkAP()) && (!(getGame().getStatusB().moraleOnStartingSpace()));
    }

    private boolean canDoSupplyRaid() {
        return (getGame().checkAP()) && (getGame().getStatusB().checkSoldiersOnEnemyLine());
    }

    private boolean canDoSabotage() {
        return (getGame().checkAP()) && (getGame().getStatusB().checkSoldiersOnEnemyLine());
    }

    public void startGame() {
        setState(state.startGame());
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
      public void supplyRaid() {
          setState(state.supplyRaid());
    }
    
    public boolean tunnelMenu() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        str.append(getGame().getEnemyB().isLadderOnCircleSpace() ? "\t1- Ladder\n":"");
        str.append(getGame().getEnemyB().isBatteringRamOnCircleSpace()? "\t2- Battering Ram\n":"");
        str.append(getGame().getEnemyB().isSiegeTowerOnCircleSpace() ? "\t3- Siege Tower\n":"");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }
    public String closeCombatAttackMenu() {
        StringBuilder str = new StringBuilder();
        str.append(getGame().getEnemyB()).append("\n");
        str.append(getGame().getEnemyB().isLadderOnCloseCombatSpace() ? "\t1- Ladder\n":"");
        str.append(getGame().getEnemyB().isBatteringRamOnCloseCombatSpace()? "\t2- Battering Ram\n":"");
        str.append(getGame().getEnemyB().isSiegeTowerOnCloseCombatSpace() ? "\t3- Siege Tower\n":"");
        str.append("\t4- Return to menu\n");
        return str.toString();
    }

    public void archersAttack(Weapon weapon) {
        setState(state.archersAttack(weapon));
    }
    public void sabotage() {
        setState(state.sabotage());
    }

    public String statusBoard() {
        StringBuilder str = new StringBuilder();
        str.append(game.getEnemyB()).append("\n\n").append(game.getStatusB()).append("\n");
        return str.toString();
    }
}
