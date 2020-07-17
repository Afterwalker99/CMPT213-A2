package com.assignment2.gameLogic.common;

/**
 * This Position Class holds the information of a width
 * and height coordinates. This class supports accessing
 * and modifying the width and height coordinates.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Position {
    // Width Coordinates.
    private int x;
    // Height Coordinates.
    private int y;

    /**
     * Default Constructor.
     */
    public Position() {
        setX(0);
        setY(0);
    }

    /**
     * Non-Default Constructor.
     * @param x Width Coordinate of the Position.
     * @param y Height Coordinate of the Position.
     */
    public Position(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * @return Width Coordinate of the Position.
     */
    public int getX() {
        return x;
    }

    /**
     * @param x Width Coordinate of the Position to be set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return Height Coordinate of the Position.
     */
    public int getY() {
        return y;
    }

    /**
     * @param y Height Coordinate of the Position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks whether two position are equal.
     * @param y Position to be compared with.
     * @return true if the 2 position are equal and vise versa.
     */
    public boolean isEqual (Position y) {
        if (y == null) {
            return false;
        }

        return (this.getX() == y.getX() && this.getY() == y.getY());
    }

    @Override
    public String toString() { return "x: " + getX() + " y: " + getY(); }
}
