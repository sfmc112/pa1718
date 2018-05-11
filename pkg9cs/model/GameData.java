/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import pkg9cs.model.cards.CardPile;
import pkg9cs.model.cards.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarah
 */
public class GameData {

    private EnemyBoard enemyB;
    private StatusBoard statusB;

    private int dayNumber;
    private int numberOfActions;

    private boolean usedExtraAP;

    private CardPile deck;
    private CardPile discarded;

    public GameData() {
        enemyB = new EnemyBoard();
        statusB = new StatusBoard();

        dayNumber = 1;
        numberOfActions = 0;

        usedExtraAP = false;

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
        usedExtraAP = false;
        numberOfActions = 0;
    }

    /**
     * Manda executar as acções da carta que está primeiro no deck
     */
    public boolean executeCard() {
        if (deck.getCardPileSize() <= 0) {
            return false;
        }

        getCardFromDeck(0).executeCard(this, dayNumber);
        discarded.receiveCard(deck.transferCard(0));
        return true;

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

    public void decreaseActionPoints() {
        if (numberOfActions > 0) {
            numberOfActions--;
        }
    }

    public Card getCardFromDeck(int index) {
        return deck.getCard(index);
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
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
        return enemyB.isTowerOnStartingSpace();
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

    }

    @Override
    public String toString() {
        //TODO
        // Tracks = NomeElemento + posição + nome da posição
        // Mostrar o Dia
        StringBuilder str = new StringBuilder();
        str.append(getEnemyB());
        str.append(getStatusB());
        str.append("You have ").append(getNumberOfActions()).append(" actions.");
        return str.toString();
    }

    
}
