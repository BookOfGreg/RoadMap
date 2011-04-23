
/**
 * Abstract class Vehicle - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public abstract class Vehicle
{    
    private float totalCost;
    private String registration;

    public boolean addCost(float amount)
    {
        totalCost+=amount;
        return true;
    }
}
