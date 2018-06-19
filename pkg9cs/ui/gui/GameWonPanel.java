/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;
import pkg9cs.states.GameWon;

/**
 *
 * @author sarah
 */
public class GameWonPanel extends JPanel implements Observer, Constants  {

    private ObservableGame observableGame;
    private JLabel lWonMessage;
    private JLabel lWonDescription;
    
    public GameWonPanel(ObservableGame observableGame){
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        
        lWonMessage = new JLabel("VICTORY!");
        lWonDescription =  new JLabel("Invaders were sucessfully repelled!");
        
        lWonMessage.setFont(bigMessageFont);
        lWonDescription.setFont(detailMessageFont);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalGlue());
        b.add(lWonMessage);
        lWonMessage.setAlignmentX(CENTER_ALIGNMENT);
        b.add(Box.createVerticalGlue());
        b.add(lWonDescription);
        lWonDescription.setAlignmentX(CENTER_ALIGNMENT);
        b.add(Box.createVerticalGlue());
        
        Box c = Box.createHorizontalBox();
        c.add(b);
        add(c);
        
        setLocation(X_START_MAIN_PANEL, Y_START_MAIN_PANEL);
        DimensionClass.setAllSizes(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof GameWon);
    }
}
