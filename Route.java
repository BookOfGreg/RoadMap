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
    
    public boolean addVehicle(Vehicle v,int start)
    {
        if (start == 0){
            v.setTarget(node1);
        }else{
            v.setTarget(node2);
        }
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
        for (Vehicle v: vehiclesOnRoad)
        {
            
            //move them
        }
        return null;
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
