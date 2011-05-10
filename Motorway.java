import java.util.ArrayList;
/**
 * Write a description of class Motorway here.
 * 
 * @author Greg Myers
 * @version 1
 */
public class Motorway extends Route
{
    private int speedPrivate = 70;
    private int speedCommercial = 60;
    private int costPrivate = 1;
    private int costCommercial = 2;

    public Motorway(String t)
    {
        type = t;
    }
    
    public Motorway()
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
