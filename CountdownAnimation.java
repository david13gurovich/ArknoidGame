/**
 * Class CountdownAnimation is an Animation that defing the animation that runs before a level starts
 * it shows the first frame of the level that
 * is gonna start and countdown to zero from the number and the time given by the programmer
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Counter tracker;

    /**
     * constractor function
     * @param numOfSeconds to display the countdown
     * @param countFrom is the first number to start the countdown with
     * @param gameScreen the first frame of the coming level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen)
    {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.tracker = new Counter((int)countFrom);
    }


    /**
     * @Override function from Animation Interface
     * @param d is the surface that the animation runs on
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        String s = String.valueOf(this.tracker.getValue());
        d.drawText(d.getWidth() / 2, d.getHeight() / 10, s, 20);
        this.tracker.decrease(1);
    }


    /**
     * @Override function from Animation Interface
     * @return boolean value the time passed for the animation to run
     */
    public boolean shouldStop() {
        if(tracker.getValue() < 0)
            return true;
        return false;
    }


    /**
     * get function
     * @return the ration time/number to countdown
     */
    public double getRatio()
    {
        return this.numOfSeconds / this.countFrom;
    }

}