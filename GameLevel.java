import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.util.Random;

/**
 * class GameLevel is an Animation concentrating all relevant information for the Level
 * to be functional and initializing it
 */
public class GameLevel implements Animation {

    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private HitListener scoreHL;
    private ScoreIndicator sIndicator;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    final int maxXValueOfGui = 800;
    final int maxYValueOfGui = 600;

    /**
     * constractor
     *
     * @param level the information needed about the level to display it as the programmer whished
     * @param score a score to start the level with
     */
    public GameLevel(LevelInformation level, int score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = new GUI("arknoid - game", maxXValueOfGui, maxYValueOfGui);
        this.remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(level.numberOfBalls());
        this.score = new Counter(score);
        this.scoreHL = new ScoreTrackingListener(this.score);
        this.sIndicator = new ScoreIndicator(maxXValueOfGui,
                (int) maxYValueOfGui / 30, score, java.awt.Color.BLACK);
        this.runner = new AnimationRunner(this.gui);
        this.running = false;
        this.keyboard = this.gui.getKeyboardSensor();
        this.level = level;
    }

    //auxiliry function to all collidables in the game (adds a Collidable to a list
    // where all game's collidables are being stored)
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    //auxiliry function to all sprites in the game to all sprites in the game (adds a Sprite to a list
    // where all game's sprites are being stored)
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * @param c is the Collidable that is going to be removed from the game
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }


    /**
     * @param s is the Sprite that is going to be removed from the game
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * @param eliminatedYValue is the y value that is going to be checked if any block still exists on that level,
     *                         if not than the playes gets 100 points
     */
    public void checkLevel(double eliminatedYValue) {
        boolean stillExisistingLevel = false;
        for (Collidable eachBlock : this.environment.getCollidables()) {
            if (eachBlock.getYValue() == eliminatedYValue) {
                stillExisistingLevel = true;
            }
        }
        if (stillExisistingLevel == false) {
            this.score.increase(100);
        }
    }


    /**
     * @return boolean value if we got to acondition for a level to be ended (user won or lost)
     * @Override function from Animation Interface
     */
    public boolean shouldStop() {
        if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        return !this.running;
    }


    /**
     * printing the name of the level on top of the screen
     *
     * @param d the surface on which we display the name
     */
    public void printLevelName(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        java.lang.String text = ("Level Name: " + this.level.levelName());
        d.drawText(maxYValueOfGui - 100, (int) maxYValueOfGui / 30, text, 15);
    }


    /**
     * @param d is the surface that the animation runs on
     * @Override function from Animation Interface
     */
    public void doOneFrame(DrawSurface d) {
        this.sIndicator.updateScore(this.score.getValue());
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        printLevelName(d);
        //a pause screen display
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Sprite backround = this.level.getBackground();
        addSprite(backround);
        Paddle paddle = new Paddle(350, 580, this.level.paddleWidth(), 20, java.awt.Color.BLACK,
                this.keyboard, 0, 800, this.level.paddleSpeed());
        paddle.addToGame(this);
        addSprite(sIndicator);
        this.addCollidable(paddle);
        Block deathBlock = new Block(0, 597, 800, 5, java.awt.Color.BLACK);
        deathBlock.addToGame(this);
        this.addCollidable(deathBlock);
        HitListener balRemover = new BallRemover(this, this.remainingBalls);
        deathBlock.addHitListener(balRemover);
        for (int k = 0; k < this.level.numberOfBalls(); k++) {
            Ball aNewBall = new Ball((k + 1) * 100, 300 + k * 30, 4, java.awt.Color.RED, this.environment);
            aNewBall.setVelocity(this.level.initialBallVelocities().get(k));
            addSprite(aNewBall);
        }
        //pattern
        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block newBlock = this.level.blocks().get(i);
            HitListener blkRemover = new BlockRemover(this, this.remainingBlocks);
            newBlock.addHitListener(blkRemover);
            newBlock.addHitListener(this.scoreHL);
            newBlock.addToGame(this);
            addCollidable(newBlock);
        }
    }


    // Run the game -- start the animation loop.
    public int run() {
        // use our runner to run the current animation -- which is one turn of
        // the game.
        CountdownAnimation cda = new CountdownAnimation(2, 3, this.sprites);
        this.runner.run(cda, cda.getRatio());
        this.running = true;
        this.runner.run(this);
        return sIndicator.getScore();
    }

    /**
     * auxiliry function that is called when we need to figuere
     * if the player is lost and the level shuold be stopped
     *
     * @return boolean value if there are no more balls on the screen - the player lost
     */
    public boolean areThereMoreBalls() {
        if (remainingBalls.getValue() == 0)
            return false;
        return true;
    }

    /**
     * auxiliry function that is called when we need to figuere
     * if the player is won and the level shuold be stopped
     *
     * @return boolean value if there are no more blocks on the screen - the player won
     */
    public boolean areThereMoreBLocks() {
        if (remainingBlocks.getValue() == 0)
            return false;
        return true;
    }

    /**
     * get function
     *
     * @return the current AnimationRunner
     */
    public AnimationRunner getAnimationRunner() {
        return runner;
    }

    /**
     * get function
     *
     * @return the current sensor to the user's keyboard
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * get function
     *
     * @return the current GUI
     */
    public GUI getGui() {
        return this.gui;
    }
}