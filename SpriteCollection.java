import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * class SpriteCollection is responsible for storing all sprites in the game
 * and concentrating the "must have" functions of Sprite interface by calling each Sprite with the same function
 */

public class SpriteCollection {

    private List<Sprite> allSprites;

    //building function
    public SpriteCollection()
    {
        this.allSprites = new LinkedList<Sprite>();
    }

    // add the given sprite to the list of all sprites in the game.
    public void addSprite(Sprite s)
    {
        this.allSprites.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed()
    {
        List<Sprite> collidablesCopy = new LinkedList<Sprite>(this.allSprites);
        for (Sprite sprite: collidablesCopy)
        {
            sprite.timePassed();
        }
    }

    /**
     * @param s is the sprite that's we are removing from the SpriteCollection
     */
    public void removeSprite(Sprite s){
        this.allSprites.remove(s);
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d)
    {
        List<Sprite> collidablesCopy = new LinkedList<Sprite>(this.allSprites);
        for (Sprite sprite: collidablesCopy)
        {
            sprite.drawOn(d);
        }
    }
}