/**
 * Class LevelTwo is a LevelInformation and defience the feateres of level 2 of the game
 */

import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import java.util.List;

public class LevelTwo implements LevelInformation {
    private String levelName;
    private int numberOfBals;
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite backround;
    private List<Block> blockList;

    /**
     * constractor function
     */
    public LevelTwo() {
        this.levelName = "Level 2 - Show Your Skills";
        this.numberOfBals = 2;
        initializeVelocities();
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        initializeBackround();
        initializeBlocks();
    }

    /**
     * The initial velocity of each ball
     */
    private void initializeVelocities() {
        this.velocityList = new LinkedList<Velocity>();
        for (int i = 0; i < this.numberOfBals; i++) {
            this.velocityList.add(new Velocity(2, 4));
        }
    }

    /**
     * The initial Backround
     */
    private void initializeBackround() {
        this.backround = new Block(0, 0, 800, 600, java.awt.Color.BLUE);
    }

    /**
     * the initial block formation
     */
    private void initializeBlocks() {
        this.blockList = new LinkedList<Block>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (i % 2 == 0) {
                    this.blockList.add(new Block(100 + (i * 110), 40 + (j * 30), 50, 30, java.awt.Color.WHITE));
                } else
                    this.blockList.add(new Block(100 + (i * 110), 40 + (j * 30), 50, 30, java.awt.Color.BLACK));
            }
        }
    }

    /**
     * get function
     *
     * @return the overall number of balls
     */
    public int numberOfBalls() {
        return this.numberOfBals;
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


