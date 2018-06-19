/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
class DiePanel extends JPanel implements Constants {

    private static List<BufferedImage> die = null;

    private ObservableGame observableGame;

    static {
        die = new ArrayList<>();

        try {
            die.add(ImageIO.read(Resources.getResourceFile("images/die_1.png")));
            die.add(ImageIO.read(Resources.getResourceFile("images/die_2.png")));
            die.add(ImageIO.read(Resources.getResourceFile("images/die_3.png")));
            die.add(ImageIO.read(Resources.getResourceFile("images/die_4.png")));
            die.add(ImageIO.read(Resources.getResourceFile("images/die_5.png")));
            die.add(ImageIO.read(Resources.getResourceFile("images/die_6.png")));

        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public DiePanel(ObservableGame observableGame) {
        this.observableGame = observableGame;


        setLocation(0, 0);
        DimensionClass.setAllSizes(this, DIM_X_DIE_PANEL, DIM_Y_DIE_PANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        if (observableGame.getDie() > 0) {
            //OngoingGamePanel.switchDieLabel(true);
            g.drawImage(die.get(observableGame.getDie() - 1), 0, 0, getWidth(), getHeight(), this);
        } else {
            //OngoingGamePanel.switchDieLabel(false);
        }
    }

}
