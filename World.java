import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class World here.
 * 
 * @author Greg Myers
 * @version 0
 */
public class World
{
    private ArrayList<Vehicle> vehiclePool;
    private ArrayList<Route> routes;
    private final float MS_PER_FRAME = (1000 / 25);
    private Random rand;

    /**
     * Creates the graph of routes from file.
     */
    public boolean loadRoutes()
    {
        //
        return true;
    }
    
    /**
     * Constructor for objects of class World
     */
    public World()
    {
        rand = new Random();
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
            update();
            //framerate
            long tickTime = System.currentTimeMillis() - startTime;
            if (tickTime < MS_PER_FRAME)
            {
                int sleep = (int)(MS_PER_FRAME - tickTime);
                Thread.currentThread().sleep(sleep);
            }
            render();
        }
    }
    
    public void render()
    {
        //send stuff to view.
    }
    
    public void update()
    {
        moveVehicles();
        addVehicles();
    }
    
    public void moveVehicles()
    {
        for (Route r:routes)
        {
            ArrayList<Vehicle> changingSection = r.moveVehicles();
            /*
             * get where vehicle is, check all sects for match, random pick sect, set target to other char and add to sect.
             */
            for (Vehicle v:changingSection)
            {
                char node = v.getTarget();
                ArrayList<Route> matchingSect = new ArrayList<Route>();
                for (Route sect:routes)
                {
                    if (sect.matchingNode(node))
                    {
                        matchingSect.add(sect);
                    }
                }
                matchingSect.get(rand.nextInt(matchingSect.size())).addVehicle(v,node);
            }
        }
    }
    
    public void addVehicles()
    {
        for (Vehicle v:vehiclePool)
        {
            if (rand.nextFloat()>0.92)
            {
                routes.get(rand.nextInt(routes.size())).addVehicle(v,rand.nextInt(2));
            }
        }
    }
    
    public static void main(String [ ] args)
    {
        new World();
    }
}
