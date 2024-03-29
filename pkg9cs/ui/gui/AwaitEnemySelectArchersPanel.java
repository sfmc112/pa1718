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
import pkg9cs.states.AwaitEnemySelectionArchersAttack;

/**
 *
 * @author sarah
 */
public class AwaitEnemySelectArchersPanel extends JPanel implements Observer, Constants{
    
    private ObservableGame observableGame;
    
    private GameButton bAALadder;
    private GameButton bAARam;
    private GameButton bAASiege;
    private GameButton bReturn;

    public AwaitEnemySelectArchersPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bAALadder = new GameButton("Attack Ladder", "AA Ladder", observableGame);
        bAARam = new GameButton("Attack Ram", "AA Ram", observableGame);
        bAASiege = new GameButton("Attack Siege", "AA Siege", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);
        
        add(bAALadder);
        add(bAARam);
        add(bAASiege);
        add(bReturn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitEnemySelectionArchersAttack);
        
        bAALadder.setEnabled(!observableGame.isLadderOnStartingSpace());
        bAARam.setEnabled(!observableGame.isBatteringRamOnStartingSpace());
        bAASiege.setEnabled(!observableGame.isSiegeTowerOnStartingSpace());
    }
    
}
