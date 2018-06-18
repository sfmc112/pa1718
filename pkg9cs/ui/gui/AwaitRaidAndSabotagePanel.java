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
import pkg9cs.states.RaidAndSabotageActionsOnly;

/**
 *
 * @author sarah
 */
public class AwaitRaidAndSabotagePanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;
    
    private GameButton bSupplyRaid;
    private GameButton bSabotage;
    private GameButton bBuyAP;
    private GameButton bEndTurn;

    public AwaitRaidAndSabotagePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bSupplyRaid = new GameButton("Supply Raid", "Supply Raid", observableGame);
        bSabotage = new GameButton("Sabotage", "Sabotage", observableGame);
        bBuyAP = new GameButton("Buy Action Point", "Buy AP", observableGame);
        bEndTurn = new GameButton("End Turn", "End Turn", observableGame);
        
        add(bSupplyRaid);
        add(bSabotage);
        add(bBuyAP);
        add(bEndTurn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof RaidAndSabotageActionsOnly);
        
        bSupplyRaid.setEnabled(observableGame.canDoSupplyRaid());
        bSabotage.setEnabled(observableGame.canDoSabotage());
        bBuyAP.setEnabled(observableGame.canDoBuyActionPoint());
    }
}
