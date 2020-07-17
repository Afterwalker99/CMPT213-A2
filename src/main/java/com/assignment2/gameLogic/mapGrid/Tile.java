package com.assignment2.gameLogic.mapGrid;

import com.assignment2.gameLogic.common.CellObject;

/**
 * This Tile Class holds the information of a cell on the map.
 * This class supports accessing and modifying the object
 * stored in the cell along with its visibility.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Tile {

    // Object stored in the cell.
    private CellObject cellObject;
    // Whether the cell is visible.
    private boolean tileNotDiscovered;

    /**
     * Default Constructor
     */
    public Tile () {
        this.cellObject = CellObject.WALL;
        tileNotDiscovered = true;
    }

    /**
     * @return The object (CellObject) stored inside the cell.
     */
    public CellObject getCellObject() { return cellObject; }

    /**
     * @return True is the cell is not visible and vise versa.
     */
    public boolean isNotDiscovered() { return tileNotDiscovered; }

    /**
     * @param cellObject Object (CellObject) to be set into the cell.
     */
    public void setCellObject(CellObject cellObject) { this.cellObject = cellObject; }

    /**
     * @param isNotDiscovered Boolean to toggle the visibility of the cell.
     */
    public void setIsNotDiscovered(boolean isNotDiscovered) { this.tileNotDiscovered = isNotDiscovered; }
}
