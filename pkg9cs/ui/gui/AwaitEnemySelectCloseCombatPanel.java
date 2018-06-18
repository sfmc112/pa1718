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
import pkg9cs.states.AwaitEnemySelectionCloseCombatAttack;

/**
 *
 * @author sarah
 */
public class AwaitEnemySelectCloseCombatPanel extends JPanel implements Observer, Constants{
    
    private ObservableGame observableGame;
    
    private GameButton bCCLadder;
    private GameButton bCCRam;
    private GameButton bCCSiege;
    private GameButton bBuyAP;
    private GameButton bReturn;

    public AwaitEnemySelectCloseCombatPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);
        
        bCCLadder = new GameButton("Attack Ladder", "CC Ladder", observableGame);
        bCCRam = new GameButton("Attack Ram", "CC Ram", observableGame);
        bCCSiege = new GameButton("Attack Siege", "CC Siege", observableGame);
        bBuyAP = new GameButton("Buy Action Point", "Buy AP", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);
        
        add(bCCLadder);
        add(bCCRam);
        add(bCCSiege);
        add(bBuyAP);
        add(bReturn);
        
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitEnemySelectionCloseCombatAttack);
        
        bCCLadder.setEnabled(observableGame.isLadderOnCircleSpace());
        bCCRam.setEnabled(observableGame.isBatteringRamOnCircleSpace());
        bCCSiege.setEnabled(observableGame.isSiegeTowerOnCircleSpace());
        bBuyAP.setEnabled(observableGame.canDoBuyActionPoint());
        bReturn.setEnabled(!observableGame.twoEnemiesOnCloseCombat());
        
    }
    
}
