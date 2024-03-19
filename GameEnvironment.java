import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class GameEnvironment is responsible for storing all Collidables in the game
 * and calculate the collision point between a ball and any collidable if it's about to happen
 */

public class GameEnvironment {

    private List<Collidable> collidables;


    /**
     * building function
     */
    public GameEnvironment()
    {
        this.collidables = new LinkedList<Collidable>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c)
    {
        this.collidables.add(c);
    }

    //removes collidable from the environment
    public void removeCollidable(Collidable c)
    {
        this.collidables.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory)
    {
        int count = 0;
        int index =0;
        double distance = 1200; //bigger than gui size
        boolean found = false;
        Point closestCollision;
        List<CollisionInfo> allCollisionPointsAndCollidable = new LinkedList<>();
        List<Collidable> collidablesCopy = new LinkedList<Collidable>(this.collidables);
        for(Collidable c : collidablesCopy)
        {
            //collects all collidables that are in the balls trajectory
            if(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                CollisionInfo info = new
                        CollisionInfo(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()), c);
                allCollisionPointsAndCollidable.add(info);
            }
        }
        if(allCollisionPointsAndCollidable.isEmpty())
            return null;
        for(CollisionInfo currInfo : allCollisionPointsAndCollidable)
        {

            //finding the closest collision
            if(currInfo.collisionPoint().distance(trajectory.start()) < distance)
            {
                distance = currInfo.collisionPoint().distance(trajectory.start());
                index = count;
                found = true;
            }
            count++;
        }
        if(found == false)
            return null;
        return allCollisionPointsAndCollidable.get(index);
    }

    //get function for the list of all collidables in the game
    public List<Collidable> getCollidables() {
        return collidables;
    }
}