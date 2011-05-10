
/**
 * Graph node, consists of a point and a char for a name.
 * 
 * @author Greg Myers
 * @version 1
 */
public class Node
{
    private char name;
    private Point location;

    /**
     * Constructor for objects of class Node
     */
    public Node(char n)
    {
        name = n;
    }
    
    public char getName()
    {
        return name;
    }
    
    public void setLocation(double x, double y)
    {
        location = new Point(x,y);
    }
    
    public boolean hasPoint()
    {
        if (location != null)
        {
            return true;
        }
        return false;
    }
    
    public double getX()
    {
        return location.getX();
    }
    
    public double getY()
    {
        return location.getY();
    }
}
