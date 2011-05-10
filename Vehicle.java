
/**
 * Vehicle has a target, a speed and a distance traveled.
 * 
 * @author Greg Myers
 * @version 1
 */
public class Vehicle
{    
    private String registration;
    protected float totalCost;
    protected double velocity; //miles per second
    private char target;
    private double distanceTraveled;
    private String type;

    public String getType()
    {
        return type;
    }
    
    public void setSpeed(double s)
    {
        velocity = s;
        distanceTraveled = 0;
    }
    
    public Vehicle()
    {
        registration = "";
        totalCost = 0;
    }
    
    public void setType(String t)
    {
        type = t;
    }
    
    public boolean addCost(String t, float amount,int distance)
    {
        totalCost+=amount;
        return true;
    }
    
    public boolean addCost(float amount)
    {
        totalCost+=amount;
        return true;
    }
    
    public String getRegistration()
    {
        return registration;
    }
    
    public float getTotalCost()
    {
        return totalCost;
    }
    
    public char getTarget()
    {
        return target;
    }
    
    public double getDistanceTraveled()
    {
        return distanceTraveled;//
    }
    
    public boolean setTarget(char newTarget)
    {
        target = newTarget;
        return true;
    }
    
    public boolean travel()
    {
        distanceTraveled+=velocity;
        return true;
    }
    
    public boolean setRegistration(String reg)
    {
        registration = reg;
        return true;
    }
}
