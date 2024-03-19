/**
 * Class AnimationRunner receives an animation and runs it according to the
 * data in the animation given as argument
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * constractor function
     * @param newGui is a given GUI to display on it the animation
     */
    public AnimationRunner(GUI newGui) {
        this.gui = newGui;
        this.framesPerSecond = 60;
    }


    /**
     * this function runs the annimation according to each animation's code in the Animation interface functions
     * and it is responsible for the time per frame roll
     * @param animation is the given animation that wenarengonna run here
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            //time per frame roll
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


    /**
     * this function runs the annimation according to each animation's code in the Animation interface functions
     * and it is responsible for the time per frame roll,
     * this particular function is designed to run an animation in one frame, like the countdown screen
     * @param animation is the given animation that wenarengonna run here
     * @param ratio is th ration between number of seconds to numbers to countdown in
     */
    public void run(Animation animation, double ratio) {
        int count =0;
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        Sleeper sleeper = new Sleeper();
        double startTime = System.currentTimeMillis() - 1000 * ratio; // timing
        while (!animation.shouldStop()) {
            DrawSurface d = gui.getDrawSurface();
            double usedTime = System.currentTimeMillis() - startTime;
            if(usedTime >= 1000 * ratio)
            {
                count++;
                startTime += (1000*ratio);
                animation.doOneFrame(d);
                gui.show(d);
            }
            double milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor((long)milliSecondLeftToSleep);
            }
        }
    }
}