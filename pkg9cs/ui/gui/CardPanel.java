/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;
import pkg9cs.states.AwaitDrawCard;

/**
 *
 * @author sarah
 */
class CardPanel extends JPanel implements Constants {

    private ObservableGame observableGame;

    private static List<BufferedImage> cards = null;
    private static BufferedImage cardBack = null;

    public static BufferedImage getCardImage(int index) {
        return cards.get(index);
    }

    public static BufferedImage getCardBack() {
        return cardBack;
    }

    static {
        cards = new ArrayList<>();

        try {
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_1.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_2.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_3.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_4.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_5.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_6.png")));
            cards.add(ImageIO.read(Resources.getResourceFile("images/card_7.png")));

            cardBack = ImageIO.read(Resources.getResourceFile("images/back_card.png"));
        } catch (IOException e) {
            //System.out.println("Error loading image");
        }
    }

    public CardPanel(ObservableGame observableGame) {
        this.observableGame = observableGame;

        setLocation(0, 0);
        DimensionClass.setAllSizes(this, DIM_X_CARD_PANEL, DIM_Y_CARD_PANEL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cardIndex = observableGame.getActiveCardNumber() - 1;
        if (observableGame.getState() instanceof AwaitDrawCard) {
            g.drawImage(getCardBack(), 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(getCardImage(cardIndex), 0, 0, getWidth(), getHeight(), this);
            g.setColor(Color.blue);

            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(10F));

            g2D.drawRect(0, (observableGame.getDayNumber() - 1) * (getHeight() / 3), getWidth(), getHeight() / 3);
        }
    }
}
