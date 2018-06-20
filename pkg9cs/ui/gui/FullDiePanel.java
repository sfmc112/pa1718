/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
public class FullDiePanel extends JPanel implements Constants, Observer {

    private ObservableGame observableGame;

    private JLabel lDieLabel;
    private DiePanel pDie;

    public FullDiePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        observableGame.addObserver(this);

        lDieLabel = new JLabel("Die plus Modifiers");
        pDie = new DiePanel(observableGame);
        lDieLabel.setFont(textFont);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lDieLabel);
        lDieLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(pDie);

        setLocation(0, 0);
        DimensionClass.setMinAndPreferredSize(this, DIM_X_DIE_PANEL, DIM_Y_DIE_PANEL + 20);
    }

    @Override
    public void update(Observable o, Object arg) {
        lDieLabel.setVisible(observableGame.getDie() > 0);
    }

}
