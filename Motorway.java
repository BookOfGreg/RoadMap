import java.util.ArrayList;
/**
 * Write a description of class Motorway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Motorway extends Route
{
    private int speedPrivate = 70;
    private int speedCommercial = 60;

    public Motorway()
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
