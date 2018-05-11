/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui;

import pkg9cs.controller.GameController;
import pkg9cs.states.AwaitAction;
import pkg9cs.states.AwaitDrawCard;
import pkg9cs.states.IState;
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class TextIU {
    GameController controller;
    boolean run;

    public TextIU() {
        controller = new GameController();
        this.run=true;
    }
    
    public void run(){
        IState state=null;
        while(!run){
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
        }
    }

    private void awaitActionUi() {
        //TODO
        System.out.println(controller.awaitActionMenu());
    }
    
    
    
}
