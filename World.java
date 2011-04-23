import java.util.ArrayList;
/**
 * Write a description of class World here.
 * 
 * @author Greg Myers
 * @version 0
 */
public class World
{
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Route> routes;
    private final float MS_PER_FRAME = (1000 / 25);

    /**
     * Creates the graph of routes from file.
     */
    public boolean loadRoutes()
    {
        return true;
    }
    
    /**
     * Constructor for objects of class World
     */
    public World()
    {
        loadRoutes();
        try{
            run();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() throws InterruptedException
    {
        long startTime = System.currentTimeMillis();
        boolean quit = false;
        while(!quit)
        {
            controllerUpdate();
            viewUpdate();
            //framerate
            long tickTime = System.currentTimeMillis() - startTime;
            if (tickTime < MS_PER_FRAME)
            {
                int sleep = (int)(MS_PER_FRAME - tickTime);
                Thread.currentThread().sleep(sleep);
            }
        }
    }
    
    public boolean controllerUpdate()
    {
        //
        return true;
    }
    
    public boolean viewUpdate()
    {
        //
        return true;
    }
    
    public static void main(String [ ] args)
    {
        new World();
    }
}
