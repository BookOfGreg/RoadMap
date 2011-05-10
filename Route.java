import java.util.ArrayList;

/**
 * Route consists of 2 nodes and the distance between them. 
 * Subclasses have different speeds based on the type of vehicle on them.
 * 
 * @author Greg Myers
 * @version 10/05/2011
 */
public class Route
{
    private Node node1;
    private Node node2;

    private int length;
    protected String type;
    private int speedPrivate;
    private int speedCommercial;
    private ArrayList<Vehicle> vehiclesOnRoad;

    public String getType()
    {
        return type;
    }
    
    public int getSpeed(String t)
    {
        return 0;
    }
    
    public int getRate(String t)
    {
        return 0;
    }

    public boolean hasPoints()
    {
        if ((node1.hasPoint()) && (node2.hasPoint()))
        {
            return true;
        }
        return false;
    }

    public int getLength()
    {
        return length;
    }
    
    public Route()
    {
        vehiclesOnRoad = new ArrayList<Vehicle>();
    }

    public Route(String t)
    {
        type = t;
        vehiclesOnRoad = new ArrayList<Vehicle>();
    }

    public ArrayList<Vehicle> getVehicles()
    {
        return vehiclesOnRoad;
    }

    public void setLength(int l)
    {
        length = l;
    }

    public void setNodes(Node n1,Node n2)
    {
        node1 = n1;
        node2 = n2;
    }

    public char otherNode(char node)
    {
        if (node == node1.getName())
        {
            return node2.getName();
        }
        else 
        {
            return node1.getName();
        }
    }

    public ArrayList<Vehicle> moveVehicles(String time)
    {
        ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
        if (vehiclesOnRoad.size()>0){
            for (int i = 0; i < vehiclesOnRoad.size();i++)
            {
                vehiclesOnRoad.get(i).travel();
                if (vehiclesOnRoad.get(i).getDistanceTraveled()>=length)
                {
                    FileHandler.writeEndSectData(vehiclesOnRoad.get(i).getRegistration(),time);
                    vList.add(vehiclesOnRoad.remove(i));
                }
            }
            return vList;
        }
        return null;
    }

    public boolean matchingNode(char node)
    {
        if ((node == node1.getName())||(node == node2.getName())){
            return true;
        }else{
            return false;
        }
    }

    public Node getNode1()
    {
        return node1;
    }

    public Node getNode2()
    {
        return node2;
    }
}
