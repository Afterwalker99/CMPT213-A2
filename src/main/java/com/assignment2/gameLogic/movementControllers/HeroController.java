package com.assignment2.gameLogic.movementControllers;

import com.assignment2.gameLogic.characters.Hero;
import com.assignment2.gameLogic.common.Direction;
import com.assignment2.gameLogic.common.Position;
import com.assignment2.gameLogic.mapGrid.MapLiaison;

/**
 * The Hero Controller is the subclass of Character Controller class
 * It provides the functionality to control a hero. The functionality
 * includes moving the hero based on user input and updating the hero's
 * position on the map.
 *
 * @author Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class HeroController extends CharacterController {
    /**
     * Non-Default Constructor.
     * @param character: Hero to be controlled by the controller.
     * @param parentGrid The map of the game.
     */
    public HeroController(Hero character, MapLiaison parentGrid) {
        super(character, parentGrid);
    }

    /**
     * Move the hero from current position based on the direction.
     * @param direction: The direction that the hero move from current position.
     * @return True if hero move successfully and vise versa.
     */
    public boolean performAction(Direction direction) {
        Position newPosition = super.getPosition(direction);

        if (parentGrid.isMovableSpot(newPosition)
                && (newPosition.getX() != -1 && newPosition.getY() != -1)) {
            character.setPreviousPosition(character.getCurrentPosition());
            character.setCurrentPosition(newPosition);
            updateMapGrid();
            parentGrid.revealSurroundingCell(newPosition.getX(),newPosition.getY());
            return true;
        }

        return false;
    }
}

