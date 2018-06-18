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
import pkg9cs.states.AwaitEnemySelectionBoilingWaterAttack;

/**
 *
 * @author sarah
 */
public class AwaitEnemySelectBoilingPanel extends JPanel implements Observer, Constants{
    
    private ObservableGame observableGame;
    
    private GameButton bBWLadder;
    private GameButton bBWRam;
    private GameButton bBWSiege;
    private GameButton bReturn;

    public AwaitEnemySelectBoilingPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bBWLadder = new GameButton("Attack Ladder", "BW Ladder", observableGame);
        bBWRam = new GameButton("Attack Ram", "BW Ram", observableGame);
        bBWSiege = new GameButton("Attack Siege", "BW Siege", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);
        
        add(bBWLadder);
        add(bBWRam);
        add(bBWSiege);
        add(bReturn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitEnemySelectionBoilingWaterAttack);
        
        bBWLadder.setEnabled(observableGame.isLadderOnCircleSpace());
        bBWRam.setEnabled(observableGame.isBatteringRamOnCircleSpace());
        bBWSiege.setEnabled(observableGame.isSiegeTowerOnCircleSpace());
    }
    
}
