
/**
 * Abstract class Vehicle - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public abstract class Vehicle
{    
    private String REGISTRATION;
    private float totalCost;
    private float velocity; //km per minute
    private char target;
    private float distanceTraveled;

    public Vehicle()
    {
        REGISTRATION = "";
        totalCost = 0;
    }
    
    public boolean addCost(float amount)
    {
        totalCost+=amount;
        return true;
    }
    
    public String getRegistration()
    {
        return REGISTRATION;
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
    
    public boolean setDistanceTraveled(float distance)
    {
        distanceTraveled = distance;
        return true;
    }
    
    public boolean setTarget(char newTarget)
    {
        target = newTarget;
        return true;
    }
}
