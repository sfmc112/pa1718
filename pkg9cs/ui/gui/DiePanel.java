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

/**
 *
 * @author sarah
 */
class DiePanel extends JPanel implements Observer{

    private ObservableGame observableGame;

    public DiePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        //TODO
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}