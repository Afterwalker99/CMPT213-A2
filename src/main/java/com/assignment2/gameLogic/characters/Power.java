package com.assignment2.gameLogic.characters;

import com.assignment2.gameLogic.common.CellObject;
import com.assignment2.gameLogic.common.Position;
import com.assignment2.gameLogic.mapGrid.MapLiaison;
import java.util.Random;

/**
 * This Power Class holds the information about a power. The information
 * includes the current position of the power in the map. This class
 * supports accessing the power's current position and also support
 * the randomizing of the power's current position.
 *
 * @author  Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class Power {
    // Power's current position.
    private Position currentPosition;

    /**
     * Non-Default Constructor.
     * @param ourMap: The current map of the game.
     */
    public Power (MapLiaison ourMap){
        setRandomPosition(ourMap);
    }

    /**
     * @return The power's current position.
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param currentPosition Power's new current position to be set.
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Randomize the power's position on the map.
     * @param ourMap: The current map of the game.
     */
    public void setRandomPosition(MapLiaison ourMap) {
        if (currentPosition != null) {
            ourMap.setCellInformation(currentPosition, CellObject.HERO);
        }

        Random intRandomizer = new Random();
        int widthCoordinate = intRandomizer.nextInt(ourMap.getMapWidth()) - 1;
        int heightCoordinate = intRandomizer.nextInt(ourMap.getMapHeight()) - 1;

        while (!(ourMap.isMovableSpot(new Position(widthCoordinate, heightCoordinate)))) {
            widthCoordinate = intRandomizer.nextInt(ourMap.getMapWidth()) - 1;
            heightCoordinate = intRandomizer.nextInt(ourMap.getMapHeight()) - 1;
        }

        this.setCurrentPosition(new Position(widthCoordinate, heightCoordinate));
        ourMap.setCellInformation(currentPosition, CellObject.POWER);
    }
}
