/**
 * Class BallRemover is responsible for deleting each ball when needed
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    //builder function
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    // Blocks that are hit should be removed
    // from the game.
    public void hitEvent(Block deathBlock, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
        if(this.remainingBalls.getValue() == 0) {
            deathBlock.removeHitListener(this);
        }
    }
}