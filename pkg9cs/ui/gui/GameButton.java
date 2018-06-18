/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pkg9cs.controller.ObservableGame;
import pkg9cs.model.elements.GameEnums;

/**
 *
 * @author sarah
 */
public class GameButton extends JButton implements GameEnums {
    
    private ObservableGame observableGame;

    public GameButton(String text, String actionCommand, ObservableGame observableGame) {
        super(text);
        this.observableGame = observableGame;
        
        setActionCommand(actionCommand);
        addActionListener(new ButtonActionListener());
    }

    class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            System.out.println(cmd);

            switch (cmd) {
                case "Archers":
                    observableGame.checkEnemiesArchers();
                    break;
                case "Boiling Water":
                    observableGame.checkEnemiesBoilingWater();
                    break;
                case "Close Combat":
                    observableGame.checkEnemiesCloseCombat();
                    break;
                case "Coupure":
                    observableGame.coupure();
                    break;
                case "Rally Troops":
                    observableGame.askUseOfSupply();
                    break;
                case "Tunnel Movement":
                    observableGame.selectTunnelMov();
                    break;
                case "Supply Raid":
                    observableGame.supplyRaid();
                    break;
                case "Sabotage":
                    observableGame.sabotage();
                    break;
                case "Buy AP":
                    observableGame.askAddActionPoint();
                    break;
                case "AP Supply":
                    observableGame.buyActionPoint(Status.SUPPLY);
                    break;
                case "AP Morale":
                    observableGame.buyActionPoint(Status.MORALE);
                    break;

                case "End Turn":
                    observableGame.endTurn();
                    break;

                case "AA Ladder":
                    observableGame.archersAttack(Enemies.LADDER);
                    break;
                case "AA Ram":
                    observableGame.archersAttack(Enemies.BATTERING_RAM);
                    break;
                case "AA Siege":
                    observableGame.archersAttack(Enemies.SIEGE_TOWER);
                    break;

                case "BW Ladder":
                    observableGame.boilingWaterAttack(Enemies.LADDER);
                    break;
                case "BW Ram":
                    observableGame.boilingWaterAttack(Enemies.BATTERING_RAM);
                    break;
                case "BW Siege":
                    observableGame.boilingWaterAttack(Enemies.SIEGE_TOWER);
                    break;

                case "CC Ladder":
                    observableGame.closeCombatAttack(Enemies.LADDER);
                    break;
                case "CC Ram":
                    observableGame.closeCombatAttack(Enemies.BATTERING_RAM);
                    break;
                case "CC Siege":
                    observableGame.closeCombatAttack(Enemies.SIEGE_TOWER);
                    break;

                case "Return":
                    observableGame.returnToMenu();
                    break;

                case "Move In":
                    observableGame.moveInTunnel();
                    break;
                case "Free Move":
                    observableGame.freeMovement();
                    break;
                case "Fast Move":
                    observableGame.fastMovement();
                    break;

                case "RT Supply":
                    observableGame.rallyTroops(Status.SUPPLY);
                    break;
                case "RT Plain":
                    observableGame.rallyTroops();
                    break;
            }
        }

    }

}
