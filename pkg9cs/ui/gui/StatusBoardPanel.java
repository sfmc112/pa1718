/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
class StatusBoardPanel extends JPanel implements Constants, Observer{
    
    private ObservableGame observableGame;
    
    private static BufferedImage imageStatusBoard = null;

    public static BufferedImage getBackgroundImage() {
        return imageStatusBoard;
    }

    static {
        try {
            imageStatusBoard = ImageIO.read(Resources.getResourceFile("images/statusboard.png"));
        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public StatusBoardPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);
        //TODO
        
        setLocation(0, 0);        
        DimensionClass.setAllSizes(this, DIM_X_BOARD_PANEL, DIM_Y_BOARD_PANEL);
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(imageStatusBoard, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
