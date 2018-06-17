/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pkg9cs.controller.ObservableGame;
import pkg9cs.states.AwaitDrawCard;
import static pkg9cs.ui.gui.AwaitBeginningPanel.background9CS;

/**
 *
 * @author sarah
 */
class CardPanel extends JPanel implements Constants, Observer {

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
        this.observableGame.addObserver(this);
        //TODO

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (observableGame.getState() instanceof AwaitDrawCard) {
                    observableGame.drawCard();
                }
            }
        });

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
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //setFocusable(observableGame.getState() instanceof AwaitDrawCard);
    }

}
