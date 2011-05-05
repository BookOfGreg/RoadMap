import java.util.ArrayList;
/**
 * Write a description of class OtherRoad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OtherRoad extends Route
{
    private int speedPrivate = 50;
    private int speedCommercial = 50;

    public OtherRoad()
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
