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
public class AwaitDrawCard extends StateAdapter {

    public AwaitDrawCard(GameData game) {
        super(game);
    }

    /**
     * Faz a verificação do EnemyLine Check, executa a carta e verifica se o
     * jogador perde imediatamente
     *
     * @return Estado GameOver ou AwaitEnemySelectionCloseCombatAttack ou
     * AwaitAction
     */
    @Override
    public IState executeCard() {

        if (getGame().checkSoldiersOnEnemyLine()) {
            if (GameData.Die.rollDie() == 1) {
                getGame().captureSoldiers();
            }
        }

        getGame().executeCard();

        if (getGame().immediateLossCheck()) {
            return new GameOver(getGame());
        } else if (getGame().twoEnemiesOnCloseCombat()) {
            return new AwaitEnemySelectionCloseCombatAttack(getGame());
        }
        return new AwaitAction(getGame());
    }

}
