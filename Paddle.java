import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * class Paddle is responsible for the player's tool in the game
 */

public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private double leftBorder;
    private double rightBorder;
    private int speed;

    /**
     * building function
     * @param rectangle is the sizes and color of the paddle
     * @param keyboard is the keyboard sensor to detect the player's commands
     * @param left is the left border of the game frame
     * @param right is the right border of the game frame
     */
    public Paddle(Rectangle rectangle ,biuoop.KeyboardSensor keyboard, double left , double right,int speed)
    {
        this.rect = rectangle;
        this.keyboard =keyboard;
        this.leftBorder = left;
        this.rightBorder = right;
        this.speed = speed;
    }

    /**
     * building function
     * @param upperLeftX starting x location of the paddle
     * @param upperLeftY the y location of the paddle for the entire game
     * @param width the x size of the paddle
     * @param height the y size of the paddle
     * @param color the color of the paddle
     * @param keyboard is the keyboard sensor to detect the player's commands
     * @param left is the left border of the game frame
     * @param right is the right border of the game frame
     */
    public Paddle(double upperLeftX, double upperLeftY ,double width,double height,
                  java.awt.Color color, biuoop.KeyboardSensor keyboard, double left , double right,int speed)
    {
        Point p = new Point(upperLeftX,upperLeftY);
        this.rect = new Rectangle(p,width,height,color);
        this.keyboard =keyboard;
        this.leftBorder = left;
        this.rightBorder = right;
        this.speed = speed;
    }

    /**
     * this function is responsible for the paddle movement to the left
     * without it leaving the borders of the game
     */
    public void moveLeft()
    {
        double currentXLocationLeft = this.rect.getUpperLeft().getX();
        Rectangle newLocation;
        Point p;
        if(currentXLocationLeft - this.leftBorder > 0)
        {
            if(currentXLocationLeft - this.leftBorder <= speed)
            {
                p = new Point(this.leftBorder,this.rect.getUpperLeft().getY());
                newLocation = new Rectangle(p, this.rect.getWidth(),this.rect.getHeight(), this.rect.getColor());
                this.rect = newLocation;
            }
            else
            {
                p = new Point(currentXLocationLeft - speed,this.rect.getUpperLeft().getY());
                newLocation = new Rectangle(p, this.rect.getWidth(),this.rect.getHeight(), this.rect.getColor());
                this.rect = newLocation;
            }
        }
    }

    /**
     * this function is responsible for the paddle movement to the right
     * without it leaving the borders of the game
     */
    public void moveRight()
    {
        double currentXLocationRight = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        Rectangle newLocation;
        Point p;
        if(this.rightBorder - currentXLocationRight > 0)
        {
            if(this.rightBorder - currentXLocationRight <= speed)
            {
                p = new Point(this.rightBorder - this.rect.getWidth(),this.rect.getUpperLeft().getY());
                newLocation = new Rectangle(p, this.rect.getWidth(),this.rect.getHeight(), this.rect.getColor());
                this.rect = newLocation;
            }
            else
            {
                p = new Point(this.rect.getUpperLeft().getX() + speed , this.rect.getUpperLeft().getY());
                newLocation = new Rectangle(p, this.rect.getWidth(),this.rect.getHeight(), this.rect.getColor());
                this.rect = newLocation;
            }
        }
    }

    // Sprite Override function , checks for commands from the player
    public void timePassed()
    {
        if(this.keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY))
        {
            this.moveLeft();
        }
        if(this.keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY))
        {
            this.moveRight();
        }
    }

    // Sprite Override function , draws the paddle on the gui
    public void drawOn(DrawSurface d)
    {
        d.setColor(this.rect.getColor());
        d.fillRectangle((int)this.rect.getUpperLeft().getX(),
                (int)this.rect.getUpperLeft().getY(),(int)this.rect.getWidth(),(int)this.rect.getHeight());
    }

    public double getYValue()
    {
        return this.rect.getUpperLeft().getY();
    }

    // Add this paddle to the game. an Override Sprite function
    public void addToGame(GameLevel g)
    {
        g.addSprite(this);
    }

    // Collidable Override function, is aget function for it's rectangle (sizes and color of the paddle)
    public Rectangle getCollisionRectangle()
    {
        return this.rect;
    }

    // Collidable Override function, notifies the paddle it was hit by the ball,
    // and it determines the new velocity of the ball after the hit
    public Velocity hit(Point collisionPoint, Velocity currentVelocity,Ball hitter)
    {
        /**
         * maybe need to implement here the listeners in every hit
         */
        Velocity updatedVelocity;
        //vertical change
        if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 0.5 &&
                collisionPoint.getX() >= this.rect.getUpperLeft().getX() - 0.5 ||
                collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + this.rect.getWidth()) + 0.5 &&
                        collisionPoint.getX() >= (this.rect.getUpperLeft().getX() + this.rect.getWidth()) - 0.5) {
            updatedVelocity = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            return updatedVelocity;
        }
        //horizontal change
        if (collisionPoint.getY() <= this.rect.getUpperLeft().getY() + 0.5 &&
                collisionPoint.getY() >= this.rect.getUpperLeft().getY() - 0.5 ||
                collisionPoint.getY() <= (this.rect.getUpperLeft().getY() + this.rect.getHeight()) + 0.5 &&
                        collisionPoint.getY() >= (this.rect.getUpperLeft().getY() + this.rect.getHeight()) - 0.5) {
            //center of paddle hit (zone 3)
            if(collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 2) &&
                    (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 3)))
            {
                updatedVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
                return updatedVelocity;
            }
            //far left paddle hit (zone 5)
            if(collisionPoint.getX() >= this.rect.getUpperLeft().getX() &&
                    (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5)))
            {
                return currentVelocity.fromAngleAndSpeed(210,currentVelocity.getSpeed());
            }
            //middle left paddle hit (zone 4)
            if(collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5) &&
                    (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 2)))
            {
                return currentVelocity.fromAngleAndSpeed(240,currentVelocity.getSpeed());
            }
            //middle right paddle hit (zone 2)
            if(collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 3) &&
                    (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 4)))
            {
                return currentVelocity.fromAngleAndSpeed(300,currentVelocity.getSpeed());
            }
            //far right paddle hit (zone 1)
            if(collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 5 * 4) &&
                    (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + this.rect.getWidth()))
            {
                return currentVelocity.fromAngleAndSpeed(330,currentVelocity.getSpeed());
            }
        }
        return null;
    }
}