/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import java.io.Serializable;
import pkg9cs.model.elements.Weapon;

/**
 *
 * @author sarah
 */
public interface IState extends Serializable{

    public IState startGame();

    /**
     * Faz a verificação do EnemyLine Check, executa a carta e verifica se o
     * jogador perde imediatamente
     *
     * @return Estado GameOver ou AwaitEnemySelectionCloseCombatAttack ou AwaitAction
     */
    public IState executeCard();

    public IState checkEnemiesCloseCombat();

    public IState checkEnemiesArchers();

    public IState checkEnemiesBoilingWater();

    /**
     * Acção Ataque de Archers
     * Se o ataque foi permitido (bem sucedido ou não), retira um action Point. Caso não seja permitido, não retira o Action Point
     * @param weapon Objeco to tipo Ladder, Ram ou SiegeTower
     * @return Novo estado, regressa ao AwaitAction
     */
    public IState archersAttack(Weapon weapon);

    public IState closeCombatAttack();

    public IState boilingWaterAttack();

    public IState selectTunnelMov();

    public IState moveInTunnel();

    public IState askUseOfSupply();

    public IState rallyTroops();

    /**
     * Verifica se o jogador já não tem mais ações para perguntar se pretende comprar uma acção extra
     * (caso ainda não tenha utilizado nesse turno e tenha recursos disponíveis)
     * @return novo estado caso o jogador pretenda comprar uma acção e tenha recursos disponíveis
     */
    public IState askAddActionPoint();
    
    public IState buyActionPoint();

    public IState endOfTurn();

    public IState coupure();

    public IState supplyRaid();

    public IState sabotage();

    public IState endOfDay();
    
    public IState endGame();

}
