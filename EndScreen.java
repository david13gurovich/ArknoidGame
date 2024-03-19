/**
 * Class EndScreen is an Animation that defing the animation that runs when the game ends
 */

import biuoop.DrawSurface;
import biuoop.GUI;

public class EndScreen implements Animation{
    private int score;
    private boolean win;
    private biuoop.KeyboardSensor keyboard;
    private GUI gui;


    /**
     * construtor function
     * @param score is the total score the player earned in the entire game
     * @param win is a boolean  whethere the palyer won or lost
     * @param k is the sensor to the keyboard of the user
     * @param gui the window on wich we display all our animations
     */
    public EndScreen(int score,boolean win,biuoop.KeyboardSensor k, GUI gui)
    {
        this.keyboard =k;
        this.score =score;
        this.win = win;
        this.gui =gui;
    }


    /**
     * @Override function from Animation Interface
     * @param d is the surface that the animation runs on
     */
    public void doOneFrame(DrawSurface d)
    {
        Sprite screen = new Block(0,0,800,600, java.awt.Color.WHITE);
        screen.drawOn(d);
        d.setColor(java.awt.Color.BLACK);
        java.lang.String text;
        //different screens for a victory and loose
        if(win) {
            text= ("You Win! Your Score Is: " + this.score);
            d.drawText(0 ,(int) 600 / 2, text , 32);
            return;
        }
        else
        {
            text = ("Game Over! Your Score Is: " + this.score);
            d.drawText(0 ,(int) 600 / 2, text , 32);
            return;
        }

    }

    /**
     * @Override function from Animation Interface
     * @return boolean value if the user hit the button on his keyboard to exit the game
     */
    public boolean shouldStop()
    {
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY))
        {
            this.gui.close();
            return true;
        }
        return false;
    }
}