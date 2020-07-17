package com.assignment2.gameLogic.mapGrid;

import com.assignment2.gameLogic.common.CellObject;
import com.assignment2.gameLogic.common.Position;

/**
 * This MapLiaison Class is a subclass of MapCore. It provides basic
 * functionality to be performed on the map. The functionality includes
 * accessors and modifier for the map and isDiscovered for each position
 * in the map.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class MapLiaison extends MapCore {

    /**
     * Non-Default Constructor.
     * @param mapWidth Width of the map.
     * @param mapHeight Height of the map.
     */
    public MapLiaison(int mapWidth, int mapHeight){
        super(mapWidth, mapHeight);
    }

    /**
     * @return the map of the game.
     */
    public Tile[][] getMapBoard() { return mapBoard; }

    /**
     * @return the width of the map.
     */
    public int getMapWidth() { return MAP_WIDTH; }

    /**
     * @return the height of the map.
     */
    public int getMapHeight() { return MAP_HEIGHT; }

    /**
     * Checks whether the position in map is not a wall and
     * in the bounds of the map.
     * @param checkPosition The position in the map to be checked.
     * @return true if the position in the map is not a wall and in
     * the bounds of the map.
     */
    public boolean isMovableSpot(Position checkPosition) {
        return (!isWall(checkPosition.getX(), checkPosition.getY())
                && isInBound(checkPosition.getX(), checkPosition.getY()));
    }

    /**
     * Set the specified cellObject in the specified position in the map.
     * @param position: The position on the map where the specified object is to be set at.
     * @param cellObject: The specified object.
     */
    public void setCellInformation (Position position, CellObject cellObject) {
        mapBoard[position.getY()][position.getX()].setCellObject(cellObject);
    }

    /**
     * Set the specified cellObject in two specified position in the map.
     * @param oldPosition: The position on the map where the a default object (PATH) is to be set at.
     * @param newPosition: The position on the map where the specified object is to be set at.
     * @param cellObject: The specified object.
     */
    public void setCellInformation (Position oldPosition, Position newPosition, CellObject cellObject) {
        if (mapBoard[oldPosition.getY()][oldPosition.getX()].getCellObject() == CellObject.HERO
                && cellObject == CellObject.ENEMY) {
            mapBoard[newPosition.getY()][newPosition.getX()].setCellObject(cellObject);
        }

        else if (mapBoard[newPosition.getY()][newPosition.getX()].getCellObject() == CellObject.HERO
                && cellObject == CellObject.ENEMY) {
            mapBoard[oldPosition.getY()][oldPosition.getX()].setCellObject(CellObject.PATH);
        }

        else {
            mapBoard[oldPosition.getY()][oldPosition.getX()].setCellObject(CellObject.PATH);
            mapBoard[newPosition.getY()][newPosition.getX()].setCellObject(cellObject);
        }
    }

    /**
     * Set a specific position in the map to be visible.
     * @param widthCoordinate: Width coordinate of the position.
     * @param heightCoordinate: Height coordinate of the position.
     */
    public void revealSurroundingCell (int widthCoordinate, int heightCoordinate) {
        for (int currentWidth = widthCoordinate - 1; currentWidth < widthCoordinate + 2; currentWidth++) {
            for (int currentHeight = heightCoordinate - 1; currentHeight < heightCoordinate + 2; currentHeight++) {
                if ((mapBoard[currentHeight][currentWidth].isNotDiscovered())
                        && (isInBound(currentWidth,currentHeight))) {
                    mapBoard[currentHeight][currentWidth].setIsNotDiscovered(false);
                }
            }
        }
    }

    /**
     * Set all position in the map to be visible.
     */
    public void revealAllSurrounding () {
        for (int currentWidth = 1; currentWidth < MAP_WIDTH; currentWidth++) {
            for (int currentHeight = 1; currentHeight < MAP_HEIGHT; currentHeight++) {
                mapBoard[currentHeight][currentWidth].setIsNotDiscovered(false);
            }
        }
    }
}