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
import pkg9cs.states.GameWon;

/**
 *
 * @author sarah
 */
public class GameWonPanel extends JPanel implements Observer, Constants  {

    private ObservableGame observableGame;
    
    public GameWonPanel(ObservableGame observableGame){
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        
        setLocation(X_START_MAIN_PANEL, Y_START_MAIN_PANEL);
        DimensionClass.setAllSizes(this, DIM_X_MAIN_PANEL, DIM_X_MAIN_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof GameWon);
    }
}
