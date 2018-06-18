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
import pkg9cs.states.AwaitTunnelMovementSelection;

/**
 *
 * @author sarah
 */
public class AwaitTunnelMovementPanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;

    private GameButton bMoveInTunnel;
    private GameButton bFreeMove;
    private GameButton bFastMove;
    private GameButton bReturn;

    public AwaitTunnelMovementPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);

        bMoveInTunnel = new GameButton("Move In Tunnel", "Move In", observableGame);
        bFreeMove = new GameButton("Free Movement", "Free Move", observableGame);
        bFastMove = new GameButton("Fast Movement", "Fast Move", observableGame);
        bReturn = new GameButton("Go Back", "Return", observableGame);

        add(bMoveInTunnel);
        add(bFreeMove);
        add(bFastMove);
        add(bReturn);

        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof AwaitTunnelMovementSelection);

        bMoveInTunnel.setEnabled(observableGame.canMoveIntoTunnel());
        bFreeMove.setEnabled(observableGame.canDoFreeMovement());
        bFastMove.setEnabled(observableGame.canDoFastMovement());
    }

}
