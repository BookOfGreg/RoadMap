import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Write a description of class World here.
 * 
 * @author Greg Myers
 * @version 10/05/2011
 */
public class World
{
    private ArrayList<Vehicle> vehiclePool;
    private ArrayList<Route> routes;
    private final float MS_PER_FRAME = (1000 / 25);
    private Random rand;
    private Window worldView;
    private int tickNo;
    
    public int getTickNo()
    {
        return tickNo;
    }

    /**
     * Creates the graph of routes from file.
     */
    public void loadRoutes()
    {
        routes = FileHandler.loadRoutes();
        //give routes positions.
        createGraph();
    }

    public String time(int i)
    {
        int hours = i/3600;
        i=i%3600;
        int minutes = i/60;
        i=i%60;
        int seconds = i;
        
        String sHours=Integer.toString(hours);
        if (sHours.length()==1)
        {
            sHours = 0+sHours;
        }
        String sMinutes=Integer.toString(minutes);
        if (sMinutes.length()==1)
        {
            sMinutes = 0+sMinutes;
        }
        String sSeconds=Integer.toString(seconds);
        if (sSeconds.length()==1)
        {
            sSeconds = 0+sSeconds;
        }
        
        String t=sHours+":"+sMinutes+":"+sSeconds;
        return t;
    }
    
    private void createGraph()
    {
        //work out unique nodes
        ArrayList<Node> uniqueNodes = new ArrayList<Node>();
        for (Route r:routes)
        {
            if (!uniqueNodes.contains(r.getNode1()))
            {
                uniqueNodes.add(r.getNode1());
            }
            if (!uniqueNodes.contains(r.getNode2()))
            {
                uniqueNodes.add(r.getNode2());
            }
        }
        //plot evenly around a circle 10,10 to 210,210
        float angle = 360/uniqueNodes.size();
        for (int n=0;n<uniqueNodes.size();n++)
        {
            uniqueNodes.get(n).setLocation(100 * Math.cos(n * angle)+150,
                100 * Math.sin(n * angle)+150);
        }
    }

    public void loadVehicles()
    {
        vehiclePool = FileHandler.loadVehicles();
    }

    /**
     * Constructor for objects of class World
     */
    public World()
    {
        rand = new Random();
        loadRoutes();
        loadVehicles();
        worldView = new Window();
        int updateLength=updateSelect();
        try{
            run(updateLength);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int updateSelect()
    {
        UserDialog ud = new UserDialog();
        return ud.getInt("how many milliseconds in a simulated second?");//stub
    }

    public void run(int updateLength) throws InterruptedException
    {
        long startTime = System.currentTimeMillis();
        long tickTime = System.currentTimeMillis() - startTime + Math.round(MS_PER_FRAME);
        long updateTime = System.currentTimeMillis() - startTime + updateLength;

        boolean quit = false;
        tickNo=0;
        FileHandler.clearSectData();
        while((!quit)&&(tickNo<86400))
        {
            //1 tick is 1 second. This is unchangable.
            long currentTime = System.currentTimeMillis()-startTime;
            if (currentTime >= updateTime)
            {
                updateTime += updateLength;
                update();
                tickNo++;
            }
            //framerate
            if  (currentTime >= tickTime)
            {
                tickTime += MS_PER_FRAME;
                worldView.render(routes,time(tickNo));
            }
        }
        //pull all vehicles back into the pool.
        for (Route r:routes)
        {
            for (Vehicle v:r.getVehicles())
            {
                vehiclePool.add(v);//
            }
        }
        worldView.render(routes,time(86400)); //Done at the end to make sure last frame is rendered.
        if (!FileHandler.writeSpeedingData(vehiclePool, routes))
        {
            System.out.println("error checking speed limits");
        }
        FileHandler.writeVehicleCosts(vehiclePool);
        worldView.render(routes,time(86400),"Program completed, check ~/SectData.txt for log.");
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
            ArrayList<Vehicle> changingSection = r.moveVehicles(time(tickNo));
            /*
             * get where vehicle is, check all sects for match, random pick sect, set target to other char and add to sect.
             */
            //rewrite, arraylist.remove, random move to pool
            if (changingSection != null){
                for (int i=0; i<changingSection.size();i++)
                {
                    changingSection.get(i).addCost(r.getType(),
                                                   r.getLength()*r.getRate(changingSection.get(i).getType()),
                                                   r.getLength());
                    if(rand.nextFloat()>0.95)
                    {
                        vehiclePool.add(changingSection.remove(i));
                    }
                    else
                    {                    
                        char node = changingSection.get(i).getTarget();
                        ArrayList<Route> matchingSect = new ArrayList<Route>();
                        for (Route sect:routes)
                        {
                            if (sect.matchingNode(node))
                            {
                                matchingSect.add(sect);
                            }
                        }
                        Route r2 = matchingSect.get(rand.nextInt(matchingSect.size()));
                        int s = r.getSpeed(changingSection.get(i).getType())-10+rand.nextInt(15);;
                        double convertToMPS = (double)s / 3600;
                        changingSection.get(i).setSpeed(convertToMPS);
                        changingSection.get(i).setTarget(r2.otherNode(changingSection.get(i).getTarget()));

                        FileHandler.writeStartSectData(changingSection.get(i).getRegistration(),
                            Character.toString(r2.getNode1().getName())+Character.toString(r2.getNode2().getName()),
                            time(tickNo));
                        r2.getVehicles().add(changingSection.remove(i));
                    }
                }
            }
        }
    }

    public void addVehicles()
    {
        //rewrite, arraylist.remove, move from pool
        if (vehiclePool.size()>0)
        {
            for (int i = 0; i<vehiclePool.size();i++)
            {
                if (rand.nextFloat()>0.92)
                {
                    Route r = routes.get(rand.nextInt(routes.size()));
                    int s = r.getSpeed(vehiclePool.get(i).getType());
                    double convertToMPS = (double)s / 3600;
                    vehiclePool.get(i).setSpeed(convertToMPS);
                    if (rand.nextFloat()>0.5)
                    {
                        vehiclePool.get(i).setTarget(r.getNode1().getName());
                    }
                    else
                    {
                        vehiclePool.get(i).setTarget(r.getNode2().getName());
                    }
                    //r.addVehicle(v,rand.nextInt(2));
                    FileHandler.writeStartSectData(vehiclePool.get(i).getRegistration(),
                        Character.toString(r.getNode1().getName())+Character.toString(r.getNode2().getName()),
                        time(tickNo));
                    r.getVehicles().add(vehiclePool.remove(i));
                }
            }
        }
    }

    public static void main(String [ ] args)
    {
        new World();
    }
}
