/**
 * Class ScoreTrackingListener is a HitLIstener that is responsible to update the user's score in the
 * current game, every block that is being hit, it's a 5 points to the user
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * builder function
     * @param scoreCounter is the Counter that keeps score of the entire game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * @Override HitListener function, each listener should do something unique, every time this functin is called
     * @param beingHit is the block that was hit
     * @param hitter is the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}