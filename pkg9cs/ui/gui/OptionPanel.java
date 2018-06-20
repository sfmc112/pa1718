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
    private GameWonPanel pGameWon;
    private GameLostPanel pGameLost;


    public OptionPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        configurePanels();
        addPanels();

        setLocation(0, 0);
        DimensionClass.setMinAndPreferredSize(this, DIM_X_OPTION_PANEL, DIM_Y_OPTION_PANEL);

        update(observableGame, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    private void configurePanels() {
        pAwaitAction = new AwaitActionPanel(observableGame);
        pAwaitAddActionPoint = new AwaitAddActionPointPanel(observableGame);
        pAwaitAddSupplyRallyTroops = new AwaitAddSupplyRallyTroopsPanel(observableGame);
        pAwaitEnemySelectArchers = new AwaitEnemySelectArchersPanel(observableGame);
        pAwaitEnemySelectBoiling = new AwaitEnemySelectBoilingPanel(observableGame);
        pAwaitEnemySelectCloseCombat = new AwaitEnemySelectCloseCombatPanel(observableGame);
        pAwaitTunnelMovement = new AwaitTunnelMovementPanel(observableGame);
        pAwaitRaidAndSabotage = new AwaitRaidAndSabotagePanel(observableGame);
        pGameWon = new GameWonPanel(observableGame);
        pGameLost= new GameLostPanel(observableGame);
    }

    private void addPanels() {
        add(pAwaitAction);
        add(pAwaitAddActionPoint);
        add(pAwaitAddSupplyRallyTroops);
        add(pAwaitEnemySelectArchers);
        add(pAwaitEnemySelectBoiling);
        add(pAwaitEnemySelectCloseCombat);
        add(pAwaitTunnelMovement);
        add(pAwaitRaidAndSabotage);
        add(pGameWon);
        add(pGameLost);
    }

}
