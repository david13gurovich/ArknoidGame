import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Line creates and responsible for line objects that are basicly a line between two points
 * each line should contain 2 points to be created that will represent it's start and end.
 */
public class Line {

    private Point start, end;

    /**
     * build function
     * @param start - a point that represents the start of a new line
     * @param end - a point that represents the end of a new line
     */
    public Line(Point start,Point end){
        this.start = start;
        this.end = end;
    }

    /**
     * build function
     * @param x1 - a x value of a point that represents the start of a new line
     * @param y1 - a y value of a point that represents the end of a new line
     * @param x1 - a x value of a point that represents the start of a new line
     * @param y1 - a y value of a point that represents the end of a new line
     */
    public Line(double x1, double y1, double x2, double y2){
        this.start = new Point(x1,y1);
        this.end = new Point(x2,y2);
    }

    /**
     * function that gets the start point of a line
     * @return the varriable of the point that represents the start of a line
     */
    public Point start() {
        return  this.start;
    }

    /**
     * function that gets the end point of a line
     * @return the varriable of the point that represents the end of a line
     */
    public Point end() {
        return  this.end;
    }

    /**
     * function that gets length of a line
     * @return the value of the distance between the start point and end point of a line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * a function that calculates the middle of a line
     * by finding the avrage value of the x and y of the 'start' and 'end' points of a line
     * @return a point that is the middle of the line
     */
    public Point middle() {
        double middleX, middleY;
        middleX = (this.start.getX() + this.end.getX())/2;
        middleY = (this.start.getY() + this.end.getY())/2;
        Point middle = new Point(middleX,middleY);
        return middle;
    }

    /**
     * a function that checks if a certain point is a part of a line
     * @param toCheck is the point we want to check if it's actually a part of a line
     * @param start is the start point of a line
     * @param end is the end point of a line
     * @return a boolean value whethere if the ppoint is a part of a line
     */
    private boolean containsPoint(Point toCheck, Point start, Point end)
    {
        double x = toCheck.getX();
        double y = toCheck.getY();

        //the case if the line that is cheked is actually a point

        if(start == end )
        {
            if(start != toCheck)
            {
                return  false;
            }
        }

        //check the case if the x value of the point is inside the range of the x values of
        //the 'start' and 'end' points of the line
        if(start.getX() > end.getX())
        {
            if(x > start.getX() || x < end.getX())
            {
                return false;
            }
        }
        if(start.getX() < end.getX())
        {
            if(x < start.getX() || x > end.getX())
            {
                return false;
            }
        }

        //check the case if the y value of the point is inside the range of the y values of
        //the 'start' and 'end' points of the line
        if(start.getY() > end.getY())
        {
            if(y > start.getY() || y < end.getY())
            {
                return false;
            }
        }
        if(start.getY() < end.getY())
        {
            if(y < start.getY() || y > end.getY())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * a function that calculates the slope of the line using known formula
     * @param start - is the start point of a line
     * @param end - is the end point of the line
     * @return the slope value of a line
     */
    private double slopeCalc(Point start,Point end)
    {
        if(start.getX() == end.getX())
        {
            return 0;
        }
        else {
            //delta y divide by delta x
            return (start.getY() - end.getY())/(start.getX() - end.getX());
        }
    }

    /**
     * in a representation of a line in this form: y = mx + b this function findes the 'b'
     * @param start - start point of a line
     * @param end - end point of a line
     * @param slope - a calculated slope of the same line
     * @return the 'b' value to have the full equation of the line
     */
    private double interWithY(Point start,Point end, double slope)
    {
        if(start.getX() == end.getX())
        {
            return  0;
        }
        return (start.getY() - (slope * start.getX()));
    }

    /**
     * this function findes intersection point (if it exists) between a line and other line that is given as parameter
     * @param other - other line to find intersection point with
     * @return null - if there are no intersection points, or Point - that is the
     * intersection point between the two lines
     */
    public Point findingIntersection(Line other){
        double currentSlope, otherSlope, currentInterWithY, otherInterWithY, x , y;
        x = this.start.getX();
        y = this.start.getY();

        //finding all relevant values to have two equations each for a line
        currentSlope = slopeCalc(this.start, this.end);
        otherSlope = slopeCalc(other.start(), other.end());
        currentInterWithY = interWithY(this.start(),this.end(), currentSlope);
        otherInterWithY = interWithY(other.start(), other.end(), otherSlope);

        //the case if both lines are points
        if(currentSlope == 0 && otherSlope == 0)
        {
            if((this.start.getX() == this.end.getX()) && (other.start().getX() == other.end().getX())) {
                return null;
            }
        }

        //the case if the both lines have the same slope than they have no intersection,
        //unless the have the same equation and in this case i chose to return null nevertheless
        if((this.start.getX() != this.end.getX()) && (other.start().getX() != other.end().getX())) {
            if (currentSlope == otherSlope) {
                return null;
            }
            //building equation for the other line
            x = (otherInterWithY - currentInterWithY) / (currentSlope -otherSlope);
            y = x * currentSlope + currentInterWithY;
        }

        //the case if the first line is vertical
        if(this.start.getX() == this.end.getX()){
            x = this.start.getX();
            y = x * otherSlope + otherInterWithY;
        }

        //the case if the second line is vertical
        if(other.start().getX() == other.end().getX()){
            x = other.start().getX();
            y = x * currentSlope + currentInterWithY;
        }
        Point intersection = new Point(x,y);

        //checks that the intersection point that had been found is in the range of our particular lines
        if(!containsPoint(intersection, this.start, this.end))
        {
            return null;
        }
        if(!containsPoint(intersection, other.start(), other.end()))
        {
            return null;
        }
        return intersection;
    }

    /**
     * a small public function that checkes if thare is an interaction point between two lines
     * @param other - is the other line to be compared with
     * @return boolean value whethere if there is a point of intersection between the two lines
     */
    public boolean isIntersecting(Line other) {
        if(findingIntersection(other) != null)
        {
            return true;
        }
        return false;
    }

    /**
     * almost the same funtion but it returns theactual point if exists and null if not
     * @param other the other line
     * @return the intersection point if exists and null if not
     */
    public Point intersectionWith(Line other) {
        return findingIntersection(other);
    }

    /**
     * a function that checkes if the two lines are exactly the same, by comparing the 'start' and 'end' points
     * @param other - is the other line that is compared with
     * @return a boolean value, true if the lines are the same anf false if they are not
     */
    public boolean equals(Line other) {
        if((this.start == other.start() && this.end == other.end())
                || (this.start == other.end() && this.end == other.start()))
        {
            return true;
        }
        return false;
    }

    /**
     * auxiliry function that finds the distance from the start of the line to a given point
     * @param point is the given point that we need to calculate the distance to
     * @return the distance between "point" and the start point of the line
     */
    public double getDistanceFromStart(Point point)
    {
        if(!(this.containsPoint(point,this.start(),this.end())))
        {
            return -1;
        }
        else {
            return this.start().distance(point);
        }

    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double min =0;
        boolean neverChanged = true;
        Point closest;
        closest = null;
        List<Point> points = new LinkedList<>();
        points = rect.intersectionPoints(this);
        for(Point point : points)
        {
            //first possability defiens the min value for later iterations
            if(neverChanged && this.getDistanceFromStart(point) > 0)
            {
                min = this.getDistanceFromStart(point);
                closest = point;
                neverChanged = false;
            }
            if(neverChanged == false && this.getDistanceFromStart(point) > 0 &&
                    this.getDistanceFromStart(point)  < min)
            {
                min = this.getDistanceFromStart(point);
                closest = point;
            }
        }
        return closest;
    }
}
