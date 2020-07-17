package com.assignment2.gameLogic.movementControllers;

import com.assignment2.gameLogic.common.Direction;
import com.assignment2.gameLogic.mapGrid.MapLiaison;
import com.assignment2.gameLogic.common.Position;
import com.assignment2.gameLogic.characters.Enemy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Enemy Controller is the subclass of Character Controller class
 * It provides the functionality to control an enemy. The functionality
 * includes moving the enemy pseudo-randomly and updating the enemy's
 * position on the map.
 *
 * @author Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class EnemyController extends CharacterController {
    // List that contains all 4 directions (Up, Down, Left, Right).
    private final List<Direction> directionList;

    /**
     * Non-Default Constructor.
     * @param character: Enemy to be controlled by the controller.
     * @param parentGrid: The map of the game.
     */
    public EnemyController(Enemy character, MapLiaison parentGrid) {
        super(character, parentGrid);
        directionList = new ArrayList<>();
        directionList.add(Direction.UP);
        directionList.add(Direction.DOWN);
        directionList.add(Direction.LEFT);
        directionList.add(Direction.RIGHT);
    }

    /**
     * Move the enemy pseudo-randomly by one step from the enemy's current position.
     */
    public void performAction() {
        Collections.shuffle(directionList);
        Position temporaryPosition;

        for (Direction direction : directionList) {
            temporaryPosition = super.getPosition(direction);

            if (parentGrid.isMovableSpot(temporaryPosition)
                    && !(temporaryPosition.isEqual(character.getPreviousPosition()))) {
                character.setPreviousPosition(character.getCurrentPosition());
                character.setCurrentPosition(temporaryPosition);
                updateMapGrid();
                return;
            }
        }

        temporaryPosition = character.getPreviousPosition();
        character.setPreviousPosition(character.getCurrentPosition());
        character.setCurrentPosition(temporaryPosition);
        updateMapGrid();
    }
}