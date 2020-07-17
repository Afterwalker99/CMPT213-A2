package com.assignment2.userInterface;

import com.assignment2.gameLogic.GameController;
import com.assignment2.gameLogic.common.Direction;
import java.util.Scanner;

/**
 * The game class is the main driver of the game. It includes the
 * game controller, interface manager and the game state of the
 * game. It provides functionality to update the game state and
 * manages the user's input.
 *
 * @author Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Game {
    // Game Controller of the Game.
    private static GameController gameController;
    // Interface Manager of the Game.
    private static InterfaceManager ourInterfaceManager;
    // The current game state.
    private boolean gameState;
    // Whether cheat mode is enabled.
    private boolean cheatEnabled;

    /**
     * Default Constructor.
     */
    public Game() {
        gameController = new GameController();
        ourInterfaceManager = new InterfaceManager("Maze Escape - Journey to Narnia");
        this.gameState = true;
    }

    /**
     * Checks whether cheat mode is enabled.
     * @return true is cheat mode is enabled and vise versa.
     */
    public boolean isCheatEnabled() { return cheatEnabled; }

    /**
     * Update the movement of each object in the map and manages the game state of the game.
     * @param inputDirection The direction that the hero move from current position.
     */
    public void updateGameState(Direction inputDirection) {
        if (gameController.moveHero(inputDirection)) {
            if (gameController.collisionChecker()) {
                gameController.heroGameEndListener();
                gameController.getOurMap().revealAllSurrounding();
                ourInterfaceManager.printMap(gameController.getOurMap());
                ourInterfaceManager.printSpecialMessage("Uh oh! I'm sorry, you have been eaten!");
                gameState = false;
                cheatEnabled = false;
                return;
            }

            gameController.moveEnemies();
        }
        else {
            System.out.println("Invalid move: you can't move through walls!");
            inputOptions();
        }

        if (gameController.collisionChecker()) {
            gameController.heroGameEndListener();
            gameController.getOurMap().revealAllSurrounding();
            ourInterfaceManager.printMap(gameController.getOurMap());
            ourInterfaceManager.printSpecialMessage("Uh oh! I'm sorry, you have been eaten!");
            gameState = false;
        }

        gameController.powerChecker();

        if (gameController.gameEndChecker()) {
            gameController.getOurMap().revealAllSurrounding();
            ourInterfaceManager.printMap(gameController.getOurMap());
            ourInterfaceManager.printSpecialMessage("GOOD JOB! You're a winner");
            gameState = false;
        }
    }

    /**
     * Maps all available keystrokes to respective option in the game.
     */
    public void inputOptions() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter your move [WASD?]: ");

        char checkedCharacter = ourInterfaceManager.integrityCheck(Character.toLowerCase(inputScanner.next().charAt(0)));

        switch (checkedCharacter){
            case 'w' -> updateGameState(Direction.UP);
            case 'a' -> updateGameState(Direction.LEFT);
            case 's' -> updateGameState(Direction.DOWN);
            case 'd' -> updateGameState(Direction.RIGHT);
            case '?' -> ourInterfaceManager.printHelpMenu(gameController.getEnemyControllerListSize());
            case 'm' -> gameController.getOurMap().revealAllSurrounding();
            case 'e' -> gameState = false;
            case 'c' -> {
                if(gameController.getEnemyControllerListSize() == 3) {
                    ourInterfaceManager.printSpecialMessage("Cheat mode has been enabled.");
                    cheatEnabled = true;
                    gameController.cheatsEnabled();
                }
                else {
                    ourInterfaceManager.printSpecialMessage("Oh no! Cheat mode can only be activated before any enemies have been slain.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Game ourGame = new Game();
        ourInterfaceManager.printHelpMenu(gameController.getEnemyControllerListSize());

        do {
            ourInterfaceManager.printScreenTitle();
            ourInterfaceManager.printMap(gameController.getOurMap());
            ourInterfaceManager.printMenu(gameController.getEnemyControllerListSize(),
                    gameController.getPowerCount(), ourGame.isCheatEnabled());
            ourGame.inputOptions();
        } while (ourGame.gameState);
    }
}