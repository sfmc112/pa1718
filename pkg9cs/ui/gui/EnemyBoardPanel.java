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
class EnemyBoardPanel extends JPanel implements Constants {

    private ObservableGame observableGame;

    private static BufferedImage imageEnemyBoard = null;
    private static BufferedImage token = null;

    public static BufferedImage getBackgroundImage() {
        return imageEnemyBoard;
    }

    static {
        try {
            imageEnemyBoard = ImageIO.read(Resources.getResourceFile("images/enemyboard.png"));
            token = ImageIO.read(Resources.getResourceFile("images/rounded-black-square-shape.png"));
        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public EnemyBoardPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;

        setLocation(X_START_MAIN_PANEL, Y_START_MAIN_PANEL);
        DimensionClass.setAllSizes(this, DIM_X_BOARD_PANEL, DIM_Y_BOARD_PANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imageEnemyBoard, 0, 0, getWidth(), getHeight(), this);

        //Draw Posições da Ladder, Ram, Siege e Trebuchets
        if (observableGame.getLadderPos() == 0) {
            g.drawImage(token, 150, getHeight() / 12, 25, 25, this);
        } else {
            g.drawImage(token, getWidth() / 8, observableGame.getLadderPos() * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        if (observableGame.getRamPos() == 0) {
            g.drawImage(token, getWidth() / 2, getHeight() / 12, 25, 25, this);
        } else {
            g.drawImage(token, getWidth() / 2, observableGame.getRamPos() * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        if (observableGame.getTowerPos() == 0) {
            g.drawImage(token, 210, getHeight() / 12, 25, 25, this);
        } else {
            g.drawImage(token, 6 * getWidth() / 8, observableGame.getTowerPos() * getHeight() / 6 + getWidth() / 10, 25, 25, this);
        }

        if (observableGame.getTrebuchetCount() > 0) {
            if (observableGame.getTrebuchetCount() == 1) {
                g.drawImage(token, getWidth() / 8, 11 * getHeight() / 12, 25, 25, this);
            } else {
                g.drawImage(token, (2 * observableGame.getTrebuchetCount()) * getWidth() / 8, 11 * getHeight() / 12, 25, 25, this);
            }
        }
    }

}
