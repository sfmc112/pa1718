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

    private static final int COUPURE_STRENGTH = 4;
    private static final int RALLYTROOPS_STRENGTH = 4;
    private static final int SABOTAGE_STRENGTH = 4;

    private EnemyBoard enemyB;
    private StatusBoard statusB;

    private int dayNumber;
    private int numberOfActions;
    private CardDayEvent dayEvent;

    private boolean usedExtraAP;
    private boolean usedBoiling;
    private boolean canUseFreeMovement;

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
        canUseFreeMovement = true;

        deck = new CardPile();
        deck.setNewCards();

        discarded = new CardPile();
    }

    /**
     * ******************************************************************************************
     */
    /*---------------------------------------GETS / SETS---------------------------------------*/
    /**
     * ******************************************************************************************
     */
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

    public boolean isCanUseFreeMovement() {
        return canUseFreeMovement;
    }

    public void setCanUseFreeMovement(boolean canUseFreeMovement) {
        this.canUseFreeMovement = canUseFreeMovement;
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

    public boolean addActionPoint(Element e) {
        return checkStatusTypeAndAddActionPoint(e);
    }

    private boolean checkStatusTypeAndAddActionPoint(Element e) {
        if (e instanceof Morale) {
            return addActionPointWithMorale();
        } else {
            return addActionPointWithSupply();
        }
    }

    private boolean addActionPointWithMorale() {
        if (!checkAvailableMorale()) {
            return false;
        }
        numberOfActions++;
        statusB.advanceMorale();
        usedExtraAP = true;
        return true;
    }

    private boolean addActionPointWithSupply() {
        if (!checkAvailableSupplies()) {
            return false;
        }
        numberOfActions++;
        statusB.advanceSupply();
        usedExtraAP = true;
        return true;
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

    public boolean suppliesFull() {
        return statusB.getSupplyCount() >= 2;
    }

    /**
     * ******************************************************************************************
     */
    /*-------------------------------New Day / New Turn Setups---------------------------------*/
 /*
     * ******************************************************************************************
     */
    /**
     * Prepara o jogo para o próximo dia
     *
     * @return true se ainda é para continuar em jogo (ainda não se jogou os 3
     * dias)
     */
    public boolean newDaySetup() {

        dayNumber++;
        newTurnSetup();

        clearDecks();
        deck.setNewCards();
        shuffleCards(deck);

        return dayNumber <= 3;
    }

    public void moveCardToDiscardedPile() {
        discarded.receiveCard(deck.transferCard(0));
    }

    public void newTurnSetup() {
        usedExtraAP = false;
        usedBoiling = false;
        canUseFreeMovement = true;
        numberOfActions = 0;
        dayEvent = null;
    }

    private void clearDecks() {
        discarded.clearCardPile();
        deck.clearCardPile();
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Card Actions------------------------------------------*/
    /**
     * ******************************************************************************************
     */
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

    public boolean isNotTheLastCard() {
        return deck.getCardPileSize() > 1;
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

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Essential Checks--------------------------------------*/
    /**
     * ******************************************************************************************
     */
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
        statusB.advanceMorale();
    }

    public boolean immediateLossCheck() {
        return enemyB.checkImmediateLossOnCloseCombat() || statusB.checkImmediateLossOnSurrender();
    }

    public boolean endOfTurnLossCheck() {
        return enemyB.checkEndOfTurnLossOnCloseCombat() || statusB.checkEndOfTurnLossOnSurrender();
    }

    public boolean checkIfWon() {
        return !newDaySetup();
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

    public boolean checkAvailableMorale() {
        return statusB.checkAvailableMorale();
    }

    public boolean checkSoldiersOnEnemyLine() {
        return statusB.checkSoldiersOnEnemyLine();
    }

    public boolean checkSoldiersInCastle() {
        return statusB.checkSoldiersInCastle();
    }

    public boolean wallOnStartingSpace() {
        return statusB.wallOnStartingSpace();
    }

    public boolean moraleOnStartingSpace() {
        return statusB.moraleOnStartingSpace();
    }

    public boolean suppliesOnStartingSpace() {
        return statusB.suppliesOnStartingSpace();
    }

    public boolean existsTrebuchets() {
        return enemyB.getTrebuchetCount() > 0;
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Player Actions----------------------------------------*/
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Archers Attack----------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public boolean archersAttack(Weapon weapon) {
        return checkWeaponTypeAndArchersAttack(weapon);
    }

    /**
     * Ataca a weapon passada por argumento
     *
     * @param weapon Tipo de inimigo a atacar
     * @return true se o ataque foi permitido (sucesso ou insucesso)
     */
    private boolean checkWeaponTypeAndArchersAttack(Weapon weapon) {
        if (weapon instanceof Ladder) {
            return archersAttackLadder();
        } else if (weapon instanceof Ram) {
            return archersAttackRam();
        } else {
            return archersAttackSiegeTower();
        }
    }

    private boolean archersAttackLadder() {
        if (enemyB.isLadderOnStartingSpace()) {
            return false;
        }
        if (enemyB.isLadderOnCloseCombatSpace()) {
            return archersAttackLadderOnCloseCombat();
        }
        if (enemyB.isLadderOnCircleSpace()) {
            return archersAttackLadderOnCircleSpace();
        }

        return archersAttackLadderOnSquareSpace();
    }

    private boolean archersAttackLadderOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM() + getLadderDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean archersAttackLadderOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getLadderDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ladder.STRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean archersAttackLadderOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getLadderDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ladder.STRENGTH) {
            enemyB.retreatLadder();
        }
        return true;
    }

    private boolean archersAttackRam() {
        if (enemyB.isBatteringRamOnStartingSpace()) {
            return false;
        }
        if (enemyB.isBatteringRamOnCloseCombatSpace()) {
            return archersAttackRamOnCloseCombat();
        }
        if (enemyB.isBatteringRamOnCircleSpace()) {
            return archersAttackRamOnCircleSpace();
        }

        return archersAttackRamOnSquareSpace();
    }

    private boolean archersAttackRamOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM() + getBatteringRamDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean archersAttackRamOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getBatteringRamDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ram.STRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean archersAttackRamOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getBatteringRamDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ram.STRENGTH) {
            enemyB.retreatRam();
        }
        return true;
    }

    private boolean archersAttackSiegeTower() {
        if (!enemyB.isTowerPresent() || enemyB.isSiegeTowerOnStartingSpace()) {
            return false;
        }
        if (enemyB.isSiegeTowerOnCloseCombatSpace()) {
            return archersAttackTowerOnCloseCombat();
        }
        if (enemyB.isSiegeTowerOnCircleSpace()) {
            return archersAttackTowerOnCircleSpace();
        }

        return archersAttackTowerOnSquareSpace();
    }

    private boolean archersAttackTowerOnCloseCombat() {
        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM() + getSiegeTowerDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    private boolean archersAttackTowerOnCircleSpace() {
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getSiegeTowerDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > SiegeTower.STRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    private boolean archersAttackTowerOnSquareSpace() {
        int dieResult = GameData.Die.rollDie() + getSiegeTowerDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > SiegeTower.STRENGTH) {
            enemyB.retreatTower();
        }
        return true;
    }

    /**
     * ******************************************************************************************
     */
    /*-------------------------------Boiling Water Attack--------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public boolean boilingWaterAttack(Weapon weapon) {
        return checkWeaponTypeAndBoilingWaterAttack(weapon);
    }

    private boolean checkWeaponTypeAndBoilingWaterAttack(Weapon weapon) {
        if (weapon instanceof Ladder) {
            return BoilingWaterAttackLadder();
        } else if (weapon instanceof Ram) {
            return BoilingWaterAttackRam();
        } else {
            return BoilingWaterAttackSiegeTower();
        }
    }

    private boolean BoilingWaterAttackLadder() {
        if (!enemyB.isLadderOnCircleSpace()) {
            return false;
        }
        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getLadderDRM() + 1;
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ladder.STRENGTH) {
            enemyB.retreatLadder();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        usedBoiling = true;
        return true;
    }

    private boolean BoilingWaterAttackRam() {
        if (!enemyB.isBatteringRamOnCircleSpace()) {
            return false;
        }

        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getBatteringRamDRM() + 1;
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > Ram.STRENGTH) {
            enemyB.retreatRam();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        usedBoiling = true;
        return true;
    }

    private boolean BoilingWaterAttackSiegeTower() {
        if (!enemyB.isTowerPresent() || !enemyB.isSiegeTowerOnCircleSpace()) {
            return false;
        }

        int dieResult = GameData.Die.rollDie() + getCircleSpaceDRM() + getSiegeTowerDRM() + 1;
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > SiegeTower.STRENGTH) {
            enemyB.retreatTower();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        usedBoiling = true;
        return true;

    }

    /**
     * ******************************************************************************************
     */
    /*-------------------------------Close Combat Attack---------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public boolean closeCombatAttack(Weapon weapon) {
        return checkWeaponTypeAndCloseCombatAttack(weapon);
    }

    private boolean checkWeaponTypeAndCloseCombatAttack(Weapon weapon) {
        if (weapon instanceof Ladder) {
            return closeCombatAttackLadder();
        } else if (weapon instanceof Ram) {
            return closeCombatAttackRam();
        } else {
            return closeCombatAttackSiegeTower();
        }
    }

    private boolean closeCombatAttackLadder() {
        if (!enemyB.isLadderOnCloseCombatSpace()) {
            return false;
        }

        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatLadder();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        return true;
    }

    private boolean closeCombatAttackRam() {
        if (!enemyB.isBatteringRamOnCloseCombatSpace()) {
            return false;
        }

        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatRam();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        return true;
    }

    private boolean closeCombatAttackSiegeTower() {
        if (!enemyB.isTowerPresent() || !enemyB.isSiegeTowerOnCircleSpace()) {
            return false;
        }

        int dieResult = GameData.Die.rollDie() + getCloseCombatSpaceDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > CloseCombat.CLOSECOMBATSTRENGTH) {
            enemyB.retreatTower();
        } else if (dieResult <= 1) {
            statusB.advanceMorale();
        }
        return true;
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Coupure Action----------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public void coupure() {
        int dieResult = GameData.Die.rollDie() + getCoupureDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > COUPURE_STRENGTH) {
            statusB.retreatWall();
        }
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Sabotage Action---------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public void sabotage() {
        int dieResult = GameData.Die.rollDie() + getSabotageDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        //System.out.println("Dado: " + dieResult);

        if (dieResult > SABOTAGE_STRENGTH) {
            enemyB.subtractTrebuchetCount();
        } else if (dieResult <= 1) {
            captureSoldiers();
        }
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Supply Raid Action------------------------------------*/
    /**
     * ******************************************************************************************
     */
    public void addSupplyCount() {
//        if (statusB.getSupplyCount() >= 2) {
//            return false;
//        }
        int dieResult = GameData.Die.rollDie() + getSupplyRaidDRM();
        dieResult = GameData.Die.adjustDieResult(dieResult);
        switch (dieResult) {
            case 1:
                captureSoldiers();
                break;
            case 2:
                break;
            case 3:
            case 4:
            case 5:
                statusB.addSupplyCount(1);
                break;
            default:
                statusB.addSupplyCount(2);
                break;
        }
//        return true;
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Rally Troops Action-----------------------------------*/
    /**
     * ******************************************************************************************
     */
    /**
     * ******************************************************************************************
     */
    /*-----------------------------------Tunnel Movement Action--------------------------------*/
    /**
     * ******************************************************************************************
     */
    public void moveInTunnel() {
        if (checkSoldiersInCastle()) {
            statusB.moveSoldiersTowardsEnemyLines();
        } else {
            statusB.moveSoldiersTowardsCastle();
        }

        canUseFreeMovement = false;
    }

    public void freeTunnelMovement() {
        if (canUseFreeMovement) {
            statusB.moveSoldiersThroughTunnel();
        }
        canUseFreeMovement = false;
    }

    public void fastTunnelMovement() {
        statusB.moveSoldiersFast();
    }

    /**
     * ******************************************************************************************
     */
    /*-----------------------------------DRM's-------------------------------------------------*/
    /**
     * ******************************************************************************************
     */
    private int getSabotageDRM() {
        return dayEvent.searchForSabotageDRM();
    }

    private int getMoraleDRM() {
        return dayEvent.searchForMoraleDRM();
    }

    private int getCircleSpaceDRM() {
        return dayEvent.searchForCircleSpaceDRM();
    }

    private int getCloseCombatSpaceDRM() {
        return dayEvent.searchForCloseCombatSpaceDRM();
    }

    private int getSupplyRaidDRM() {
        return dayEvent.searchForSupplyRaidDRM();
    }

    private int getCoupureDRM() {
        return dayEvent.searchForCoupureDRM();
    }

    private int getLadderDRM() {
        return dayEvent.searchForLadderDRM();
    }

    private int getBatteringRamDRM() {
        return dayEvent.searchForBatteringRamDRM();
    }

    private int getSiegeTowerDRM() {
        return dayEvent.searchForSiegeTowerDRM();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Day ").append(dayNumber).append("\n");
        str.append(getEnemyB()).append("\n\n");
        str.append(getStatusB()).append("\n");
        str.append("You have ").append(getNumberOfActions()).append(" actions.").append("\n");
        str.append(usedBoiling ? "You have used boiling water this turn\n" : "You have not used boiling water this turn!\n");
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
            if (dieResult < DIE[0]) {
                return DIE[0];
            } else if (dieResult > DIE[DIE.length - 1]) {
                return DIE[DIE.length - 1];
            }
            return dieResult;
        }

    }

}
