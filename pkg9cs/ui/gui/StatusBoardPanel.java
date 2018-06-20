/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
class StatusBoardPanel extends JPanel implements Constants {

    private ObservableGame observableGame;

    private static BufferedImage imageStatusBoard = null;
    private static BufferedImage token = null;

    public static BufferedImage getBackgroundImage() {
        return imageStatusBoard;
    }

    static {
        try {
            imageStatusBoard = ImageIO.read(Resources.getResourceFile("images/statusboard.png"));
            token = ImageIO.read(Resources.getResourceFile("images/rounded-black-square-shape.png"));
        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public StatusBoardPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;

        setLocation(0, 0);
        DimensionClass.setAllSizes(this, DIM_X_BOARD_PANEL, DIM_Y_BOARD_PANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imageStatusBoard, 0, 0, getWidth(), getHeight(), this);

        //Draw Posições da Wall, Supply, Morale
        if (observableGame.getWallPos() == 0) {
            g.drawImage(token, 150, 400, 25, 25, this);
        } else {
            g.drawImage(token, getWidth() / 8, (4 - observableGame.getWallPos()) * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        if (observableGame.getMoralePos() == 0) {
            g.drawImage(token, 165, 400, 25, 25, this);
        } else {
            g.drawImage(token, 160, (4 - observableGame.getMoralePos()) * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        if (observableGame.getSupplyPos() == 0) {
            g.drawImage(token, 180, 400, 25, 25, this);
        } else {
            g.drawImage(token, 6 * getWidth() / 8, (4 - observableGame.getSupplyPos()) * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        //Soldiers
        g.drawImage(token, observableGame.getSoldiersPos() * 55 + 35, 11 * getHeight() / 12, 25, 25, this);

        //Supply Count
        if (observableGame.getSupplyCount() > 0) {
            g.drawImage(token, 6 * getWidth() / 8, getHeight() - (60 * observableGame.getSupplyCount()), 25, 25, this);
        }
    }

}
