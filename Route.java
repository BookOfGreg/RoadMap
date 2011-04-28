import java.util.ArrayList;
/**
 * Abstract class Route - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public abstract class Route
{
    private int length;
    private char node1;
    private char node2;
    private String type;
    private int speedPrivate;
    private int speedCommercial;
    private ArrayList<Vehicle> vehiclesOnRoad;
    
    public ArrayList<Vehicle> getVehicles()
    {
        return vehiclesOnRoad;
    }
    
    public boolean setLength(int l)
    {
        length = l;
        return true;
    }
    
    public boolean setNodes(String s)
    {
        setNode1(s.charAt(0));
        setNode2(s.charAt(1));
        return true;
    }
    
    private boolean setNode1(char c)
    {
        node1 = c;
        return true;
    }
    
    private boolean setNode2(char c)
    {
        node2 = c;
        return true;
    }
    
    public boolean addVehicle(Vehicle v,int start)
    {
        if (start == 0){
            v.setTarget(node1);
        }else{
            v.setTarget(node2);
        }
        //v.velocity = selectSpeed(); //going to need to typecast.
        vehiclesOnRoad.add(v);
        return true;
    }
    
    public boolean addVehicle(Vehicle v, char node)
    {
        if (node == node1){
            v.setTarget(node2);
        }else{
            v.setTarget(node1);
        }
        vehiclesOnRoad.add(v);
        return true;
    }
    
    public ArrayList<Vehicle> moveVehicles()
    {
        ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
        for (Vehicle v: vehiclesOnRoad)
        {
            v.travel();
            if (v.getDistanceTraveled()>=length)
            {
                vList.add(v); //Might cause problems removing v in middle of for loop of v.
                //write sect data
            }
        }
        return vList;
    }
    
    public boolean matchingNode(char node)
    {
        if ((node == node1)||(node == node2)){
            return true;
        }else{
            return false;
        }
    }
}
