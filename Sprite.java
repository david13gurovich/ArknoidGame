import biuoop.DrawSurface;

/**
 * class Sprite is an interface that helps determine the behavior of any object that is visible on the gui
 */

public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
    //adss sprite to Game
    void addToGame(GameLevel g);
}