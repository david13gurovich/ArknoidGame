import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * class Block is responsible to make an object a physical obsticle for a ball
 */
public class Block implements Collidable , Sprite , HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;

    /**
     * building function
     * @param rect is the shape and location given to a block
     */
    public Block(Rectangle rect)
    {
        this.hitListeners = new LinkedList<HitListener>();
        this.rect = rect;
    }

    /**
     * building function
     * @param upperleft is the location of the block
     * @param width is the x size of the block
     * @param height is the y size of the block
     * @param color is the color given to the block
     */
    public Block(Point upperleft,double width,double height, java.awt.Color color )
    {
        this.hitListeners = new LinkedList<HitListener>();
        this.rect = new Rectangle(upperleft,width,height,color);
    }

    /**
     * building function
     * @param x is the x location of the block
     * @param y is the y location of the block
     * @param width is the x size of the block
     * @param height is the y size of the block
     * @param color is the color given to the block
     */
    public Block(double x, double y,double width,double height, java.awt.Color color)
    {
        this.hitListeners = new LinkedList<HitListener>();
        Point p = new Point(x,y);
        this.rect = new Rectangle(p,width,height,color);
    }

    //get function for the rectangle of the block
    public Rectangle getCollisionRectangle()
    {
        return this.rect;
    }


    /**
     * collidable override function
     * it notifies the block that a ball hit it, and dermines the new velocity for the ball after the hit
     * @param collisionPoint is the calculated collision point between the ball and the block
     * @param currentVelocity is the ball's velocity
     * @return the new velocity for the ball after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter)
    {
        Velocity updatedVelocity;
        double inpersion9 = 0.9;
        double inpersion5 =0.5;
        Point upperLeft,upperRight,lowerLeft,lowerRight;
        //if the ball hits a corner of the block with the inpresition of 1 pixel referring the calculation
        upperLeft = this.rect.getUpperLeft();
        upperRight = new Point(this.rect.getUpperLeft().getX() +
                this.rect.getWidth(), this.rect.getUpperLeft().getY());
        lowerLeft = new Point(this.rect.getUpperLeft().getX(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        lowerRight = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        if(collisionPoint.getX() <= upperLeft.getX() + inpersion9 &&
                collisionPoint.getX() >= upperLeft.getX() - inpersion9 &&
                collisionPoint.getY() <= upperLeft.getY() + inpersion9 &&
                collisionPoint.getY() >= upperLeft.getY() - inpersion9 ||
                collisionPoint.getX() <= upperRight.getX() + inpersion9 &&
                        collisionPoint.getX() >= upperRight.getX() - inpersion9 &&
                        collisionPoint.getY() <= upperRight.getY() + inpersion9 &&
                        collisionPoint.getY() >= upperRight.getY() - inpersion9 ||
                collisionPoint.getX() <= lowerLeft.getX() + inpersion9 &&
                        collisionPoint.getX() >= lowerLeft.getX() - inpersion9 &&
                        collisionPoint.getY() <= lowerLeft.getY() + inpersion9 &&
                        collisionPoint.getY() >= lowerLeft.getY() - inpersion9 ||
                collisionPoint.getX() <= lowerRight.getX() + inpersion9 &&
                        collisionPoint.getX() >= lowerRight.getX() - inpersion9 &&
                        collisionPoint.getY() <= lowerRight.getY() + inpersion9 &&
                        collisionPoint.getY() >= lowerRight.getY() - inpersion9)
        {
            updatedVelocity = new Velocity(currentVelocity.getDx() * -1,currentVelocity.getDy() * -1);
            notifyHit(hitter);
            return updatedVelocity;
        }
        //horizontal change with the inpresition of 1 pixel referring the calculation
        if(collisionPoint.getX() <= this.rect.getUpperLeft().getX() + inpersion5 &&
                collisionPoint.getX() >= this.rect.getUpperLeft().getX() - inpersion5 ||
                collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + this.rect.getWidth()) + inpersion5 &&
                        collisionPoint.getX() >= (this.rect.getUpperLeft().getX() + this.rect.getWidth()) - inpersion5)
        {
            updatedVelocity = new Velocity(currentVelocity.getDx() * -1,currentVelocity.getDy());
            notifyHit(hitter);
            return updatedVelocity;
        }
        //vertical change with the inpresition of 1.8 pixel referring the calculation
        if(collisionPoint.getY() <= this.rect.getUpperLeft().getY() + inpersion9 &&
                collisionPoint.getY() >= this.rect.getUpperLeft().getY() - inpersion5 ||
                collisionPoint.getY() <= (this.rect.getUpperLeft().getY() + this.rect.getHeight()) + inpersion9 &&
                        collisionPoint.getY() >= (this.rect.getUpperLeft().getY() + this.rect.getHeight()) - inpersion9)
        {
            updatedVelocity = new Velocity(currentVelocity.getDx(),currentVelocity.getDy()  * -1);
            notifyHit(hitter);
            return updatedVelocity;
        }
        return null;
    }


    /**
     * sprite Override function that draws the block on the gui by it's parameters (it's rectangle)
     * @param surface is the tool that draws the block on the gui
     */
    public void drawOn(DrawSurface surface){
        surface.setColor(this.rect.getColor());
        surface.fillRectangle((int)this.rect.getUpperLeft().getX(),
                (int)this.rect.getUpperLeft().getY(),(int)this.rect.getWidth(),(int)this.rect.getHeight());
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle((int)this.rect.getUpperLeft().getX(),
                (int)this.rect.getUpperLeft().getY(),(int)this.rect.getWidth(),(int)this.rect.getHeight());
    }


    /**
     * function that iterates over all HitListeners each block has and informs that a hit accured
     * by calling a function from HitListener interface "hitEvent"
     * @param hitter is the ball that hit the current block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new LinkedList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    /**
     * @Override HitNotifier function, adds a hitListener to the current block's list of HitListeners
     * @param hl is the HitListener we are adding to the list
     */
    public void addHitListener(HitListener hl)
    {
        this.hitListeners.add(hl);
    }


    /**
     * @Override HitNotifier function, removes a hitListener from the block's list of HitListeners
     * @param hl is the HitListener we are removing from the list
     */
    public void removeHitListener(HitListener hl)
    {
        this.hitListeners.remove(hl);
    }


    /**
     * @return the block's list of HitLIsteners
     */
    public List<HitListener> getHitListeners()
    {
        return this.hitListeners;
    }


    /**
     * @return the y value of each block
     * this function used to determine if all blocks from each level are gone,
     * which adds 100 points to the player
     */
    public double getYValue()
    {
         return this.rect.getUpperLeft().getY();
    }

    /**
     * sprite Override function
     * does nothing so far...
     */
    public void timePassed()
    {

    }

    /**
     * sprite Override function adds the block to list of all sprites in the game
     * @param g is where the list of all sprites is located
     */
    public void addToGame(GameLevel g)
    {
        g.addSprite(this);
    }


    /**
     * removing current block from the ongoing game
     * @param game the game from which we need to remove the block
     */
    public void removeFromGame(GameLevel game){
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}