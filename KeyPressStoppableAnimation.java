/**
 * Class KeyPressStoppableAnimation is an Animation and is suposed to wrap every animation
 * that is running from or/and until the user hits a button
 */


import biuoop.DrawSurface;

public class KeyPressStoppableAnimation implements Animation {
    private  biuoop.KeyboardSensor keyboard;
    private String key;
    private Animation screen;
    private AnimationRunner runner;


    /**
     * constractor
     * @param sensor is the user's keyboard
     * @param key is the button to which the given animation is sensetive to
     * @param animation the given animation
     * @param runner the AnimationRunner given
     */
    public KeyPressStoppableAnimation(biuoop.KeyboardSensor sensor, String key,
                                      Animation animation, AnimationRunner runner)
    {
        this.keyboard = sensor;
        this.key = key;
        this.screen = animation;
        this.runner = runner;
    }

    /**
     * we do the frame that the given Animation would do
     * @param d is the surface that the animation runs on
     * @Override function from Animation Interface
     */
    public void doOneFrame(DrawSurface d)
    {
        while(!this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY))
        {
            this.runner.run(this.screen);
            this.screen.doOneFrame(d);
        }
        shouldStop();
    }

    /**
     * @return boolean value if we got to acondition for an animation to be stopped
     * @Override function from Animation Interface
     */
    public boolean shouldStop()
    {
        return this.screen.shouldStop();
    }
}