/**
 * class Collidableis an interface that defines any object to be physical,
 * it means that a Collidable object could not be penatrated by any other colliadble
 */

public interface Collidable {
    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);

    public double getYValue();
}