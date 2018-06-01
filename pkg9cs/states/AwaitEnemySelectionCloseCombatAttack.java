/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import pkg9cs.model.GameData;
import pkg9cs.model.elements.Weapon;

/**
 *
 * @author sarah
 */
public class AwaitEnemySelectionCloseCombatAttack extends StateAdapter {

    public AwaitEnemySelectionCloseCombatAttack(GameData game) {
        super(game);
    }

    @Override
    public IState askAddActionPoint() {
        if (getGame().canDoBuyActionPoint())
            return new AwaitAddActionPoint(getGame());

        return this;
    }

    @Override
    public IState closeCombatAttack(Weapon weapon) {

        if (!getGame().closeCombatAttack(weapon)) { //Em caso de escolher uma opção errada, permanece no mesmo estado
            return this;
        }
        getGame().subtractActionPoint(); //O ataque foi permitido, reduz um action point
        if (getGame().immediateLossCheck()) {
            return new GameLost(getGame());
        }

        if (!getGame().twoEnemiesOnCloseCombat()) { //se ataque for bem sucedido, vai para AwaitAction
            return new AwaitAction(getGame());
        } else {
            if (getGame().checkAP()) { //se ataque falhar e tiver ações, permanece no mesmo estado
                return this;
            } else if (getGame().canDoBuyActionPoint()) { //se ataque falhar e não tiver ações e puder comprar uma ação, vai para comprar acao
                return new AwaitAddActionPoint(getGame());
            } else {
                return new AwaitAction(getGame()); //se ataque falhar, não tiver ações e não puder comprar acção, vai para o estado AwaitAction
            }                                       //De lá, faz endOfTurn e vê que o jogador perdeu e passa para o estado GameLost
        }
    }

    @Override
    public IState returnToMenu() {
        if (getGame().twoEnemiesOnCloseCombat()) {
            return this;
        }
        return new AwaitAction(getGame());
    }

}
