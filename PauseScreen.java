/**
 * Class PauseScreen is an Animation that defing the animation that runs when the user requested a pause
 */

import biuoop.GUI;
import biuoop.DrawSurface;

public class PauseScreen implements Animation {

    private biuoop.KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constractor
     * @param k is sensor to the user's keyboard
     */
    public PauseScreen(biuoop.KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * @Override function from Animation Interface
     * @param d is the surface that the animation runs on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY))
        {
            this.stop = true;
        }
    }

    /**
     * @Override function from Animation Interface
     * @return boolean value if the user hit the button on his keyboard to resume to the game
     */
    public boolean shouldStop()
    {
        return this.stop;
    }
}