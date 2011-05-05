
/**
 * Abstract class Vehicle - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public class Vehicle
{    
    private String registration;
    private float totalCost;
    private double velocity; //miles per second
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
        distanceTraveled+=velocity; /// THIS IS MASSIVE
        return true;
    }
    
    public boolean setRegistration(String reg)
    {
        registration = reg;
        return true;
    }
}
