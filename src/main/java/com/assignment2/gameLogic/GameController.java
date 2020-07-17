package com.assignment2.gameLogic;

import com.assignment2.gameLogic.characters.Enemy;
import com.assignment2.gameLogic.characters.Hero;
import com.assignment2.gameLogic.characters.Power;
import com.assignment2.gameLogic.common.CellObject;
import com.assignment2.gameLogic.common.Direction;
import com.assignment2.gameLogic.common.Position;
import com.assignment2.gameLogic.mapGrid.MapLiaison;
import com.assignment2.gameLogic.movementControllers.EnemyController;
import com.assignment2.gameLogic.movementControllers.HeroController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Game Controller class holds the information of the game, The information includes
 * the map of the game, the size of the map, controllers for the the enemy and hero and
 * the power entities in the game. It provides functionality to manage all of the entities
 * in the maze (Power, Enemies, Hero). It also provides with functionality to check
 * collision between entities in the map and manages the state of the game.
 *
 * @author Jason Yang Wen TAN
 * @author  Snehith Reddy GADE
 *
 * @version 1.0
 */
public class GameController {
    // Width Size of the Map.
    private final short MAP_WIDTH = 20;
    // Height Size of the Map.
    private final short MAP_HEIGHT = 15;
    // The map of the game.
    private final MapLiaison ourMap;
    // Concurrency Lock for initializing map.
    public Lock gameLock;
    // List of the enemies in the game.
    private List<EnemyController> enemyControllerList;
    // Hero in the game.
    private HeroController heroController;
    // Power in the game.
    private Power power;
    // Numbers of enemies left to kill to pass the level.
    private int enemiesLeft;

    /**
     * Default Constructor.
     */
    public GameController() {
        gameLock = new ReentrantLock();
        gameLock.lock();
        ourMap = new MapLiaison(MAP_WIDTH, MAP_HEIGHT);
        initController();
        gameLock.unlock();
    }

    /**
     * Initialize the power object, the controller for each enemies and hero in the map and the numbers
     * of enemies left to kill.
     */
    private void initController() {
        power = new Power(ourMap);
        this.enemyControllerList = new ArrayList<>();
        this.heroController = new HeroController(new Hero(), ourMap);
        enemyControllerList.add(new EnemyController(new Enemy(1, MAP_HEIGHT - 2), ourMap));
        enemyControllerList.add(new EnemyController(new Enemy(MAP_WIDTH - 2, 1), ourMap));
        enemyControllerList.add(new EnemyController(new Enemy(MAP_WIDTH - 2, MAP_HEIGHT - 2), ourMap));
        enemiesLeft = enemyControllerList.size();
    }

    /**
     * @return The map of the game.
     */
    public MapLiaison getOurMap() {
        return ourMap;
    }

    /**
     * @return The size of the Enemy Controller List.
     */
    public int getEnemyControllerListSize() {
        return enemyControllerList.size();
    }

    /**
     * @return The amount of power the hero holds.
     */
    public int getPowerCount() {
        return ((Hero)heroController.getCharacter()).getPowerCount();
    }

    /**
     * Moves all enemies in the map pseudo-randomly by one step.
     */
    public void moveEnemies() {
        for (EnemyController enemyController : enemyControllerList) {
            enemyController.performAction();

            if (enemyController.getCharacter().getPreviousPosition().isEqual(power.getCurrentPosition())) {
                ourMap.setCellInformation(power.getCurrentPosition(), CellObject.POWER);
            }
        }
    }

    /**
     * Move the hero from current position based on the direction.
     * @param direction: The direction that the hero move from current position.
     * @return True if hero move successfully and vise versa.
     */
    public boolean moveHero(Direction direction) {
        return heroController.performAction(direction);
    }

    /**
     * Checks whether the hero is in same cell on the map with the power. If they
     * are, the hero picks up the power and a new power is randomly spawned.
     */
    public void powerChecker() {
        Position heroPosition = heroController.getCharacter().getCurrentPosition();

        if (power.getCurrentPosition().isEqual(heroPosition)) {
            ((Hero)heroController.getCharacter()).incrementPower();
            power.setRandomPosition(ourMap);
        }
    }

    /**
     * Checks whether the hero is in same cell on the map with one or more enemies.
     * If they are, it checks whether the hero have enough power to kill the enemies.
     * @return True if hero was killed by the enemies and vise versa.
     */
    public boolean collisionChecker() {
        Position heroPosition = heroController.getCharacter().getCurrentPosition();
        int enemyCounter = 0;

        for (EnemyController enemyController : enemyControllerList) {
            Position currentEnemyPosition = enemyController.getCharacter().getCurrentPosition();

            if (heroPosition.isEqual(currentEnemyPosition)) {
                enemyCounter++;
            }
        }

        if (enemyCounter > 0) {
            if (getPowerCount() >= enemyCounter) {
                ((Hero)heroController.getCharacter()).decrementPower(enemyCounter);
                removeEnemy();
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove enemy killed by the hero.
     */
    public void removeEnemy() {
        Position heroPosition = heroController.getCharacter().getCurrentPosition();
        Iterator<EnemyController> enemyControllerIterator = enemyControllerList.iterator();

        while (enemyControllerIterator.hasNext()){
            EnemyController nextEnemyController = enemyControllerIterator.next();
            Position currentEnemyPosition = nextEnemyController.getCharacter().getCurrentPosition();

            if (heroPosition.isEqual(currentEnemyPosition)) {
                enemyControllerIterator.remove();
                enemiesLeft--;
            }
        }
    }

    /**
     * Checks whether the player finishes the game.
     * @return true if enemies left to be killed is zero and vise versa.
     */
    public boolean gameEndChecker() {
        return enemiesLeft == 0;
    }

    /**
     * Enables cheat mode in the game.
     */
    public void cheatsEnabled() {
        enemiesLeft = 1;
    }

    /**
     * Set hero object to dead hero object when the hero is slain by the enemies.
     */
    public void heroGameEndListener() {
        ourMap.setCellInformation(heroController.getCharacter().getCurrentPosition(), CellObject.DEAD_HERO);
    }
}