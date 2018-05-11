/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.controller;

import pkg9cs.model.GameData;
import pkg9cs.states.IState;
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class GameController {

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
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    
    public String startGameMenu() {
        StringBuilder str = new StringBuilder();
        str.append("\t1- New Game\n");
        str.append("\t2- Load Game\n");
        str.append("\t3- Save Game\n");
        str.append("\t4- Quit Game\n");
        return str.toString();
    }
    /**
     * Draw Card Menu
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    public boolean drawCardMenu() {
        //TODO
        // Draw() ; Save(); Load(); Quit()
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Await Action Menu
     * @return String com o menu preparado para ser apresentado ao utilizador
     */
    public String awaitActionMenu() {
        //TODO
        StringBuilder str = new StringBuilder();
        str.append(game.toString());
        str.append("\nAvailable actions:\n");
        if(canDoArchers())
            str.append("\t1- Archers\n");
        if(canDoBoiling())
            str.append("\t2- Boiling\n");
        if(canDoCloseCombat())
            str.append("\t3- Close Combat\n");
        if(canDoCoupure())
            str.append("\t4- Coupure\n");
        if(canDoRallyTroops())
            str.append("\t5- Rally Troops\n");
        if(canDoTunnelMovement())
            str.append("\t6- Tunnel Movement\n");
        if(canDoSupplyRaid())
            str.append("\t7- Supply Raid\n");
        if(canDoSabotage())
            str.append("\t8- Sabotage\n");
        // 9- EndTurn(); 10- SaveGame() ; 11- LoadGame(); 12- Quit()
        
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
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canDoRallyTroops() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canDoTunnelMovement() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canDoSupplyRaid() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean canDoSabotage() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
