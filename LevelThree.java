/**
 * Class LevelThree is a LevelInformation and defience the feateres of level 3 of the game
 */

import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import java.util.List;

public class LevelThree implements LevelInformation {
    private String levelName;
    private int numberOfBalls;
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite backround;
    private List<Block> blockList;

    /**
     * constractor function
     */
    public LevelThree() {
        this.levelName = "Level 3 - Direct Hit";
        this.numberOfBalls = 1;
        initializeVelocities();
        this.paddleSpeed = 10;
        this.paddleWidth = 80;
        initializeBackround();
        initializeBlocks();
    }

    /**
     * The initial velocity of each ball
     */
    private void initializeVelocities() {
        this.velocityList = new LinkedList<Velocity>();
        this.velocityList.add(new Velocity(3, 5));
    }

    /**
     * The initial Backround
     */
    private void initializeBackround() {
        this.backround = new Block(0, 0, 800, 600, java.awt.Color.GRAY);
    }

    /**
     * the initial block formation
     */
    private void initializeBlocks() {
        this.blockList = new LinkedList<Block>();
        this.blockList.add(new Block(390, 60, 20, 20, java.awt.Color.RED));
    }

    /**
     * get function
     *
     * @return the overall number of balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * get function
     *
     * @return a list of all balls velocities
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    /**
     * get function
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * get function
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * get function
     *
     * @return the name of the level
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * get function
     *
     * @return the backround
     */
    public Sprite getBackground() {
        return this.backround;
    }

    /**
     * get function
     *
     * @return the list of all blocks
     */
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * get function
     *
     * @return the number of blocks needed to be removed in order to win
     */
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }
}

