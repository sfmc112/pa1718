/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.states;

import java.io.Serializable;
import pkg9cs.model.elements.Element;
import pkg9cs.model.elements.Supply;
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
     * @param weapon Objecto to tipo Ladder, Ram ou SiegeTower
     * @return Novo estado, regressa ao AwaitAction
     */
    public IState archersAttack(Weapon weapon);

    /**
     * Acção Close Combat Attack
     * Se o ataque foi permitido (bem sucedido ou não), retira um action Point. Caso não seja permitido, não retira o Action Point
     * Verifica se o jogador perde imediatamente
     * Verifica se o jogador tem de voltar a fazer CloseCombat (em caso do ataque não ser bem sucedido)
     * @return AwaitAction ou GameLost ou AwaitAddActionPoint
     */
    public IState closeCombatAttack(Weapon weapon);

    /**
     * Acção Ataque de Boiling Water
     * Se o ataque foi permitido (bem sucedido ou não), retira um action Point. Caso não seja permitido, não retira o Action Point.
     * Se sair 1 no dado, diminui moral, e tem que se verificar se o jogador perdeu imediatamente.
     * @param weapon Objecto to tipo Ladder, Ram ou SiegeTower
     * @return Novo estado, AwaitAction ou GameOver
     */
    public IState boilingWaterAttack(Weapon weapon);

    public IState selectTunnelMov();

    public IState moveInTunnel();
    
    public IState freeMovement();
    
    public IState fastMovement();

    public IState askUseOfSupply();

    public IState rallyTroops();
    
    public IState rallyTroops(Supply supp);

    /**
     * Verifica se o jogador já não tem mais ações para perguntar se pretende comprar uma acção extra
     * (caso ainda não tenha utilizado nesse turno e tenha recursos disponíveis)
     * @return novo estado caso o jogador pretenda comprar uma acção e tenha recursos disponíveis
     */
    public IState askAddActionPoint();
    
    public IState buyActionPoint(Element e);

    public IState endOfTurn();

    public IState coupure();

    public IState supplyRaid();

    public IState sabotage();
    
    public IState endGame();
    
    public IState returnToMenu();

}
