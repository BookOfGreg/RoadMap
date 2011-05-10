import java.util.ArrayList;
/**
 * Write a description of class OtherRoad here.
 * 
 * @author Greg Myers
 * @version 1
 */
public class OtherRoad extends Route
{
    private int speedPrivate = 50;
    private int speedCommercial = 50;
    private int costPrivate = 0;
    private int costCommercial = 5;

    public OtherRoad(String t)
    {
        type = t;
    }
    
    public OtherRoad()
    {
        //
    }
    
    @Override
    public int getRate(String t)
    {
        if (t.equals("private"))
        {
            return costPrivate;
        }
        else
        {
            return costCommercial;
        }
    }
    
    @Override
    public int getSpeed(String type)
    {
        if(type.equals("private"))
        {
            return speedPrivate;
        }
        else
        {
            return speedCommercial;
        }
    }
}
