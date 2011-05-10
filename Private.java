
/**
 * Only contains the remaining free motorway miles, 
 * Its type is handled as a string in Vehicle rather than as typecasting.
 * 
 * @author Greg Myers
 * @version 0
 */
public class Private extends Vehicle
{
    private float freeMiles = 5;
    private float higherMiles = 10;
    private int higherRate = 2;

    /**
     * Constructor for objects of class Private
     */
    public Private()
    {
        // initialise instance variables
    }

    @Override
    public boolean addCost(String t,float amount,int distance)
    {
        if (t.equals("motorway") && (freeMiles>0))
        {
            if (freeMiles < distance)
            {
                distance -= freeMiles;
                freeMiles = 0;
            }
            else
            {
                freeMiles -= distance;
                distance = 0;
            }
        }
        if (t.equals("motorway") && (freeMiles<=0) && (higherMiles>0))
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
