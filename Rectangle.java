import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

/**
 * class Rectangle is responsible for every rectangle in the game,
 * it determines it's sizes , location and color
 */

public class Rectangle {

    private Point upperLeft;
    private double widthSize;
    private double heightSize;
    private java.awt.Color color;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color)
    {
        this.upperLeft = upperLeft;
        this.widthSize = width;
        this.heightSize = height;
        this.color = color;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line)
    {
        Line upperHorizontal , botHorizontal ,leftVertical , rightVertical;
        //creating a line for every side of the rectangle
        upperHorizontal = new Line(this.upperLeft.getX(),this.upperLeft.getY(),
                this.upperLeft.getX() + this.widthSize, this.upperLeft.getY());
        botHorizontal = new Line(this.upperLeft.getX(),this.upperLeft.getY() + this.heightSize,
                this.upperLeft.getX() + this.widthSize, this.upperLeft.getY() + this.heightSize);
        leftVertical = new Line(this.upperLeft.getX(),this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.heightSize);
        rightVertical = new Line(this.upperLeft.getX() + this.widthSize,this.upperLeft.getY(),
                this.upperLeft.getX() + this.widthSize, this.upperLeft.getY() + this.heightSize);
        List<Point> listOfPoints = new LinkedList<>();
        listOfPoints.clear();
        if(line.findingIntersection(upperHorizontal) != null) {
            listOfPoints.add(line.findingIntersection(upperHorizontal));
        }
        if(line.findingIntersection(botHorizontal) != null) {
            listOfPoints.add(line.findingIntersection(botHorizontal));
        }
        if(line.findingIntersection(leftVertical) != null) {
            listOfPoints.add(line.findingIntersection(leftVertical));
        }
        if(line.findingIntersection(rightVertical) != null) {
            listOfPoints.add(line.findingIntersection(rightVertical));
        }
        return listOfPoints;
    }

    // @Return the width and height of the rectangle
    public double getWidth() {
        return this.widthSize;
    }


    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.heightSize;
    }


    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft()
    {
        return this.upperLeft;
    }

    //returns the color of the rectangle
    public java.awt.Color getColor()
    {
        return this.color;
    }

    //checks if a given point is inside the rectangle
    public boolean containPoint(Point p)
    {
        if(p.getX() >= this.upperLeft.getX() && p.getX() <= this.upperLeft.getX() + this.widthSize) {
            if (p.getY() >= this.upperLeft.getY() && p.getY() <= this.upperLeft.getY() + this.heightSize)
                return true;
        }
        return false;
    }
}