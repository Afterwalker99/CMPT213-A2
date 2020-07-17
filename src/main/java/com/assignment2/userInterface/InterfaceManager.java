package com.assignment2.userInterface;

import com.assignment2.gameLogic.mapGrid.MapLiaison;
import com.assignment2.gameLogic.common.CellObject;
import java.util.Scanner;

/**
 * The InterfaceManager class stores the information of the text user interface of
 * the game. The information includes the screen title and symbols for each objects.
 * It provides the functionality to checks the integrity of user's input and printing
 * the screen title, menu option and help menu.
 */
public class InterfaceManager {
    // Title of the Game.
    private String screenTitle;
    // Symbol for enemy.
    private final char ENEMY_SYMBOL = '!';
    // Symbol for hero.
    private final char HERO_SYMBOL = '@';
    // Symbol for wall.
    private final char WALL_SYMBOL = '#';
    // Symbol for power.
    private final char POWER_SYMBOL = '$';
    // Symbol for path.
    private final char PATH_SYMBOL = ' ';
    // Symbol for undiscovered cell.
    private final char UNDISCOVERED_SYMBOL = '.';
    // Symbol for dead hero.
    private final char DEAD_HERO = 'X';

    /**
     * Non-Default Constructor
     * @param screenTitle: The title of the game.
     */
    public InterfaceManager(String screenTitle) {
        setScreenTitle(screenTitle);
    }

    /**
     * @param screenTitle The title of the game to be set.
     */
    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    /**
     * Checks whether the user's input is a valid input.
     * @param nextInput: User's input.
     * @return the user's input which is valid.
     */
    public char integrityCheck(char nextInput) {
        Scanner inputScanner = new Scanner(System.in);
        while (nextInput != 'w' && nextInput != 'a' && nextInput != 's' && nextInput != 'd'
                && nextInput != '?' && nextInput != 'm' && nextInput != 'e' && nextInput != 'c') {
            System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up)");
            System.out.print("Enter your move [WASD?]: ");
            nextInput = Character.toLowerCase(inputScanner.next().charAt(0));
        }
        return nextInput;
    }

    /**
     * Print the title of the game.
     */
    public void printScreenTitle() {
        System.out.printf("\n%s\n", screenTitle);
    }

    /**
     * Print the map of the game onto the screen.
     * @param outputMap The map of the game.
     */
    public void printMap(MapLiaison outputMap) {
        for (int i = 0; i <= outputMap.getMapHeight(); i++) {
            for (int j = 0; j <= outputMap.getMapWidth(); j++) {
                if (i == 0 || i == outputMap.getMapHeight() || j == 0 || j == outputMap.getMapWidth()){
                    System.out.print(WALL_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.ENEMY) {
                    System.out.print(ENEMY_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.HERO) {
                    System.out.print(HERO_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.POWER) {
                    System.out.print(POWER_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].isNotDiscovered()) {
                    System.out.print(UNDISCOVERED_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.WALL) {
                    System.out.print(WALL_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.PATH) {
                    System.out.print(PATH_SYMBOL);
                }

                else if (outputMap.getMapBoard()[i][j].getCellObject() == CellObject.DEAD_HERO) {
                    System.out.print(DEAD_HERO);
                }

                else {
                    System.out.println("ERROR IN PRINTING MAP.");
                }
            }

            System.out.println();
        }
    }

    /**
     * Print the menu of the game to the screen.
     * @param enemyCount: The amount of enemy alive.
     * @param powerCount: The amount of power the hero holds.
     * @param cheatStatus: Whether cheat mode is enabled.
     */
    public void printMenu(int enemyCount, int powerCount, boolean cheatStatus) {
        System.out.println("Total number of monsters to be killed: " +
                ((cheatStatus)? "1" : "3"));
        System.out.println("Number of powers currently in possession: " + (powerCount));
        System.out.println("Number of monsters alive: " + enemyCount);
    }

    /**
     * Print message to screen with a box around the text.
     * @param endMessage: The message to be print on the screen.
     */
    public void printSpecialMessage(String endMessage) {
        int containerPadding = 4;
        int starContainerSize = (containerPadding * 2) + endMessage.length();

        System.out.println("\n" + "*".repeat(starContainerSize));
        System.out.println("*" + " ".repeat(containerPadding - 1) + endMessage
                + " ".repeat(containerPadding - 1) + "*");
        System.out.println("*".repeat(starContainerSize));
    }

    /**
     * Print the help menu to the screen.
     * @param enemyCount: The amount of enemy alive.
     */
    public void printHelpMenu(int enemyCount) {
        System.out.println("\nDIRECTIONS:");
        System.out.printf("\tKill %d Monster(s)\n", enemyCount);
        System.out.println("LEGEND:");
        System.out.printf("\t%c: Wall\n", WALL_SYMBOL);
        System.out.printf("\t%c: You (a hero)\n", HERO_SYMBOL);
        System.out.printf("\t%c: Monster\n", ENEMY_SYMBOL);
        System.out.printf("\t%c: Power\n", POWER_SYMBOL);
        System.out.printf("\t%c: Unexplored Space\n", UNDISCOVERED_SYMBOL);
        System.out.println("MOVES:");
        System.out.println("\t Use W (up), A (left), S (down) and D (right) to move.");
        System.out.println("\t (You must press enter after each move).");
    }
}