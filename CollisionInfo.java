/**
 * class CollisionInfo is concentrating all relevant information of any collision in the game
 */

public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * building function
     * @param p is the collision point
     * @param c is the collision object
     */
    public CollisionInfo(Point p, Collidable c)
    {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    // the point at which the collision occurs.
    public Point collisionPoint()
    {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject()
    {
        return this.collisionObject;
    }
}