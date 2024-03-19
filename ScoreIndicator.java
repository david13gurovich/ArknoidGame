import biuoop.DrawSurface;
import java.awt.Color;


/**
 * Class ScoreIndicator is responsible for keeping score of the game and displaying it
 */
public class ScoreIndicator implements Sprite {

    private int score;
    private java.awt.Color color;
    private int width;
    private int height;


    /**
     * builder function
     * @param guiWidth is the width of the window, which helps to figure out where the middle of the screen is
     * @param heightOfScore is the level on which we should display the score
     * @param score is the current score in the game
     * @param color is the color the score will be displayed in
     */
    public ScoreIndicator(int guiWidth, int heightOfScore,int score ,java.awt.Color color)
    {
        this.width = guiWidth;
        this.height = heightOfScore;
        this.color = color;
        this.score = score;
    }


    /**
     * @Override Sprite function, all sprites should have this function : to display the object on the screen
     * @param d is the given screen on which the ball will be showed on
     */
    public void drawOn(DrawSurface d)
    {
        d.setColor(this.color);
        java.lang.String text= ("Score: " + this.score);
        d.drawText(this.width/2 -50,this.height,text , 15);
    }


    /**
     * @param newScore is the current score that we need to update in the ScoreIndicator of the game
     */
    public void updateScore(int newScore)
    {
        this.score = newScore;
    }


    /**
     * @Override Sprite function, i found no use for it
     */
    public void timePassed()
    {

    }

    /**
     * @param g is the game we add to the current ScoreIndicator
     */
    public void addToGame(GameLevel g)
    {
        g.addSprite(this);
    }

    /**
     * get function
     * @return the current score
     */
    public int getScore()
    {
        return this.score;
    }




}