/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs;

import pkg9cs.controller.GameController;
import pkg9cs.model.EnemyBoard;
import pkg9cs.model.GameData;
import pkg9cs.ui.TextUI;

/**
 *
 * @author sarah
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TextUI textUI = new TextUI();
        textUI.run();

        /*
        System.out.println("Hello! Where are the elements?");
        EnemyBoard e = new EnemyBoard();
        System.out.println("The ladder is on the position " + e.getLadderPos());
        System.out.println("The ram is on the position " + e.getRamPos());
        System.out.println("The tower is on the position " + e.getTowerPos());

        e.advanceLadder();
        e.advanceRam();
        e.advanceTower();
        
        System.out.println("");
        System.out.println("The ladder is on the position " + e.getLadderPos());
        System.out.println("The ram is on the position " + e.getRamPos());
        System.out.println("The tower is on the position " + e.getTowerPos());
        
        e.retreatLadder();
        e.retreatRam();
        e.retreatTower();
        
        System.out.println("");
        System.out.println("The ladder is on the position " + e.getLadderPos());
        System.out.println("The ram is on the position " + e.getRamPos());
        System.out.println("The tower is on the position " + e.getTowerPos());
         */
//        GameData g = new GameData();
        
        //g.getCard(1).executeCard(g, g.getDayNumber());
        //g.getCardFromDeck(1).executeCard(g, 1);
        
//        System.out.println("");
//        System.out.println("The ladder is on the position " + g.getEnemyB().getLadderPos());
//        System.out.println("The ram is on the position " + g.getEnemyB().getRamPos());
//        System.out.println("The tower is on the position " + g.getEnemyB().getTowerPos());
//        System.out.println("Wall is on the position " + g.getStatusB().getWallPos());
//        System.out.println("Morale is on the position " + g.getStatusB().getMoralePos());
//        System.out.println("Supply is on the position " + g.getStatusB().getSupplyPos());
//        
//        g.getCardFromDeck(0).executeCard(g, 1);
//
//        System.out.println("");
//        System.out.println("The ladder is on the position " + g.getEnemyB().getLadderPos());
//        System.out.println("The ram is on the position " + g.getEnemyB().getRamPos());
//        System.out.println("The tower is on the position " + g.getEnemyB().getTowerPos());
//        System.out.println("Wall is on the position " + g.getStatusB().getWallPos());
//        System.out.println("Morale is on the position " + g.getStatusB().getMoralePos());
//        System.out.println("Supply is on the position " + g.getStatusB().getSupplyPos());

//        g.setDayNumber(2);
//        g.getCard(1).executeCard(g, g.getDayNumber());
//        g.getCard(2).executeCard(g, g.getDayNumber());
//        
//        System.out.println("");
//        System.out.println("The ladder is on the position " + g.getEnemyB().getLadderPos());
//        System.out.println("The ram is on the position " + g.getEnemyB().getRamPos());
//        System.out.println("The tower is on the position " + g.getEnemyB().getTowerPos());
//        System.out.println("Wall is on the position " + g.getAllyB().getWallPos());
//        System.out.println("Morale is on the position " + g.getAllyB().getMoralePos());
//        System.out.println("Supply is on the position " + g.getAllyB().getSupplyPos());
//        
//        g.setDayNumber(3);
//        g.getCard(1).executeCard(g, g.getDayNumber());
//        g.getCard(2).executeCard(g, g.getDayNumber());
//        
//        System.out.println("");
//        System.out.println("The ladder is on the position " + g.getEnemyB().getLadderPos());
//        System.out.println("The ram is on the position " + g.getEnemyB().getRamPos());
//        System.out.println("The tower is on the position " + g.getEnemyB().getTowerPos());
//        System.out.println("Wall is on the position " + g.getAllyB().getWallPos());
//        System.out.println("Morale is on the position " + g.getAllyB().getMoralePos());
//        System.out.println("Supply is on the position " + g.getAllyB().getSupplyPos());
    }

}
