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
import pkg9cs.states.AwaitAddActionPoint;

/**
 *
 * @author sarah
 */
public class AwaitAddActionPointPanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;

    private GameButton bAPSupply;
    private GameButton bAPMorale;
    private GameButton bEndTurn;
    private GameButton bReturn;

    public AwaitAddActionPointPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);

        bAPSupply = new GameButton("Use Supply", "AP Supply", observableGame);
        bAPMorale = new GameButton("Use Morale", "AP Morale", observableGame);
        bEndTurn = new GameButton("End Turn", "End Turn", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);
        
        add(bAPSupply);
        add(bAPMorale);
        add(bReturn);
        add(bEndTurn);

        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitAddActionPoint);
        
        bAPSupply.setEnabled(observableGame.canDoBuyActionPoint() && observableGame.checkAvailableSupplies());
        bAPMorale.setEnabled(observableGame.canDoBuyActionPoint() && observableGame.checkAvailableMorale());
 
    }

}
