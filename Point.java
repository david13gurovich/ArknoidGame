/**
 * @Author: David Gurovich
 * ID: 318572047
 * Class description: Class Point gets 2 double varriables wich will represent the new point location.
 * there are some function that calculate some relevant information about any particular point.
 */

import java.lang.Math;

public class Point {

    private double x;
    private double y;

    /**
     * build function
     * @param x - is the x value of the pont
     * @param y - is the y value of the pont
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * get function of x
     * @return the x value of a point
     */
    public  double getX()
    {
        return  this.x;
    }

    /**
     * get function of y
     * @return the y value of a point
     */
    public  double getY()
    {
        return  this.y;
    }

    /**
     * a function that calculates distance between two points
     * @param other - is another point to which this function will calculate the distance to
     * @return 0 if it's the same point and double value wich represents the distence between the two points
     */
    public double distance(Point other){
        double hight , length , distance;
        hight = other.getY() - this.y;
        length = other.getX() - this.x;
        if(hight == 0 && length == 0){
            return 0;
        }
        distance = Math.sqrt(hight*hight + length*length);
        return distance;
    }

    /**
     * function that checks if the to points are the same
     * @param other - is another point, to which the current point will be compared to
     * @return boolean value wether it's the same point or not
     */
    public boolean equals(Point other){
        if(other.getX() == this.x && other.getY() == this.y )
        {
            return true;
        }
        return false;
    }
}