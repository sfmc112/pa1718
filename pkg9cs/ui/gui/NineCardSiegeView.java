/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import pkg9cs.controller.ObservableGame;

/**
 *
 * @author sarah
 */
public class NineCardSiegeView extends JFrame implements Observer, Constants {

    private ObservableGame observableGame;

    private static Font fonte = new Font("Book Antiqua", Font.ITALIC, 32);

    private JMenuBar menuBar;
    //private JToolBar toolBar;
    //private JPanel mainPanel;
    private AwaitBeginningPanel pAwaitBeginning;
    private OngoingGamePanel pOngoingGame;
//    private GameWonPanel pGameWon;
//    private GameLostPanel pGameLost;

    public NineCardSiegeView(ObservableGame observableGame) {
        this(observableGame, 200, 100, DIM_X_FRAME, DIM_Y_FRAME);
    }

    public NineCardSiegeView(ObservableGame obGame, int x, int y, int width, int height) {
        super("9 Card Siege");

        observableGame = obGame;
        observableGame.addObserver(this);

        //todo
        createComponents();
        displayView();

        pAwaitBeginning.setLayout(new BorderLayout());

        JPanel newPanel = new JPanel();
        JPanel loadPanel = new JPanel();
        JPanel sul = new JPanel();

        newPanel.setOpaque(false);
        loadPanel.setOpaque(false);
        sul.setOpaque(false);

        pAwaitBeginning.add(sul, BorderLayout.SOUTH);
        sul.setLayout(new GridLayout(3, 1));

        sul.add(newPanel);
        sul.add(loadPanel);

        HomeScreenButton btNew = new HomeScreenButton("New Game", "New");
        HomeScreenButton btLoad = new HomeScreenButton("Load Game", "Load");

        newPanel.add(btNew);
        loadPanel.add(btLoad);

        setLocation(x, y);
        setSize(width, height);

        DimensionClass.setAllSizes(this, width, height);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    private void createComponents() {
        menuBar = new BarraMenus();
        pAwaitBeginning = new AwaitBeginningPanel(observableGame);
        pOngoingGame = new OngoingGamePanel(observableGame);
        //TODO NEWS

    }

    private void displayView() {
        //Setup View
        Container cp = getContentPane();
        setJMenuBar(menuBar);

        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel();

        DimensionClass.setMinAndPreferredSize(mainPanel, BG_IMG_X_DIM, BG_IMG_Y_DIM);

        mainPanel.add(pAwaitBeginning);
        mainPanel.add(pOngoingGame);
        //todo add outros paineis

        //cp.setLayout(new BorderLayout());
        cp.add(Box.createVerticalGlue());
        cp.add(mainPanel);
        cp.add(Box.createVerticalGlue());
    }

    /*    Barra de menus    */
    class BarraMenus extends JMenuBar {

        private JMenu menu0;
        private JMenuItem mainItem;
        private JMenuItem newItem;
        private JMenuItem loadItem;
        private JMenuItem saveItem;
        private JMenuItem aboutItem;
        private JMenuItem exitItem;

        public BarraMenus() {
            criarObjGraf();
            disporVista();
        }

        private void criarObjGraf() {
            menu0 = new JMenu("File");

            mainItem = createNewMenuItem("Main Screen", "Go to the main screen", KeyEvent.VK_M, null);
            newItem = createNewMenuItem("New", "Start a new game", KeyEvent.VK_N, null);
            loadItem = createNewMenuItem("Load", "Load a previously saved game", KeyEvent.VK_L, "icons/open-folder.png");
            saveItem = createNewMenuItem("Save", "Save current game", KeyEvent.VK_S, "icons/floppy-disk.png");
            aboutItem = createNewMenuItem("About", "Show application information", KeyEvent.VK_A, "icons/information.png");
            exitItem = createNewMenuItem("Exit", "Exit application", KeyEvent.VK_E, "icons/exit.png");

        }

        private void disporVista() {
            add(menu0);
            menu0.add(mainItem);
            menu0.add(newItem);
            menu0.add(loadItem);
            menu0.add(saveItem);
            menu0.addSeparator();
            menu0.add(aboutItem);
            menu0.addSeparator();
            menu0.add(exitItem);
        }

        /**
         * Criar um novo JMenuItem genérico
         *
         * @param text nome do item de menu
         * @param tooltip
         * @param keyEvent tecla de atalho
         * @param iconName nome do ficheiro do icone do item de menu
         * @return
         */
        private JMenuItem createNewMenuItem(String text, String tooltip, int keyEvent, String iconName) {

            JMenuItem menuItem1 = new JMenuItem(text);
            menuItem1.setToolTipText(tooltip);
            menuItem1.setMnemonic(keyEvent);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.ALT_MASK));

            menuItem1.setActionCommand(text);

            if (iconName != null) {
                menuItem1.setIcon(new ImageIcon(Resources.getResourceFile(iconName)));
            }

            menuItem1.addActionListener(new MenuItemAction());

            return menuItem1;
        }

    }

    class MenuItemAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String filename = null;
            int n;

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Game file only", "g", "game");
            JFileChooser gestorFicheiros = new JFileChooser("");
            gestorFicheiros.setFileFilter(filter);

            String cmd = e.getActionCommand();
            //System.out.println("Executar o comando " + cmd);
            if (cmd == null) {
                return;
            }
            switch (cmd) {
                case "Main Screen": {
                    n = JOptionPane.showConfirmDialog(NineCardSiegeView.this, "Exit current game without saving?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        observableGame.endGame();
                    }
                }
                break;
                case "New": {
                    observableGame.endGame();
                    observableGame.newGame();
                }
                break;
                case "Load": {

                    n = gestorFicheiros.showOpenDialog(NineCardSiegeView.this);

                    if (n == JFileChooser.APPROVE_OPTION) {
                        filename = gestorFicheiros.getName(gestorFicheiros.getSelectedFile());

                    }

                    boolean loaded = observableGame.loadGame(filename);
                    
                    if(loaded)
                        JOptionPane.showMessageDialog(NineCardSiegeView.this, "Game was loaded sucessfully", "Load Game", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                }
                break;
                case "Save": {
                    n = gestorFicheiros.showSaveDialog(NineCardSiegeView.this);

                    if (n == JFileChooser.APPROVE_OPTION) {
                        filename = gestorFicheiros.getName(gestorFicheiros.getSelectedFile());
                    }

                    boolean saved = observableGame.saveGame(filename);
                    
                    if(saved)
                        JOptionPane.showMessageDialog(NineCardSiegeView.this, "Game was saved sucessfully", "Save Game", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                }
                break;
                case "About": {
                    JOptionPane.showMessageDialog(NineCardSiegeView.this, "Made by: Sarah Cunha", "About", JOptionPane.OK_OPTION, UIManager.getIcon("OptionPane.informationIcon"));
                }
                break;
                case "Exit": {
                    n = JOptionPane.showConfirmDialog(NineCardSiegeView.this, "Exit?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        System.exit(0);
                    }
                }

                break;
            }
        }
    }

    class HomeScreenButton extends JButton {

//        private double opacity;
        public HomeScreenButton(String name, String actionCommand) {
            super(name);

            setActionCommand(actionCommand);
            addActionListener(new MenuItemAction());

            DimensionClass.setAllSizes(this, 225, 50);

            setBackground(Color.lightGray);
            setFocusable(false);

//            opacity = 0.8;
            setFont(fonte);
        }

//        public void setOpacity(float opacity) {
//            this.opacity = opacity;
//        }
//
//        public double getOpacity() {
//            return opacity;
//        }
//
//        @Override
//        public void paint(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g.create();
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)getOpacity()));
//            super.paint(g2);
//            g2.dispose();
//        }
    }
}
