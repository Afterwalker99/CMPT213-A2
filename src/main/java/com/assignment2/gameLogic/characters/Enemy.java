package com.assignment2.gameLogic.characters;

import com.assignment2.gameLogic.common.Position;

/**
 * This Enemy Class is a subclass of Character class. It holds
 * the information about an Enemy. The information includes in
 * the current position and the previous position of the enemy
 * the map. This class supports accessing and modifying the
 * enemy's current position and previous position.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Enemy extends Character {
    /**
     * Non-Default Constructor.
     * @param x Width coordinate in the map.
     * @param y Height coordinate in the map.
     */
    public Enemy (int x, int y) { super(new Position(x, y)); }

    @Override
    public String toString() {
        return "Enemy{" + "currentPosition=" + super.getCurrentPosition() + '}';
    }
}
