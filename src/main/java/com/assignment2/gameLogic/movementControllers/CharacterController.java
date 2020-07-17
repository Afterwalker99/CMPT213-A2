package com.assignment2.gameLogic.movementControllers;

import com.assignment2.gameLogic.common.CellObject;
import com.assignment2.gameLogic.common.Direction;
import com.assignment2.gameLogic.common.Position;
import com.assignment2.gameLogic.characters.Character;
import com.assignment2.gameLogic.characters.Enemy;
import com.assignment2.gameLogic.mapGrid.MapLiaison;

/**
 * The Character Controller is the superclass class that provides
 * functionality to control a character object. The functionality
 * includes managing the movement of the character and updating
 * the character's position on the map.
 *
 * @author Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public abstract class CharacterController {
    // Character to be controlled by the controller.
    protected Character character;
    // The map of the game.
    protected MapLiaison parentGrid;

    /**
     * Non-Default Constructor
     * @param character Character to be controlled by the controller.
     * @param parentGrid The map of the game.
     */
    public CharacterController(Character character, MapLiaison parentGrid) {
        this.character = character;
        this.parentGrid = parentGrid;
    }

    /**
     * Update the map of the game with the latest position of the character.
     */
    protected void updateMapGrid() {
        parentGrid.setCellInformation(
                character.getPreviousPosition(),
                character.getCurrentPosition(),
                (character instanceof Enemy) ? CellObject.ENEMY: CellObject.HERO);
    }

    /**
     * @return the character controlled by the character controller.
     */
    public Character getCharacter() { return character; }

    /**
     * @param direction: The specified direction (Up, Down, Left, Right).
     * @return the position of the specified direction from the character's current position.
     */
    protected Position getPosition(Direction direction) {
        return switch (direction) {
            case LEFT -> new Position(character.getCurrentPosition().getX() - 1,
                    character.getCurrentPosition().getY());
            case RIGHT -> new Position(character.getCurrentPosition().getX() + 1,
                    character.getCurrentPosition().getY());
            case UP -> new Position(character.getCurrentPosition().getX(),
                    character.getCurrentPosition().getY() - 1);
            case DOWN -> new Position(character.getCurrentPosition().getX(),
                    character.getCurrentPosition().getY() + 1);
        };
    }
}
