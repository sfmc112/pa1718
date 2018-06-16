/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;
import pkg9cs.model.elements.GameEnums;
import pkg9cs.model.elements.Supply;

/**
 *
 * @author sarah
 */
public class StateAdapter implements IState, GameEnums {

    private GameData game;

    public StateAdapter(GameData game) {
        this.game = game;
    }

    @Override
    public GameData getGame() {
        return game;
    }

    @Override
    public void setGame(GameData game) {
        this.game = game;
    }

    @Override
    public IState startGame() {
        return this;
    }

    @Override
    public IState executeCard() {
        return this;
    }

    @Override
    public IState checkEnemiesCloseCombat() {
        return this;
    }

    @Override
    public IState checkEnemiesArchers() {
        return this;
    }

    @Override
    public IState checkEnemiesBoilingWater() {
        return this;
    }

    @Override
    public IState archersAttack(Enemies weapon) {
        return this;
    }

    @Override
    public IState closeCombatAttack(Enemies weapon) {
        return this;
    }

    @Override
    public IState boilingWaterAttack(Enemies weapon) {
        return this;
    }

    @Override
    public IState selectTunnelMov() {
        return this;
    }

    @Override
    public IState moveInTunnel() {
        return this;
    }
    
    @Override
    public IState freeMovement() {
        return this;
    }

    @Override
    public IState fastMovement() {
        return this;
    }

    @Override
    public IState askUseOfSupply() {
        return this;
    }

    @Override
    public IState rallyTroops() {
        return this;
    }

    @Override
    public IState rallyTroops(Status supp) {
        return this;
    }

    @Override
    public IState askAddActionPoint() {
        return this;
    }

    @Override
    public IState buyActionPoint(Status e) {
        return this;
    }

    @Override
    public IState endOfTurn() {
        return this;
    }

    @Override
    public IState coupure() {
        return this;
    }

    @Override
    public IState supplyRaid() {
        return this;
    }

    @Override
    public IState sabotage() {
        return this;
    }

    @Override
    public IState endGame() {
        //return this;
        return new StartGame(new GameData());
    }

    @Override
    public IState returnToMenu() {
        return this;
    }

}
