/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

/**
 *
 * @author sarah
 */
public interface Constants {

    static final int BG_IMG_X_DIM = AwaitBeginningPanel.background9CS.getWidth();
    static final int BG_IMG_Y_DIM = AwaitBeginningPanel.background9CS.getHeight();

    static final int DIM_X_FRAME = BG_IMG_X_DIM;
    static final int DIM_Y_FRAME = BG_IMG_Y_DIM + 65;

    static final int DIM_X_MAIN_PANEL = DIM_X_FRAME;
    static final int DIM_Y_MAIN_PANEL = DIM_Y_FRAME;

    static final int X_START_MAIN_PANEL = 0, Y_START_MAIN_PANEL = 0;
    
    static final int DIM_X_BOARD_PANEL = 370;
    static final int DIM_Y_BOARD_PANEL = 535;
    
    static final int DIM_X_CARD_PANEL = 225;
    static final int DIM_Y_CARD_PANEL = 325;
    
    static final int DIM_X_DIE_PANEL = 100;
    static final int DIM_Y_DIE_PANEL = 100;
    
    static final int DIM_X_OPTION_PANEL = DIM_X_MAIN_PANEL;
    static final int DIM_Y_OPTION_PANEL = DIM_Y_MAIN_PANEL - DIM_Y_BOARD_PANEL;

}
