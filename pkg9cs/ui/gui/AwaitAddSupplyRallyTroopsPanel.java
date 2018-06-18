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
import pkg9cs.states.AwaitAddSupplyRallyTroops;

/**
 *
 * @author sarah
 */
public class AwaitAddSupplyRallyTroopsPanel extends JPanel implements Observer, Constants{
    
    private ObservableGame observableGame;
    
    private GameButton bRTSupply;
    private GameButton bRTPlain;
    private GameButton bBuyAP;
    private GameButton bReturn;

    public AwaitAddSupplyRallyTroopsPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bRTSupply = new GameButton("Use Supply", "RT Supply", observableGame);
        bRTPlain = new GameButton("Don't use Supply", "RT Plain", observableGame);
        bBuyAP = new GameButton("Buy Action Point", "Buy AP", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);
        
        add(bRTSupply);
        add(bRTPlain);
        add(bBuyAP);
        add(bReturn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitAddSupplyRallyTroops);
        
        bRTSupply.setEnabled(observableGame.canDoRallyTroops() && observableGame.checkAvailableSupplies());
        bRTPlain.setEnabled(observableGame.canDoRallyTroops());
        bBuyAP.setEnabled(observableGame.canDoBuyActionPoint());
    }
    
}
