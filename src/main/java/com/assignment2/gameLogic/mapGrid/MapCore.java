package com.assignment2.gameLogic.mapGrid;

import com.assignment2.gameLogic.common.CellObject;
import com.assignment2.gameLogic.common.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This MapCore Class is a superclass that holds the information
 * about a map. The information includes the map (2D Matrix), and
 * the width and height of the map. This class supports checks for
 * whether a position on the map contains either wall or path.
 * It uses randomized recursive depth first search algorithm to
 * generate a random map for the game.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class MapCore {
    // The Map of the game.
    protected final Tile[][] mapBoard;
    // Width of the map.
    protected final int MAP_WIDTH;
    // Height of the map.
    protected final int MAP_HEIGHT;

    /**
     * Non-Default Constructor.
     * @param mapWidth Width of the map.
     * @param mapHeight Height of the map.
     */
    public MapCore(int mapWidth, int mapHeight){
        this.MAP_WIDTH = mapWidth - 1;
        this.MAP_HEIGHT = mapHeight - 1;
        mapBoard = new Tile[mapHeight][mapWidth];
        initMap();
    }

    /**
     * Initialize and create the map randomly using the recursive depth
     * first search algorithm.
     */
    private void initMap() {
        do {
            for (int i = 0; i <= MAP_HEIGHT; i++) {
                for (int j = 0; j <= MAP_WIDTH; j++) {
                    mapBoard[i][j] = new Tile();
                }
            }

            generateMap(new Position(1, 1));
            mapBoard[1][1].setCellObject(CellObject.PATH);
            mapBoard[1][MAP_WIDTH - 1].setCellObject(CellObject.PATH);
            mapBoard[MAP_HEIGHT - 1][1].setCellObject(CellObject.PATH);
            mapBoard[MAP_HEIGHT - 1][MAP_WIDTH - 1].setCellObject(CellObject.PATH);
        } while (!(isValidMap()));

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                mapBoard[i][j].setIsNotDiscovered(false);
            }
        }

        removeRandomWall();
        mapBoard[1][1].setCellObject(CellObject.HERO);
        mapBoard[1][MAP_WIDTH - 1].setCellObject(CellObject.ENEMY);
        mapBoard[MAP_HEIGHT - 1][1].setCellObject(CellObject.ENEMY);
        mapBoard[MAP_HEIGHT - 1][MAP_WIDTH - 1].setCellObject(CellObject.ENEMY);
    }


    /**
     * Checks whether the position is in the boundary of the map.
     * @param widthCoordinate Width Coordinate of the Position.
     * @param heightCoordinate Height Coordinate of the Position.
     * @return True if the position is inside the boundary of the map and vise versa.
     */
    protected boolean isInBound ( int widthCoordinate, int heightCoordinate) {
        return ((heightCoordinate <= MAP_HEIGHT - 1) &&
                (heightCoordinate >= 1) &&
                (widthCoordinate <= MAP_WIDTH - 1) &&
                (widthCoordinate >= 1));
    }

    /**
     * Checks whether there is a wall at the position in the map.
     * @param widthCoordinate Width Coordinate of the Position.
     * @param heightCoordinate Height Coordinate of the Position.
     * @return True if there is a wall at that position of the map and vise versa.
     */
    protected boolean isWall (int widthCoordinate, int heightCoordinate) {
        if (!isInBound(widthCoordinate, heightCoordinate)) {
            return false;
        }

        return mapBoard[heightCoordinate][widthCoordinate].getCellObject() == CellObject.WALL;
    }

    /**
     * Checks whether there is a path at the position in the map.
     * @param widthCoordinate Width Coordinate of the Position.
     * @param heightCoordinate Height Coordinate of the Position.
     * @return True if there is a path at that position of the map and vise versa.
     */
    protected boolean isPath (int widthCoordinate, int heightCoordinate) {
        if (!isInBound(widthCoordinate, heightCoordinate)) {
            return false;
        }

        return mapBoard[heightCoordinate][widthCoordinate].getCellObject() == CellObject.PATH;
    }

    /**
     * Helper Function for generateMaze: Checks whether that position is valid to be set as a path in the maze.
     * @param currentPosition The current position in the map.
     * @return True if it is valid to be set as a path in the maze and vise versa.
     */
    private boolean isValidSpot(Position currentPosition) {
        if (!isInBound(currentPosition.getX(), currentPosition.getY())) {
            return false;
        }

        if ((currentPosition.getX() == MAP_WIDTH - 2 && currentPosition.getY() == MAP_HEIGHT - 2) ||
                (currentPosition.getX() == MAP_WIDTH - 2 && currentPosition.getY() == 2) ||
                (currentPosition.getX() == 2 && currentPosition.getY() == MAP_HEIGHT - 2) ||
                (currentPosition.getX() == 2 && currentPosition.getY() == 2)) {
            return false;
        }

        List<Position> directionList = new ArrayList<>();
        directionList.add(new Position(0,-1));
        directionList.add(new Position(1,0));
        directionList.add(new Position(0,1));
        directionList.add(new Position(-1,0));

        int pathCounter = 0;

        for(Position direction: directionList) {
            Position newPosition = new Position(currentPosition.getX() + direction.getX(),
                    currentPosition.getY() + direction.getY());
            if (isInBound(newPosition.getX(), newPosition.getY()) && isPath(newPosition.getX(), newPosition.getY())){
                pathCounter++;
            }
        }

        return pathCounter <= 1;
    }

    /**
     * Checks the whether map generated fits into the all specification of the map.
     * @return True if the map satisfy all the specification of the map and vise versa.
     */
    private boolean isValidMap() {
        // Left to Right
        for (int i = 1; i < MAP_WIDTH - 1; i++) {
            // Check whether the cell itself is a wall
            if (mapBoard[1][i].getCellObject() == CellObject.WALL
                    && mapBoard[1][i + 1].getCellObject() == CellObject.WALL) {
                return false;
            }
            if (mapBoard[MAP_HEIGHT - 1][i].getCellObject() == CellObject.WALL
                    && mapBoard[MAP_HEIGHT - 1][i + 1].getCellObject() == CellObject.WALL) {
                return false;
            }
        }

        // Up to Down
        for (int i = 1; i < MAP_HEIGHT - 1; i++) {
            // Check whether the cell itself is a wall
            if (mapBoard[i][1].getCellObject() == CellObject.WALL
                    && mapBoard[i + 1][1].getCellObject() == CellObject.WALL) {
                return false;
            }
            if (mapBoard[i][MAP_WIDTH - 1].getCellObject() == CellObject.WALL
                    && mapBoard[i + 1][MAP_WIDTH - 1].getCellObject() == CellObject.WALL) {
                return false;
            }
        }

        if (isWall(MAP_WIDTH - 2, 1)
                && isWall(MAP_WIDTH - 1, 2)) {
            return false;
        }

        if (isWall(MAP_WIDTH - 2, MAP_HEIGHT - 1)
                && isWall(MAP_WIDTH - 1, MAP_HEIGHT - 2 )) {
            return false;
        }

        if (isWall(2, MAP_HEIGHT - 1)
                && isWall(1, MAP_HEIGHT - 2)) {
            return false;
        }

        return true;
    }

    /**
     * Helper Function for randomRemoveWall(): Checks whether the wall at the current position
     * of the map could be remove while still satisfying the specification of the map.
     * @param widthCoordinate: Width coordinate of the position.
     * @param heightCoordinate: Height coordinate of the position.
     * @return True if the wall at the current position of the map can be removed and vise versa.
     */
    private boolean cellSurroundingChecker (int widthCoordinate, int heightCoordinate) {
        // Top Left
        if (isPath(widthCoordinate - 1, heightCoordinate - 1)
                && isPath(widthCoordinate, heightCoordinate - 1)
                && isPath(widthCoordinate - 1, heightCoordinate)) {
            return false;
        }

        // Top Right
        else if (isPath(widthCoordinate + 1, heightCoordinate - 1)
                && isPath(widthCoordinate,heightCoordinate - 1)
                && isPath(widthCoordinate + 1, heightCoordinate)) {
            return false;
        }
        // Bottom Right
        else if (isPath(widthCoordinate + 1, heightCoordinate + 1)
                && isPath(widthCoordinate, heightCoordinate + 1)
                && isPath(widthCoordinate, heightCoordinate + 1)) {
            return false;
        }
        // Bottom Left
        else if (isPath(widthCoordinate - 1, heightCoordinate + 1)
                && isPath(widthCoordinate,heightCoordinate + 1)
                && isPath(widthCoordinate, heightCoordinate + 1)) {
            return false;
        }

        return true;
    }

    /**
     * Randomly remove wall in the map to create loop in the maze.
     */
    private void removeRandomWall() {
        Random intRandomizer = new Random();

        int widthCoordinate = -1;
        int heightCoordinate = -1;

        int removeWallCounter = intRandomizer.nextInt(
                (MAP_HEIGHT / 2) - (MAP_HEIGHT / 4)) + (MAP_HEIGHT / 4) + 3;
        int deadLockCounter = (MAP_HEIGHT * MAP_WIDTH) / 2;

        if (cellSurroundingChecker(2, 2)) {
            mapBoard[2][2].setCellObject(CellObject.PATH);
            removeWallCounter--;
        }

        while (removeWallCounter >= 0 && deadLockCounter > 0) {
            while (!(isWall(widthCoordinate, heightCoordinate))) {
                widthCoordinate = intRandomizer.nextInt(MAP_WIDTH) - 1;
                heightCoordinate = intRandomizer.nextInt(MAP_HEIGHT) - 1;

            }

            if (cellSurroundingChecker(widthCoordinate, heightCoordinate)) {
                mapBoard[heightCoordinate][widthCoordinate].setCellObject(CellObject.PATH);
                removeWallCounter--;
            }

            else {
                deadLockCounter--;
            }

            widthCoordinate = intRandomizer.nextInt(MAP_WIDTH) - 1;
            heightCoordinate = intRandomizer.nextInt(MAP_HEIGHT) - 1;
        }
    }

    /**
     * Recursive Function that uses Recursive Depth First Search Algorithm to
     * randomly generate map.
     * @param startPosition: The current position in the map.
     */
    private void generateMap(Position startPosition) {
        List<Position> newDirections = new ArrayList<>();
        newDirections.add(new Position(0,-1));
        newDirections.add(new Position(0,1));
        newDirections.add(new Position(1,0));
        newDirections.add(new Position(-1,0));
        Collections.shuffle(newDirections);

        for(Position direction: newDirections) {
            Position newPosition = new Position(startPosition.getX() + direction.getX(),
                    startPosition.getY() + direction.getY());
            if (isValidSpot(newPosition)) {
                mapBoard[newPosition.getY()][newPosition.getX()].setCellObject(CellObject.PATH);
                generateMap(newPosition);
            }
        }
    }
}
