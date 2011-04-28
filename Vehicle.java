
/**
 * Abstract class Vehicle - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public abstract class Vehicle
{    
    private String registration;
    private float totalCost;
    private float velocity; //km per minute
    private char target;
    private float distanceTraveled;

    public Vehicle()
    {
        registration = "";
        totalCost = 0;
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
    
    public float getDistanceTraveled()
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
