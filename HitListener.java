/**
 * class HitListener is an interface that defines object to activate the hitEvent function,
 * which transmits the data about the ball that hit and the block that had been hit to the game
 */

public interface HitListener {

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}