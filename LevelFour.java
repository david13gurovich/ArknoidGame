/**
 * Class LevelFour is a LevelInformation and defience the feateres of level 4 of the game
 */

import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import java.util.List;

public class LevelFour implements LevelInformation {
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
    public LevelFour() {
        this.levelName = "Level 4 - Expert";
        this.numberOfBals = 2;
        initializeVelocities();
        this.paddleSpeed = 20;
        this.paddleWidth = 110;
        initializeBackround();
        initializeBlocks();
    }


    /**
     * The initial velocity of each ball
     */
    private void initializeVelocities() {
        this.velocityList = new LinkedList<Velocity>();
        for (int i = 0; i < this.numberOfBals; i++) {
            this.velocityList.add(new Velocity(1 + i, 4));
        }
    }

    /**
     * The initial Backround
     */
    private void initializeBackround() {
        this.backround = new Block(0, 0, 800, 600, java.awt.Color.PINK);
    }


    /**
     * the initial block formation
     */
    private void initializeBlocks() {
        this.blockList = new LinkedList<Block>();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 6; j++) {
                if (i % 2 == 0) {
                    this.blockList.add(new Block(i * (800 / 25), 40 + (j * 40), 32, 20, java.awt.Color.YELLOW));
                } else
                    this.blockList.add(new Block(i * (800 / 25), 40 + (j * 40), 32, 20, java.awt.Color.BLUE));
            }
        }
    }

    /**
     * get function
     * @return the overall number of balls
     */
    public int numberOfBalls() {
        return this.numberOfBals;
    }

    /**
     * get function
     * @return a list of all balls velocities
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    /**
     * get function
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * get function
     * @return the paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * get function
     * @return the name of the level
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * get function
     * @return the backround
     */
    public Sprite getBackground() {
        return this.backround;
    }

    /**
     * get function
     * @return the list of all blocks
     */
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * get function
     * @return the number of blocks needed to be removed in order to win
     */
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }

}

