package com.assignment2.gameLogic.characters;

import com.assignment2.gameLogic.common.Position;

/**
 * This Hero Class is a subclass of Character class. It holds
 * the information about a Hero. The information includes the
 * current position and the previous position of the hero in
 * the map. It also holds the amount of power the hero holds.
 * This class supports accessing and modifying the hero's
 * current position, previous position and the amount of power
 * the hero holds.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Hero extends Character {
    //The amount of power the hero holds.
    private int amountOfPower;

    /**
     * Default Constructor.
     */
    public Hero() {
        super(new Position(1, 1));
        this.amountOfPower = 0;
    }

    /**
     * @return the amount of power the hero holds.
     */
    public int getPowerCount() { return amountOfPower; }

    /**
     * Increase the amount of power the hero holds by one.
     */
    public void incrementPower() { this.amountOfPower++; }

    /**
     * Decrement the amount of power the hero holds.
     * @param decrementAmount Amount to be decremented.
     */
    public void decrementPower(int decrementAmount) { this.amountOfPower -= decrementAmount; }

    @Override
    public String toString() {
        return "Hero{" +
                "currentPosition=" + super.getCurrentPosition() +
                ", amountOfPower=" + amountOfPower + '}';
    }
}
