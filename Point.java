
/**
 * My own implementation of the Java Point class but using Doubles instead.
 * 
 * @author Greg Myers
 * @version 1
 */
public class Point
{
    private double x;
    private double y;

    /**
     * Constructor for objects of class Point
     */
    public Point(double newX, double newY)
    {
        setLocation(newX,newY);
    }

    public double getY()
    {
        return y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public void setLocation(double newX, double newY)
    {
        x=newX;
        y=newY;
    }
}
