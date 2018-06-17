/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
class OptionPanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;

    private AwaitActionPanel pAwaitAction;
    private AwaitAddActionPointPanel pAwaitAddActionPoint;
    private AwaitAddSupplyRallyTroopsPanel pAwaitAddSupplyRallyTroops;
    private AwaitEnemySelectArchersPanel pAwaitEnemySelectArchers;
    private AwaitEnemySelectBoilingPanel pAwaitEnemySelectBoiling;
    private AwaitEnemySelectCloseCombatPanel pAwaitEnemySelectCloseCombat;
    private AwaitTunnelMovementPanel pAwaitTunnelMovement;
    private AwaitRaidAndSabotagePanel pAwaitRaidAndSabotage;

    private GameButton bArchers;
    private GameButton bBoilingWater;
    private GameButton bCloseCombat;
    private GameButton bCoupure;
    private GameButton bRallyTroops;
    private GameButton bTunnelMovement;
    private GameButton bSupplyRaid;
    private GameButton bSabotage;
    private GameButton bBuyAP;
    private GameButton bAPSupply;
    private GameButton bAPMorale;
    private GameButton bEndTurn;

    private GameButton bAALadder;
    private GameButton bAARam;
    private GameButton bAASiege;

    private GameButton bBWLadder;
    private GameButton bBWRam;
    private GameButton bBWSiege;

    private GameButton bCCLadder;
    private GameButton bCCRam;
    private GameButton bCCSiege;

    private GameButton bReturn;

    private GameButton bMoveInTunnel;
    private GameButton bFreeMove;
    private GameButton bFastMove;

    private GameButton bRTSupply;
    private GameButton bRTPlain;

    public OptionPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        //TODO

        configurePanels();
        configureButtons();
        addButtonsToPanels();
        addPanels();

        setBackground(Color.lightGray);
        setLocation(0, 0);
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        bArchers.setEnabled(observableGame.canDoArchers());
        bBoilingWater.setEnabled(observableGame.canDoBoiling());
        bCloseCombat.setEnabled(observableGame.canDoCloseCombat());
        bCoupure.setEnabled(observableGame.canDoCoupure());
        bRallyTroops.setEnabled(observableGame.canDoRallyTroops());
        bTunnelMovement.setEnabled(observableGame.canMoveIntoTunnel() || observableGame.canDoFastMovement() || observableGame.canDoFreeMovement());
        bSupplyRaid.setEnabled(observableGame.canDoSupplyRaid());
        bSabotage.setEnabled(observableGame.canDoSabotage());
        bBuyAP.setEnabled(observableGame.canDoBuyActionPoint());
        //bAPSupply.setEnabled(observableGame.canDoBoiling());
        //bAPMorale.setEnabled(observableGame.canDoBoiling());
        //bEndTurn.setEnabled(observableGame.canDoBoiling());

        bAALadder.setEnabled(!observableGame.isLadderOnStartingSpace() && observableGame.checkAP());
        bAARam.setEnabled(!observableGame.isBatteringRamOnStartingSpace() && observableGame.checkAP());
        bAASiege.setEnabled(!observableGame.isSiegeTowerOnStartingSpace() && observableGame.checkAP());

        bBWLadder.setEnabled(observableGame.isLadderOnCircleSpace() && observableGame.checkAP());
        bBWRam.setEnabled(observableGame.isBatteringRamOnCircleSpace() && observableGame.checkAP());
        bBWSiege.setEnabled(observableGame.isSiegeTowerOnCircleSpace() && observableGame.checkAP());

        bCCLadder.setEnabled(observableGame.isLadderOnCloseCombatSpace() && observableGame.checkAP());
        bCCRam.setEnabled(observableGame.isBatteringRamOnCloseCombatSpace() && observableGame.checkAP());
        bCCSiege.setEnabled(observableGame.isSiegeTowerOnCloseCombatSpace() && observableGame.checkAP());

        //bReturn = new GameButton("Go Back", "Return", observableGame);
        bMoveInTunnel.setEnabled(observableGame.canMoveIntoTunnel());
        bFreeMove.setEnabled(observableGame.canDoFreeMovement());
        bFastMove.setEnabled(observableGame.canDoFastMovement());

        //bRTSupply = new GameButton("Use Supply", "RT Supply", observableGame);
        //bRTPlain = new GameButton("Don't use Supply", "RT Plain", observableGame);
        repaint();
    }

    private void configurePanels() {
        pAwaitAction = new AwaitActionPanel(observableGame);
        //pAwaitAction.setLayout(new GridLayout(5,5));
        pAwaitAddActionPoint = new AwaitAddActionPointPanel(observableGame);
        pAwaitAddSupplyRallyTroops = new AwaitAddSupplyRallyTroopsPanel(observableGame);
        pAwaitEnemySelectArchers = new AwaitEnemySelectArchersPanel(observableGame);
        pAwaitEnemySelectBoiling = new AwaitEnemySelectBoilingPanel(observableGame);
        pAwaitEnemySelectCloseCombat = new AwaitEnemySelectCloseCombatPanel(observableGame);
        pAwaitTunnelMovement = new AwaitTunnelMovementPanel(observableGame);
        pAwaitRaidAndSabotage = new AwaitRaidAndSabotagePanel(observableGame);
    }

    private void configureButtons() {
        bArchers = new GameButton("Archers Attack", "Archers", observableGame);
        bBoilingWater = new GameButton("Boiling Water", "Boiling Water", observableGame);
        bCloseCombat = new GameButton("Close Combat", "Close Combat", observableGame);
        bCoupure = new GameButton("Coupure", "Coupure", observableGame);
        bRallyTroops = new GameButton("Rally Troops", "Rally Troops", observableGame);
        bTunnelMovement = new GameButton("Tunnel Movement", "Tunnel Movement", observableGame);
        bSupplyRaid = new GameButton("Supply Raid", "Supply Raid", observableGame);
        bSabotage = new GameButton("Sabotage", "Sabotage", observableGame);
        bBuyAP = new GameButton("Buy Action Point", "Buy AP", observableGame);
        bAPSupply = new GameButton("Use Supply", "AP Supply", observableGame);
        bAPMorale = new GameButton("Use Morale", "AP Morale", observableGame);
        bEndTurn = new GameButton("End Turn", "End Turn", observableGame);

        bAALadder = new GameButton("Attack Ladder", "AA Ladder", observableGame);
        bAARam = new GameButton("Attack Ram", "AA Ram", observableGame);
        bAASiege = new GameButton("Attack Siege", "AA Siege", observableGame);

        bBWLadder = new GameButton("Attack Ladder", "BW Ladder", observableGame);
        bBWRam = new GameButton("Attack Ram", "BW Ram", observableGame);
        bBWSiege = new GameButton("Attack Siege", "BW Siege", observableGame);

        bCCLadder = new GameButton("Attack Ladder", "CC Ladder", observableGame);
        bCCRam = new GameButton("Attack Ram", "CC Ram", observableGame);
        bCCSiege = new GameButton("Attack Siege", "CC Siege", observableGame);

        bReturn = new GameButton("Go Back", "Return", observableGame);

        bMoveInTunnel = new GameButton("Move In Tunnel", "Move In", observableGame);
        bFreeMove = new GameButton("Free Movement", "Free Move", observableGame);
        bFastMove = new GameButton("Fast Movement", "Fast Move", observableGame);

        bRTSupply = new GameButton("Use Supply", "RT Supply", observableGame);
        bRTPlain = new GameButton("Don't use Supply", "RT Plain", observableGame);
    }

    private void addButtonsToPanels() {
        pAwaitAction.add(bArchers);
        pAwaitAction.add(bBoilingWater);
        pAwaitAction.add(bCloseCombat);
        pAwaitAction.add(bCoupure);
        pAwaitAction.add(bRallyTroops);
        pAwaitAction.add(bTunnelMovement);
        pAwaitAction.add(bSupplyRaid);
        pAwaitAction.add(bSabotage);
        pAwaitAction.add(bBuyAP);
        pAwaitAction.add(bEndTurn);

        pAwaitAddActionPoint.add(bAPSupply);
        pAwaitAddActionPoint.add(bAPMorale);
        pAwaitAddActionPoint.add(bEndTurn);
        pAwaitAddActionPoint.add(bReturn);

        pAwaitAddSupplyRallyTroops.add(bRTSupply);
        pAwaitAddSupplyRallyTroops.add(bRTPlain);
        pAwaitAddSupplyRallyTroops.add(bBuyAP);
        pAwaitAddSupplyRallyTroops.add(bReturn);

        pAwaitEnemySelectArchers.add(bAALadder);
        pAwaitEnemySelectArchers.add(bAARam);
        pAwaitEnemySelectArchers.add(bAASiege);
        pAwaitEnemySelectArchers.add(bReturn);

        pAwaitEnemySelectBoiling.add(bBWLadder);
        pAwaitEnemySelectBoiling.add(bBWRam);
        pAwaitEnemySelectBoiling.add(bBWSiege);
        pAwaitEnemySelectBoiling.add(bReturn);

        pAwaitEnemySelectCloseCombat.add(bCCLadder);
        pAwaitEnemySelectCloseCombat.add(bCCRam);
        pAwaitEnemySelectCloseCombat.add(bCCSiege);
        pAwaitEnemySelectCloseCombat.add(bBuyAP);
        pAwaitEnemySelectCloseCombat.add(bReturn);

        pAwaitTunnelMovement.add(bMoveInTunnel);
        pAwaitTunnelMovement.add(bFreeMove);
        pAwaitTunnelMovement.add(bFastMove);
        pAwaitTunnelMovement.add(bReturn);

        pAwaitRaidAndSabotage.add(bSupplyRaid);
        pAwaitRaidAndSabotage.add(bSabotage);
        pAwaitRaidAndSabotage.add(bBuyAP);
        pAwaitRaidAndSabotage.add(bEndTurn);
    }

    private void addPanels() {
        add(pAwaitAction);
        //DimensionClass.setAllSizes(pAwaitAction, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitAddActionPoint);
        //DimensionClass.setAllSizes(pAwaitAddActionPoint, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitAddSupplyRallyTroops);
        //DimensionClass.setAllSizes(pAwaitAddSupplyRallyTroops, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitEnemySelectArchers);
        //DimensionClass.setAllSizes(pAwaitEnemySelectArchers, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitEnemySelectBoiling);
        //DimensionClass.setAllSizes(pAwaitEnemySelectBoiling, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitEnemySelectCloseCombat);
        //DimensionClass.setAllSizes(pAwaitEnemySelectCloseCombat, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitTunnelMovement);
        //DimensionClass.setAllSizes(pAwaitTunnelMovement, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
        add(pAwaitRaidAndSabotage);
        //DimensionClass.setAllSizes(pAwaitRaidAndSabotage, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);
    }

}
