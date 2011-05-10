
/**
 * Doesn't actually need to do anything in this sim, 
 * Its type is handled as a string in Vehicle rather than as typecasting.
 * 
 * @author Greg Myers 
 * @version 0
 */
public class Commercial extends Vehicle
{
    private float higherMiles = 10;
    private int higherRate = 3;
    
    /**
     * Constructor for objects of class Commercial
     */
    public Commercial()
    {
        //
    }
    
    @Override
    public boolean addCost(String t,float amount,int distance)
    {
        if (t.equals("dual carriageways") && (higherMiles>0))
        {
            if (higherMiles < distance)
            {
                distance -= higherMiles;
                totalCost += higherMiles*higherRate;
                higherMiles = 0;
            }
            else
            {
                higherMiles -= distance;
                totalCost += distance*higherRate;
                distance = 0;
            }
        }
        totalCost += distance*amount;

        return true;
    }
}
