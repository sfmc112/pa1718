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
import pkg9cs.states.StartGame;

/**
 *
 * @author sarah
 */
public class AwaitBeginningPanel extends JPanel implements Observer, Constants {

    private ObservableGame observableGame;

    static protected BufferedImage background9CS = null;

    public static BufferedImage getBackgroundImage() {
        return background9CS;
    }

    static {
        try {
            background9CS = ImageIO.read(Resources.getResourceFile("images/9cs_logo.png"));
        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public AwaitBeginningPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;
        this.observableGame.addObserver(this);

        setLocation(X_START_MAIN_PANEL, Y_START_MAIN_PANEL);
        DimensionClass.setMinAndPreferredSize(this, BG_IMG_X_DIM, BG_IMG_Y_DIM);

        update(observableGame, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background9CS, 0, 0, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(observableGame.getState() instanceof StartGame);
    }

}
