/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import java.io.Serializable;
import pkg9cs.model.boardspaces.CloseCombat;
import pkg9cs.model.cards.CardPile;
import pkg9cs.model.cards.Card;
import pkg9cs.model.cards.CardDayEvent;
import pkg9cs.model.elements.*;

/**
 *
 * @author sarah
 */
public class GameData implements Serializable {

    private EnemyBoard enemyB;
    private StatusBoard statusB;

    private int dayNumber;
    private int numberOfActions;
    private CardDayEvent dayEvent;

    private boolean usedExtraAP;
    private boolean usedBoiling;

    private CardPile deck;
    private CardPile discarded;

    public GameData() {
        enemyB = new EnemyBoard();
        statusB = new StatusBoard();

        dayNumber = 1;
        numberOfActions = 0;
        dayEvent = null;

        usedExtraAP = false;
        usedBoiling = false;

        deck = new CardPile();
        deck.setNewCards();

        discarded = new CardPile();
    }

    public EnemyBoard getEnemyB() {
        return enemyB;
    }

    public StatusBoard getStatusB() {
        return statusB;
    }

    public boolean isUsedExtraAP() {
        return usedExtraAP;
    }

    public void setUsedExtraAP(boolean usedExtraAP) {
        this.usedExtraAP = usedExtraAP;
    }

    public CardDayEvent getDayEvent() {
        return dayEvent;
    }

    public void setDayEvent(CardDayEvent dayEvent) {
        this.dayEvent = dayEvent;
    }

    public boolean isUsedBoiling() {
        return usedBoiling;
    }

    public void setUsedBoiling(boolean usedBoiling) {
        this.usedBoiling = usedBoiling;
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    public void setNumberOfActions(int numberOfActions) {
        this.numberOfActions = numberOfActions;
    }

    public void addActionPoint() {
        numberOfActions++;
        usedExtraAP = true;
    }

    public void subtractActionPoint() {
        if (numberOfActions > 0) {
            numberOfActions--;
        }
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public boolean newDaySetup() {
        if (dayNumber > 3) {
            return false;
        }

        dayNumber++;
        newTurnSetup();
        resetDeck();
        shuffleCards(deck);
        return true;
    }

    public void newTurnSetup() {
        discarded.receiveCard(deck.transferCard(0));
        usedExtraAP = false;
        numberOfActions = 0;
    }

    /*-------------------------------Card Actions----------------------------------------*/
    public Card getCardFromDeck(int index) {
        return deck.getCard(index);
    }

    /**
     * Manda executar as acções da carta que está primeiro no deck
     */
    public boolean executeCard() {
        if (deck.getCardPileSize() <= 0) {
            return false;
        }

        getCardFromDeck(0).executeCard(this, dayNumber);

        return true;

    }

    /**
     * Volta a colocar as cartas que foram usadas no turno de volta no Deck,
     * preparando-as para o próximo turno.
     *
     * @see CardPile
     */
    public void resetDeck() {
        while (discarded.getCardPileSize() > 0) {
            deck.receiveCard(discarded.transferCard(0));
        }
    }

    public void shuffleCards(CardPile cp) {
        cp.shuffleCards();
    }

    public void shuffleDeck() {
        deck.shuffleCards();
    }

    public boolean towerIsOnStartingSpace() {
        return enemyB.isSiegeTowerOnStartingSpace();
    }

    public void removeSiegeTower() {
        if (towerIsOnStartingSpace()) {
            enemyB.removeSiegeTower();
        }
    }

    public void addTrebuchetCount() {
        enemyB.addTrebuchetCount();
    }

    public void captureSoldiers() {
        statusB.captureSoldiers();
    }

    public boolean immediateLossCheck() {
        if (enemyB.checkImmediateLossOnCloseCombat()) {
            return true;
        }
        return statusB.checkImmediateLossOnSurrender();

    }

    public boolean twoEnemiesOnCloseCombat() {
        return enemyB.countEnemiesOnCloseCombat() == 2;
    }

    public boolean enemiesOnCloseCombat() {
        return enemyB.countEnemiesOnCloseCombat() > 0;
    }

    public boolean enemiesOnCircleSpace() {
        return enemyB.countEnemiesOnCircleSpace() > 0;
    }

    /**
     * Verifica se há inimigos atacáveis, isto é, se há algum que não esteja na
     * posição inicial
     *
     * @return false se todos os inimigos estão na posição inicial
     */
    public boolean enemiesAttackable() {
        return enemyB.countEnemiesOnStartingPosition() < 3;
    }

    /**
     * Verifica se ainda tem acções no presente turno
     *
     * @return true se ainda há acções para jogar
     */
    public boolean checkAP() {
        return numberOfActions > 0;
    }

    /**
     * Verifica se o jogador tem recursos disponíveis para comprar uma acção
     *
     * @return true se tem algum recurso disponível (morale ou supply)
     */
    public boolean checkAvailableResources() {
        return statusB.checkAvailableResources();
    }

    public boolean checkAvailableSupplies() {
        return statusB.checkAvailableSupplies();
    }

    public boolean checkSoldiersOnEnemyLine() {
        return statusB.checkSoldiersOnEnemyLine();
    }

    /*-------------------------------Attacks----------------------------------------*/
    public void attackCloseCombat() {
        //TODO
    }

    public void attackBoilingWater() {
        //TODO
    }

    public boolean archersAttack(Weapon weapon) {
        return checkWeaponTypeAndAttack(weapon);
    }

    /**
     * Chama o método de ataque ao respetivo objeto passado por argumento
     *
     * @param weapon Tipo de inimigo a atacar
     * @return true se o ataque foi permitido (sucesso ou insucesso)
     */
    public boolean checkWeaponTypeAndAttack(Weapon weapon) {
        if (weapon instanceof Ladder) {
            return attackLadder();
        } else if (weapon instanceof Ram) {
            return attackRam();
        } else {
            return attackSiegeTower();
        }
    }

    private boolean attackLadder() {
        if (enemyB.isLadderOnStartingSpace()) {
            return false;
        }
        if (enemyB.isLadderOnCloseCombatSpace()) {
            return attackLadderOnCloseCombat();
        }
        if (enemyB.isLadderOnCircleSpace()) {
            return attackLadderOnCircleSpace();
        }

        return attackLadderOnSquareSpace();
    }

    private boolean attackLadderOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean attackLadderOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getLadderDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > Ladder.STRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean attackLadderOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getLadderDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > Ladder.STRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean attackRam() {
        if (enemyB.isBatteringRamOnStartingSpace()) {
            return false;
        }
        if (enemyB.isBatteringRamOnCloseCombatSpace()) {
            return attackRamOnCloseCombat();
        }
        if (enemyB.isBatteringRamOnCircleSpace()) {
            return attackRamOnCircleSpace();
        }

        return attackRamOnSquareSpace();
    }

    private boolean attackRamOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean attackRamOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getBatteringRamDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > Ram.STRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean attackRamOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getBatteringRamDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > Ram.STRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean attackSiegeTower() {
        if (!enemyB.isTowerPresent() || enemyB.isSiegeTowerOnStartingSpace()) {
            return false;
        }
        if (enemyB.isSiegeTowerOnCloseCombatSpace()) {
            return attackTowerOnCloseCombat();
        }
        if (enemyB.isSiegeTowerOnCircleSpace()) {
            return attackTowerOnCircleSpace();
        }

        return attackTowerOnSquareSpace();
    }

    private boolean attackTowerOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    private boolean attackTowerOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getSiegeTowerDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > SiegeTower.STRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    private boolean attackTowerOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getSiegeTowerDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);

        if (dieResult > SiegeTower.STRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    /*-------------------------------DRM's----------------------------------------*/
    public int getSabotageDRM() {
        return dayEvent.searchForSabotageDRM();
    }

    public int getMoraleDRM() {
        return dayEvent.searchForMoraleDRM();
    }

    public int getCircleSpaceDRM() {
        return dayEvent.searchForCircleSpaceDRM();
    }

    public int getCloseCombatSpaceDRM() {
        return dayEvent.searchForCloseCombatSpaceDRM();
    }

    public int getSupplyRaidDRM() {
        return dayEvent.searchForSupplyRaidDRM();
    }

    public int getCoupureDRM() {
        return dayEvent.searchForCoupureDRM();
    }

    public int getLadderDRM() {
        return dayEvent.searchForLadderDRM();
    }

    public int getBatteringRamDRM() {
        return dayEvent.searchForBatteringRamDRM();
    }

    public int getSiegeTowerDRM() {
        return dayEvent.searchForSiegeTowerDRM();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Day ").append(dayNumber).append("\n");
        str.append(getEnemyB()).append("\n\n");
        str.append(getStatusB()).append("\n");
        str.append("You have ").append(getNumberOfActions()).append(" actions.").append("\n");
        str.append(deck.getCard(0).printDay(dayNumber - 1)).append("\n");
        return str.toString();
    }

    /**
     * Classe estática referente ao Dado
     *
     * @see GameData
     */
    public static class Die {

        private static final int[] DIE = {1, 2, 3, 4, 5, 6};

//        public Die() {
//            die = new int[6];
//            for (int i = 0; i < die.length; i++) {
//                die[i] = i + 1;
//            }
//        }
        public static int rollDie() {
            return DIE[(int) (Math.random() * DIE.length)];
        }

        public static int adjustDieResult(int dieResult) {
            if (dieResult < DIE[1]) {
                return DIE[1];
            } else if (dieResult > DIE[DIE.length - 1]) {
                return DIE[DIE.length - 1];
            }
            return dieResult;
        }

//        public static int rollDieAdjusted(){
//            return GameData.Die.adjustDieResult(GameData.Die.rollDie());
//        }
    }

}
