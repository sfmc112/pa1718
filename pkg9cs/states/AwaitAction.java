/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class AwaitAction extends StateAdapter {

    public AwaitAction(GameData game) {
        super(game);
    }

    @Override
    public IState endOfTurn() {
        //TODO
        //WinOrLossCheck
            //if LOSS -> new GameOver();
        //se fim do dia -> fazer acções fim do dia
            //if day = 4 , new GameOver(); (WIN!!)
        //se fim do turno -> fazer acções de fim de turno
        return new AwaitDrawCard(getGame());
    }

    /**
     * Verifica se o jogador já não tem mais ações para perguntar se pretende comprar uma acção extra
     * (caso ainda não tenha utilizado nesse turno e tenha recursos disponíveis)
     * @return novo estado caso o jogador pretenda comprar uma acção e tenha recursos disponíveis
     */
    @Override
    public IState askAddActionPoint() {
        if (!getGame().checkAP() && !getGame().isUsedExtraAP() && getGame().checkAvailableResources()) {
            return new AwaitAddActionPoint(getGame());
        }
        return this;
    }

    @Override
    public IState askUseOfSupply() {
        if(getGame().checkAP() && getGame().checkAvailableSupplies())
            return new AwaitAddSupplyRallyTroops(getGame());
        return this;
    }

    @Override
    public IState selectTunnelMov() {
        if(getGame().checkAP())
            return new AwaitTunnelMovementSelection(getGame());
        return this;
    }

    @Override
    public IState checkEnemiesCloseCombat() {
        if (getGame().checkAP() && getGame().enemiesOnCloseCombat()) {
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesBoilingWater() {
        if (getGame().checkAP() && getGame().enemiesOnCircleSpace()) {
            return new AwaitEnemySelectionBoilingWaterAttack(getGame());
        }
        return this;
    }

    @Override
    public IState checkEnemiesArchers() {
        if (getGame().checkAP() && getGame().enemiesAttackable()) {
            return new AwaitEnemySelectionArchersAttack(getGame());
        }
        return this;
    }

}
