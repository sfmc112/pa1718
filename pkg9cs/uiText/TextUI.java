/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.uiText;

import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import pkg9cs.controller.GameController;
import pkg9cs.model.elements.*;
import pkg9cs.states.*;

/**
 *
 * @author sarah
 */
public class TextUI implements Observer, Serializable {

    GameController controller;
    boolean run;

    public TextUI(GameController game) {
        controller = game;
        controller.addObserver(this);
        this.run = true;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void run() {
        IState state = null;
        while (run) {
            state = controller.getState();
            if (state instanceof StartGame) {
                startGameUi();
            } else if (state instanceof AwaitAction) {
                awaitActionUi();
            } else if (state instanceof AwaitAddActionPoint) {
                addActionPointMenuUi();
            } else if (state instanceof AwaitAddSupplyRallyTroops) {
                rallyTroopsMenuUi();
            } else if (state instanceof AwaitDrawCard) {
                drawCardUi();
            } else if (state instanceof AwaitEnemySelectionArchersAttack) {
                enemySelectionArchersUi();
            } else if (state instanceof AwaitEnemySelectionBoilingWaterAttack) {
                enemySelectionBoilingAttackUi();
            } else if (state instanceof AwaitEnemySelectionCloseCombatAttack) {
                enemySelectionCloseCombatUi();
            } else if (state instanceof AwaitTunnelMovementSelection) {
                tunnelMenuUi();
            } else if (state instanceof GameLost) {
                lostMenuUi();
            } else if (state instanceof GameWon) {
                winMenuUi();
            } else if (state instanceof RaidAndSabotageActionsOnly) {
                raidAndSabotageMenuUi();
            }
        }
    }
    
    

    private void startGameUi() {
        System.out.println(controller.startGameMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.newGame();
                break;
            case 2:
                loadText();
                break;
            case 3:
                run = false;
        }
    }

    private void drawCardUi() {
        System.out.println(controller.drawCardMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.drawCard();
                break;
            case 2:
                saveText();
                break;
            case 3:
                loadText();
                break;
            case 4:
                System.out.println(controller.statusBoard());
                break;
            case 5:
                run = false;
        }
    }

    private void awaitActionUi() {
        System.out.println(controller.awaitActionMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.checkEnemiesArchers();
                break;
            case 2:
                controller.checkEnemiesBoilingWater();
                break;
            case 3:
                controller.checkEnemiesCloseCombat();
                break;
            case 4:
                controller.coupure();
                break;
            case 5:
                controller.askUseOfSupply();
                break;
            case 6:
                controller.selectTunnelMov();
                break;
            case 7:
                controller.supplyRaid();
                break;
            case 8:
                controller.sabotage();
                break;
            case 9:
                controller.askAddActionPoint();
                break;
            case 10:
                controller.endTurn();
                break;
            case 11:
                saveText();
                break;
            case 12:
                loadText();
                break;
            case 13:
                run = false;

        }
    }

    private void enemySelectionArchersUi() {
        System.out.println(controller.archersMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.archersAttack(new Ladder());
                break;
            case 2:
                controller.archersAttack(new Ram());
                break;
            case 3:
                controller.archersAttack(new SiegeTower());
                break;
            case 4:
                controller.returnToMenu();
                break;
        }
    }

    private void tunnelMenuUi() {
        System.out.println(controller.tunnelMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.moveInTunnel();
                break;
            case 2:
                controller.freeMovement();
                break;
            case 3:
                controller.fastMovement();
                break;
            case 4:
                controller.returnToMenu();
                break;
        }
    }

    private void enemySelectionBoilingAttackUi() {
        System.out.println(controller.boilingAttackMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.boilingWaterAttack(new Ladder());
                break;
            case 2:
                controller.boilingWaterAttack(new Ram());
                break;
            case 3:
                controller.boilingWaterAttack(new SiegeTower());
                break;
            case 4:
                controller.returnToMenu();
                break;
        }
    }

    private void enemySelectionCloseCombatUi() {
        System.out.println(controller.closeCombatAttackMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.closeCombatAttack(new Ladder());
                break;
            case 2:
                controller.closeCombatAttack(new Ram());
                break;
            case 3:
                controller.closeCombatAttack(new SiegeTower());
                break;
            case 4:
                controller.askAddActionPoint();
                break;
            case 5:
                controller.returnToMenu();
                break;

        }
    }

    private void winMenuUi() {
        System.out.println(controller.winMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.endGame();
                break;
            case 2:
                run = false;
        }
    }

    private void lostMenuUi() {
        System.out.println(controller.lostMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.endGame();
                break;
            case 2:
                run = false;
        }
    }

    private void addActionPointMenuUi() {
        System.out.println(controller.addActionPointMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.buyActionPoint(new Supply());
                break;
            case 2:
                controller.buyActionPoint(new Morale());
                break;
            case 3:
                controller.endTurn();
                break;
            case 4:
                controller.returnToMenu();
        }
    }

    private void rallyTroopsMenuUi() {
        System.out.println(controller.rallyTroopsMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.rallyTroops(new Supply());
                break;
            case 2:
                controller.rallyTroops();
                break;
            case 3:
                controller.returnToMenu();
        }
    }

    private void raidAndSabotageMenuUi() {
        System.out.println(controller.raidAndSabotageMenu());
        int opt = readOption();
        switch (opt) {
            case 1:
                controller.supplyRaid();
                break;
            case 2:
                controller.sabotage();
                break;
            case 3:
                controller.askAddActionPoint();
                break;
            case 4:
                controller.endTurn();
        }
    }

    private int readOption() {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            in.next();
        }
        return in.nextInt();
    }

    private void loadText() {
        System.out.print("\nName of the file to load:  ");
        String filename = readFileName();
        if (filename == null || filename.isEmpty()) {
            return;
        }
        try {
            setController(load_game(filename));
            System.out.println("\nGame loaded.\n");
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found.\n");
        } catch (IOException ex) {

        }
    }

    private GameController load_game(String filename) throws FileNotFoundException, IOException {
        ObjectInputStream objectIStream = null;
        GameController control = null;
        try {
            objectIStream = new ObjectInputStream(new FileInputStream(filename));
            control = (GameController) objectIStream.readObject();
        } catch (FileNotFoundException exception) {
            System.err.println("Erro: ficheiro inexistente\n" + exception.getMessage());
            throw new FileNotFoundException();
        } catch (IOException | ClassNotFoundException exception) {
        } finally {
            if (objectIStream != null) {
                try {
                    objectIStream.close();
                } catch (IOException exception) {
                }
            }
        }
        return control;
    }

    private void saveText() {
        System.out.println("\nName of the file to save: ");
        String filename = readFileName();
        if (filename == null || filename.isEmpty()) {
            return;
        }
        save_game(filename);
        System.out.println("\nGame saved\n");
    }

    private void save_game(String filename) {
        ObjectOutputStream objectOStream = null;
        try {
            objectOStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOStream.writeObject(this.controller);
        } catch (FileNotFoundException exception) {
            System.err.println("Erro: ficheiro inexistente\n" + exception.getMessage());
        } catch (IOException exception) {
        } finally {
            if (objectOStream != null) {
                try {
                    objectOStream.close();
                } catch (IOException exception) {
                }
            }
        }
    }

    private String readFileName() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(controller.printMSG());
    }

}
