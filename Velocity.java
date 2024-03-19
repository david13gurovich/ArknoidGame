/**
 * class velocity is responsible to the direction and the speed each ball will have
 */
public class Velocity {

    double dx;
    double dy;

    /**
     * build function
     * @param dx - horizontal velocity
     * @param dy - vertical velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * build function
     * @param angle is the desired angle to be for the ball's velocity
     * @param speed is the desired speed to be for the ball's velocity
     * @return the new velocity that matches the speed and angle that were given
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * this function helps to find the ball's next location
     * @param p is the point where the ball currently is located
     * @return point where the ball will move next according to it's velocity
     */
    public Point applyToPoint(Point p) {
        double x, y;
        x = p.getX();
        y = p.getY();
        Point newPoint = new Point(x + this.dx, y + this.dy);
        return newPoint;
    }

    /**
     * gets the vertical velocity
     * @return the vertical velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * gets the horizontal velocity
     * @return the horizontal velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * calculates the current speed of a ball
     * @return current speed
     */
    public double getSpeed()
    {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}