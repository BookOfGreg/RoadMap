import java.util.ArrayList;
/**
 * Write a description of class DualCarriageway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DualCarriageway extends Route
{
    private int speedPrivate = 60;
    private int speedCommercial = 50;

    public DualCarriageway()
    {
        //
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
