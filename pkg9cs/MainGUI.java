/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs;

import pkg9cs.controller.GameController;
import pkg9cs.controller.ObservableGame;
import pkg9cs.ui.gui.NineCardSiegeView;
import pkg9cs.ui.textui.TextUI;

/**
 *
 * @author sarah
 */
public class MainGUI {
    public static void main(String[] args) {
        GameController g = new GameController();
        
        new NineCardSiegeView(new ObservableGame(g));
    }
}
