import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Class Ball creates and responsible for 'ball' objects that are
 * basicly a circle that has a center point and a radius, color, and velocity
 */
public  class Ball implements Sprite {

    Point center;
    int radius;
    java.awt.Color color;
    Velocity velocity;
    GameEnvironment game;

    /**
     * builder function
     * @param center is a point that will represent the center of a new ball
     * @param r is the value of the radius of the new ball
     * @param color is the color of the new ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment newGame){
        this.center = center;
        this.radius = r;
        this.color = color;
        this.game = newGame;
    }

    /**
     * builder function
     * @param x is the x value of a point that will represent the center of a new ball
     * @param y is the y value of a point that will represent the center of a new ball
     * @param r is the value of the radius of the new ball
     * @param color is the color of the new ball
     */
    public Ball(double x,double y, int r, java.awt.Color color , GameEnvironment newGame){
        this.center = new Point(x,y);
        this.radius = r;
        this.color = color;
        this.game = newGame;
    }

    /**
     * get function to get the x value of the center of the ball
     * @return the x value of the center
     */
    public int getX(){
        return (int)this.center.getX();
    }

    /**
     * get function to get the y value of the center of the ball
     * @return the y value of the center
     */
    public int getY(){
        return (int)this.center.getY();
    }

    /**
     * get function to get the size value of the ball
     * @return size of the ball using known formula Pi * radius * radius
     */
    public int getSize(){
        return (int)(this.radius * this.radius * Math.PI);
    }

    /**
     * gets the color of the ball
     * @return ball's color
     */
    public java.awt.Color getColor(){
        return this.color;
    }

    /**
     * gets the velocity of the ball
     * @return ball's velocity
     */
    public Velocity getVelocity(){
        return this.velocity;
    }

    /**
     * sets a new velocity for a ball
     * @param v is the new velocity that is going to be set to the ball
     */
    public void setVelocity(Velocity v){
        this.velocity = v;
    }

    /**
     * sets a new velocity for a ball
     * @param dx is the value of horizontal velocity that is going to be set to the ball
     * @param dy is the value of vertical velocity that is going to be set to the ball
     */
    public void setVelocity(double dx, double dy){
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this function is responsible for showing the ball on the screen (graphics of the ball)
     * @param surface is the given screen on which the ball will be showed on
     */
    public void drawOn(DrawSurface surface){
        surface.setColor(this.color);
        surface.fillCircle(this.getX(),this.getY(),this.radius);
    }

    /**
     * this function calculates the ball's trajectory by finding current point of the
     * center and the end point where the ball will hit the frame of the gui if there will no obsticle in the way
     * @param sizeXTop upper left x value of the frame
     * @param sizeYTop upper left y value of the frame
     * @param sizeXBot lower right x value of the frame
     * @param sizeYBot lower right y value of the frame
     * @return ball's trajectory represented by the line
     */
    public Line getTrajectory(int sizeXTop, int sizeYTop, int sizeXBot, int sizeYBot)
    {
        double dx, dy;
        Point start,end , imaginary;
        Line bot, top , left ,right,auxilary, correct;
        dx = this.velocity.getDx();
        dy = this.velocity.getDy();
        //building lines that represent the frame lines
        bot = new Line(sizeXTop,sizeYTop + sizeYBot, sizeXTop +sizeXBot, sizeYTop + sizeYBot);
        top = new Line(sizeXTop,sizeYTop, sizeXTop +sizeXBot, sizeYTop);
        left = new Line(sizeXTop,sizeYTop, sizeXTop, sizeYTop + sizeYBot);
        right = new Line(sizeXTop + sizeXBot,sizeYTop, sizeXTop +sizeXBot, sizeYTop + sizeYBot);
        start = this.center;
        //ball's trajectory , when the end point is defenetly outside the frame
        imaginary = new Point(this.center.getX() + (900 * dx), this.center.getY() + (900 * dy));
        auxilary = new Line(start,imaginary);
        //only one possible intersection with frame at any trajectory
        if(auxilary.findingIntersection(bot) != null)
        {
            end = auxilary.findingIntersection(bot);
        }
        else if(auxilary.findingIntersection(top) != null)
        {
            end = auxilary.findingIntersection(top);
        }
        else if(auxilary.findingIntersection(right) != null)
        {
            end = auxilary.findingIntersection(right);
        }
        else
        {
            end = auxilary.findingIntersection(left);
        }
        correct = new Line(start,end);
        return correct;
    }

    /**
     * @Override Sprite function
     * constantly making moves
     */
    public void timePassed()
    {
        this.moveOneStep(800,600);
    }

    /**
     * @Override Sprite function
     * adds this ball to the list of sprites in the game
     */
    public void addToGame(GameLevel g)
    {
        g.addSprite(this);
    }

    /**
     * this function is responsible for moving the ball by the velocity it has, one step only in that direction
     * @param sizeX is the value of the right border of the given window in which the ball will bounce
     * @param sizeY is the value of the bottom border of the given window in which the ball will bounce
     */
    public void moveOneStep(int sizeX, int sizeY) {
        int x ,y;
        Velocity v;
        Point closestCollision, nextPoint;
        Collidable bounceOff;
        x = (int)this.center.getX();
        y = (int)this.center.getY();
        //if there is an obsticle in the way of the ball
        if(this.game.getClosestCollision(this.getTrajectory(0,0,sizeX,sizeY)) != null)
        {
            closestCollision = (this.game.getClosestCollision(this.getTrajectory(0,
                    0, sizeX, sizeY))).collisionPoint();
            bounceOff = (this.game.getClosestCollision(this.getTrajectory(0,
                    0, sizeX, sizeY))).collisionObject();
            //closest collision with an obsticle
            if(x + this.velocity.getDx() >= 0 && x + this.velocity.getDx() <= sizeX &&
                    y + this.velocity.getDy() >= 0 && y + this.velocity.getDy() <= sizeY) {
                nextPoint = new Point(x + this.velocity.getDx(), y + this.velocity.getDy());
                if (bounceOff.getCollisionRectangle().containPoint(nextPoint)) {
                    v = bounceOff.hit(closestCollision, this.getVelocity(),this);
                    if (v != null) {
                        this.setVelocity(v);
                    }
                }
                //to prevent the ball to get inside the obsticle
                nextPoint = new Point(x + this.velocity.getDx() * 1.5, y + this.velocity.getDy() * 1.5);
                if (bounceOff.getCollisionRectangle().containPoint(nextPoint)) {
                    v = bounceOff.hit(closestCollision, this.getVelocity(),this);
                    if (v != null) {
                        this.setVelocity(v);
                    }
                }
            }
        }
        // top border
        if(y + this.velocity.getDy() <= 0 && this.velocity.getDy() < 0 ||
                y + this.velocity.getDy() * 1.5 <= 0 && this.velocity.getDy() < 0)
        {
            this.setVelocity(this.velocity.getDx() , this.velocity.getDy() * -1);
        }

        //bottom border
        if(y + this.velocity.getDy() >= sizeY && this.velocity.getDy() > 0 ||
                y + this.velocity.getDy() * 1.5 >= sizeY && this.velocity.getDy() > 0)
        {
            this.setVelocity( this.velocity.getDx() , this.velocity.getDy() * -1);
        }

        //right side
        if(x + this.velocity.getDx() >= sizeX && this.velocity.getDx() > 0 ||
                x + this.velocity.getDx() * 1.5 >= sizeX && this.velocity.getDx() > 0)
        {
            this.setVelocity(this.velocity.getDx() * -1 , this.velocity.getDy());
        }

        //left side
        if(x + this.velocity.getDx() <= 0 && this.velocity.getDx() < 0 ||
                x + this.velocity.getDx() * 1.5 <= 0 && this.velocity.getDx() < 0)
        {
            this.setVelocity(this.velocity.getDx() * -1 , this.velocity.getDy());
        }

        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * same function s before only for a smaller frame then
     * the given window (a frame to the ball to bounce inside the given window)
     * @param sizeXTop is the value of the left border of the given frame in which the ball will bounce
     * @param sizeYTop is the value of the top border of the given frame in which the ball will bounce
     * @param sizeXBot is the value of the right border of the given frame in which the ball will bounce
     * @param sizeYBot is the value of the bottom border of the given frame in which the ball will bounce
     */
    public void moveOneStep(int sizeXTop, int sizeYTop, int sizeXBot, int sizeYBot) {
        int x ,y;
        Velocity v;
        Point closestCollision , nextPoint;
        Collidable bounceOff;
        x = (int)this.center.getX();
        y = (int)this.center.getY();
        //if there is an obsticle in the way of the ball
        if(this.game.getClosestCollision(this.getTrajectory(sizeXTop,sizeYTop,sizeXBot,sizeYBot)) != null)
        {
            closestCollision =
                    (this.game.getClosestCollision(this.getTrajectory(sizeXTop,
                            sizeYTop, sizeXBot, sizeYBot))).collisionPoint();
            bounceOff =
                    (this.game.getClosestCollision(this.getTrajectory(sizeXTop,
                            sizeYTop, sizeXBot, sizeYBot))).collisionObject();

            //closest collision with an obsticle
            if(x + this.velocity.getDx() >= sizeXTop && x + this.velocity.getDx() <= sizeXBot &&
                    y + this.velocity.getDy() >= sizeYTop && y + this.velocity.getDy() <= sizeYBot) {
                nextPoint = new Point(x + this.velocity.getDx(), y + this.velocity.getDy());
                if (bounceOff.getCollisionRectangle().containPoint(nextPoint)) {
                    v = bounceOff.hit(closestCollision, this.getVelocity(),this);
                    if (v != null) {
                        this.setVelocity(v);
                    }
                }
                //to prevent the ball to get inside the obsticle
                nextPoint = new Point(x + this.velocity.getDx() * 1.2, y + this.velocity.getDy() * 1.2);
                if (bounceOff.getCollisionRectangle().containPoint(nextPoint)) {
                    v = bounceOff.hit(closestCollision, this.getVelocity(),this);
                    if (v != null) {
                        this.setVelocity(v);
                    }
                }
            }
        }
        // top border
        if(y - this.radius <= sizeYTop && this.velocity.getDy() < 0 )
        {
            this.setVelocity(this.velocity.getDx() , this.velocity.getDy() * -1);
        }

        //bottom border
        if(y + this.radius >= sizeYBot && this.velocity.getDy() > 0 )
        {
            this.setVelocity( this.velocity.getDx() , this.velocity.getDy() * -1);
        }

        //right side
        if(x + this.radius >= sizeXBot && this.velocity.getDx() > 0 )
        {
            this.setVelocity(this.velocity.getDx() * -1 , this.velocity.getDy());
        }

        //left side
        if(x - this.radius <= sizeXTop && this.velocity.getDx() < 0 )
        {
            this.setVelocity(this.velocity.getDx() * -1 , this.velocity.getDy());
        }

        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * @param game is the game curruntly active
     * the function deletes the ball from the game when called
     */
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);
    }

}