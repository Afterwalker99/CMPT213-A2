package com.assignment2.gameLogic.characters;

import com.assignment2.gameLogic.common.Position;

/**
 * This Character Class is a superclass that holds the information
 * about a character (Enemy or Player). The information includes
 * the current position and the previous position of the character
 * in the map. This class supports accessing and modifying the
 * character's current position and previous position.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public abstract class Character {
    // Character's current position.
    private Position currentPosition;
    // Character's previous position.
    private Position previousPosition;

    /**
     * Non-Default Constructor.
     * @param currentPosition: Character's current position.
     */
    public Character (Position currentPosition) {
        this.currentPosition = currentPosition;
        this.previousPosition = null;
    }

    /**
     * @return Character's current position.
     */
    public Position getCurrentPosition() { return currentPosition; }

    /**
     * @param currentPosition: Character's new current position to set.
     */
    public void setCurrentPosition(Position currentPosition) { this.currentPosition = currentPosition; }

    /**
     * @return Character's previous position.
     */
    public Position getPreviousPosition() { return previousPosition; }

    /**
     * @param previousPosition: Character's new previous position to set.
     */
    public void setPreviousPosition(Position previousPosition) { this.previousPosition = previousPosition; }
}
