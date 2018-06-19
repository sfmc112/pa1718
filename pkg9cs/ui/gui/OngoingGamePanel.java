/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import pkg9cs.controller.ObservableGame;
import pkg9cs.states.AwaitDrawCard;
import pkg9cs.states.GameLost;
import pkg9cs.states.GameWon;
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class OngoingGamePanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;
    
    private static Font textFont=  new Font("Book Antiqua", Font.PLAIN, 18);

    private EnemyBoardPanel pEnemyBoard;
    private StatusBoardPanel pStatusBoard;

    private JLabel lTurnAndAP;
    private JLabel lDieLabel;
    private DiePanel pDie;

    private CardPanel pCard;
    private JButton bDrawCard;

    private OptionPanel pOptions;

    public OngoingGamePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        createComponents();
        displayView();
        registerListeners();

        setLocation(0, 0);
        DimensionClass.setMinAndPreferredSize(this, DIM_X_MAIN_PANEL, DIM_X_MAIN_PANEL);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(!(observableGame.getState() instanceof StartGame));

        lTurnAndAP.setText("Turn: " + observableGame.getTurnNumber() + "  AP: " + observableGame.getNumberOfActions());

        bDrawCard.setEnabled(observableGame.getState() instanceof AwaitDrawCard);
    }

    private void createComponents() {
        pEnemyBoard = new EnemyBoardPanel(observableGame);
        pStatusBoard = new StatusBoardPanel(observableGame);
        lTurnAndAP = new JLabel("");
        pCard = new CardPanel(observableGame);
        pDie = new DiePanel(observableGame);
        pOptions = new OptionPanel(observableGame);
        lDieLabel = new JLabel("Die plus Modifiers");

        bDrawCard = new JButton("Draw Card");
        bDrawCard.setEnabled(false);
        
        lTurnAndAP.setFont(textFont);
        lDieLabel.setFont(textFont);

    }

    private void displayView() {
        Box LeftBox = Box.createVerticalBox();


        LeftBox.add(pEnemyBoard);
        LeftBox.add(Box.createVerticalGlue());

        Box rightBox = Box.createVerticalBox();

        rightBox.add(pStatusBoard);
        rightBox.add(Box.createVerticalGlue());

        Box centerBox = Box.createVerticalBox();

        //centerBox.add(Box.createVerticalGlue());
        centerBox.add(lTurnAndAP);
        lTurnAndAP.setAlignmentX(CENTER_ALIGNMENT);
        centerBox.add(pCard);
        centerBox.add(bDrawCard);
        bDrawCard.setAlignmentX(CENTER_ALIGNMENT);
        centerBox.add(Box.createVerticalGlue());
        centerBox.add(lDieLabel);
        lDieLabel.setAlignmentX(CENTER_ALIGNMENT);
        centerBox.add(pDie);
        centerBox.add(Box.createVerticalGlue());

        Box mainCenterBox = Box.createHorizontalBox();

        mainCenterBox.add(Box.createHorizontalGlue());
        mainCenterBox.add(LeftBox);
        mainCenterBox.add(Box.createHorizontalGlue());
        mainCenterBox.add(centerBox);
        mainCenterBox.add(Box.createHorizontalGlue());
        mainCenterBox.add(rightBox);
        mainCenterBox.add(Box.createHorizontalGlue());

        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(pOptions);
        bottomBox.add(Box.createHorizontalGlue());

        DimensionClass.setAllSizes(mainCenterBox, DIM_X_MAIN_PANEL, DIM_Y_BOARD_PANEL);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(mainCenterBox);
        add(bottomBox);
    }

    private void registerListeners() {
        bDrawCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observableGame.drawCard();
            }
        });        
    }
}
