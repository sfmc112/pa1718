/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;
import pkg9cs.states.AwaitAction;

/**
 *
 * @author sarah
 */
public class AwaitActionPanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;
    
    private GameButton bArchers;
    private GameButton bBoilingWater;
    private GameButton bCloseCombat;
    private GameButton bCoupure;
    private GameButton bRallyTroops;
    private GameButton bTunnelMovement;
    private GameButton bSupplyRaid;
    private GameButton bSabotage;
    private GameButton bBuyAP;
    private GameButton bEndTurn;

    public AwaitActionPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bArchers = new GameButton("Archers Attack", "Archers", observableGame);
        bBoilingWater = new GameButton("Boiling Water", "Boiling Water", observableGame);
        bCloseCombat = new GameButton("Close Combat", "Close Combat", observableGame);
        bCoupure = new GameButton("Coupure", "Coupure", observableGame);
        bRallyTroops = new GameButton("Rally Troops", "Rally Troops", observableGame);
        bTunnelMovement = new GameButton("Tunnel Movement", "Tunnel Movement", observableGame);
        bSupplyRaid = new GameButton("Supply Raid", "Supply Raid", observableGame);
        bSabotage = new GameButton("Sabotage", "Sabotage", observableGame);
        bBuyAP = new GameButton("Buy Action Point", "Buy AP", observableGame);
        bEndTurn = new GameButton("End Turn", "End Turn", observableGame);
        
        add(bArchers);
        add(bBoilingWater);
        add(bCloseCombat);
        add(bCoupure);
        add(bRallyTroops);
        add(bTunnelMovement);
        add(bSupplyRaid);
        add(bSabotage);
        add(bBuyAP);
        add(bEndTurn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitAction);
        
        bArchers.setEnabled(observableGame.canDoArchers());
        bBoilingWater.setEnabled(observableGame.canDoBoiling());
        bCloseCombat.setEnabled(observableGame.canDoCloseCombat());
        bCoupure.setEnabled(observableGame.canDoCoupure());
        bRallyTroops.setEnabled(observableGame.canDoRallyTroops());
        bTunnelMovement.setEnabled(observableGame.canMoveIntoTunnel() || observableGame.canDoFastMovement() || observableGame.canDoFreeMovement());
        bSupplyRaid.setEnabled(observableGame.canDoSupplyRaid());
        bSabotage.setEnabled(observableGame.canDoSabotage());
        bBuyAP.setEnabled(observableGame.canDoBuyActionPoint());
    }

}
