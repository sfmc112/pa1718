/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui;

import java.util.Scanner;
import pkg9cs.controller.GameController;
import pkg9cs.states.AwaitAction;
import pkg9cs.states.AwaitDrawCard;
import pkg9cs.states.IState;
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class TextUI {
    GameController controller;
    boolean run;

    public TextUI(GameController gameController) {
        controller = gameController;
        this.run = true;
    }
    
    public void run(){
        IState state=null;
        while(run){
            state= controller.getState();
            if(state instanceof StartGame){
                startGameUi();
            }else if(state instanceof AwaitDrawCard){
                drawCardUi();
            }else if(state instanceof AwaitAction){
                awaitActionUi();
            }
        }
    }

    private void startGameUi() {
        //TODO
        System.out.println(controller.startGameMenu());
        int opt = readOption();
        switch(opt){
            case 1:
                controller.startGame();
                break;
            case 2:
                load();
                break;
            case 3:
                save();
                break;
            case 4:
                run=false;
        }
    }

    private void drawCardUi() {
        //TODO
        System.out.println(controller.drawCardMenu());
        int opt = readOption();
        switch(opt){
            case 1:
                controller.drawCard();
                break;
            case 2:
                save();
                break;
            case 3:
                load();
                break;
            case 4:
                run=false;
        }
    }

    private void awaitActionUi() {
        System.out.println(controller.awaitActionMenu());
        int opt = readOption();
        switch(opt){
        }
    }

    private int readOption() {
        Scanner in = new Scanner(System.in);
        while(!in.hasNextInt()){
            in.next();
        }
        return in.nextInt();
    }

    private void load() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void save() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
