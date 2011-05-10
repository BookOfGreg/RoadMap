import java.util.ArrayList;
/**
 * Write a description of class DualCarriageway here.
 * 
 * @author Greg Myers
 * @version 1
 */
public class DualCarriageway extends Route
{
    private int speedPrivate = 60;
    private int speedCommercial = 50;
    private int costPrivate = 1;
    private int costCommercial = 2;

    public DualCarriageway(String t)
    {
        type = t;
    }
    
    public DualCarriageway()
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
