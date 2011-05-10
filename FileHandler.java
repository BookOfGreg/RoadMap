import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
/**
 * File handler Accesses, processes and writes everything to do with the text files.
 * 
 * @author Greg Myers
 * @version 10/05/2011
 */
public abstract class FileHandler
{
    /*
     * code based off the SuperSim project, based off SimpleIO
     */
    public static ArrayList<Route> loadRoutes()
    {
        ArrayList<Route> routes = new ArrayList<Route>();

        boolean open = false;
        String currentLine;
        BufferedReader reader;
        //File file = getFile("Select Segments.txt");
        ArrayList<String> nodeOrder = new ArrayList<String>();
        //if (file != null){
        try {
            reader = new BufferedReader(new FileReader("data/Segments.txt"));
            open = true;
            currentLine = readLine(open,reader);
            while (currentLine != null){
                Scanner s = new Scanner(currentLine).useDelimiter("\\s*;\\s*");
                try{
                    Route r;
                    String type = s.next();
                    if (type.equals("motorway")){
                        r = new Motorway("motorway");
                    }
                    else if (type.equals("dual carriageway")){
                        r = new DualCarriageway("dual carriageway");
                    }
                    else{
                        r = new OtherRoad("other road");
                    }
                    r.setLength(s.nextInt());
                    nodeOrder.add(s.next());
                    routes.add(r);
                }
                catch(Exception e){
                    System.err.println("Section type not recognised");
                    return null;
                }
                currentLine = readLine(open,reader);
            }
        }
        catch (FileNotFoundException e) { 
            System.err.println("TextReader: Problem opening file for reading: Segments.txt");
        }
        //}
        //for each string, check if char is unique and if not make node.
        ArrayList<Node> uniqueNodes = new ArrayList<Node>();
        for (String s:nodeOrder)
        {
            boolean found = false;
            outerLoop:
            for (Node n:uniqueNodes)
            {
                if (s.charAt(0)==n.getName())
                {
                    found = true;
                    break outerLoop;
                }
            }
            if (!found)
            {
                uniqueNodes.add(new Node(s.charAt(0)));
            }
            //repeated code
            found = false;
            outerLoop:
            for (Node n:uniqueNodes)
            {
                if (s.charAt(1)==n.getName())
                {
                    found = true;
                    break outerLoop;
                }
            }
            if (!found)
            {
                uniqueNodes.add(new Node(s.charAt(1)));
            }
        }
        //add nodes to routes
        Node n1 = null;
        Node n2 = null;
        for (int i = 0; i < routes.size();i++)
        {
            for (Node n:uniqueNodes)
            {
                if (n.getName()==nodeOrder.get(i).charAt(0))
                {
                    n1=n;
                }
                else if (n.getName()==nodeOrder.get(i).charAt(1))
                {
                    n2=n;
                }
            }
            routes.get(i).setNodes(n1,n2);
        }
        return routes;
    }

    public static ArrayList<Vehicle> loadVehicles()
    {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        boolean open = false;
        String currentLine;
        BufferedReader reader;
        //File file = getFile("Select Vehicles.txt");
        //if (file != null){
        try {
            reader = new BufferedReader(new FileReader("data/Vehicles.txt"));
            open = true;
            currentLine = readLine(open,reader);
            while (currentLine != null){
                Scanner s = new Scanner(currentLine).useDelimiter("\\s*;\\s*");
                try{
                    Vehicle r;
                    String registration = s.next();
                    String type = s.next();
                    if (type.equals("private car")){
                        r = new Private();
                        r.setType("private");
                    }
                    else{
                        r = new Commercial();
                        r.setType("commercial");
                    }
                    r.setRegistration(registration);
                    //r.addCost(s.nextFloat()); //uncomment to make cost roll over to next day
                    vehicles.add(r);
                }
                catch(Exception e){
                    System.err.println("Vehicle type not recognised");
                    return null;
                }
                currentLine = readLine(open,reader);
            }
        }
        catch (FileNotFoundException e) { 
            System.err.println("TextReader: Problem opening file for reading: Vehicles.txt");
        }
        //}
        return vehicles;
    }

    public static boolean clearSectData()
    {
        try{
            FileWriter myFileWriter = new FileWriter("data/segmentData.txt",false);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            myBufferedWriter.write("Registration;Section;Start time,End time");
        }
        catch(Exception e){
            System.err.println("Problem overwriting file.");
        }
        return true;
    }

    public static boolean writeStartSectData(String reg, String sect, String time)
    {
        try{
            FileWriter myFileWriter = new FileWriter("data/segmentData.txt",true);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            String line = reg+";"+sect+";"+time;
            myBufferedWriter.write(line);
            myBufferedWriter.newLine();
            myBufferedWriter.close();
            return true;
        }
        catch(Exception e){
            System.err.println("Problem writing to file.");
        }
        return false;
    }

    public static boolean writeSpeedingData(ArrayList<Vehicle> vehicles, ArrayList<Route> routes)
    {
        ArrayList<String> sectData = readAllLines("data/segmentData.txt");

        outerloop:
        for (int i=0;i<sectData.size();i++)
        {
            Scanner s = new Scanner(sectData.get(i)).useDelimiter("\\s*;\\s*");
            Vehicle currentVehicle = null;
            Route currentRoute = null;
            String registration = s.next();

            loop:
            for (Vehicle v:vehicles)
            {
                if (registration.equals(v.getRegistration()))
                {
                    currentVehicle = v;
                    break loop; //break to specific point to avoid accidentally jumping out of 2 loops.
                }
            }
            String nodePair = s.next();

            loop:
            for (Route r:routes)
            {
                if (r.matchingNode(nodePair.charAt(0))&&r.matchingNode(nodePair.charAt(1)))
                {
                    currentRoute = r;
                    break loop;
                }
            }
            if (currentRoute == null||currentVehicle == null)
            {
                return false;//some kind of error.
            }
            int speedLimit = currentRoute.getSpeed(currentVehicle.getType());
            //distance / time = speed;
            //yes this section has way too many pointless variables but I was too sleepy to bother cutting it down XD
            int distance = currentRoute.getLength();
            String time1 = s.next();
            if (!s.hasNext())
            {
                break outerloop;//
            }
            String time2 = s.next();
            int hoursPassed=Integer.valueOf(Character.toString(time2.charAt(0))+Character.toString(time2.charAt(1)))-Integer.valueOf(Character.toString(time1.charAt(0))+Character.toString(time1.charAt(1)));
            int minutesPassed=Integer.valueOf(Character.toString(time2.charAt(3))+Character.toString(time2.charAt(4)))-Integer.valueOf(Character.toString(time1.charAt(3))+Character.toString(time1.charAt(4)));
            int secondsPassed=Integer.valueOf(Character.toString(time2.charAt(6))+Character.toString(time2.charAt(7)))-Integer.valueOf(Character.toString(time1.charAt(6))+Character.toString(time1.charAt(7)));
            int timePassedSecs=secondsPassed+60*minutesPassed+60*60*hoursPassed;
            double actualMPS = (double)distance/(double)timePassedSecs;
            double actualMPH = actualMPS*3600;
            if (actualMPH > speedLimit)
            {
                String sd=sectData.remove(i);
                sd+=";Speeding " + actualMPH;
                sectData.add(i,sd);
            }
        }
        writeArrayList(sectData, "data/segmentData.txt");
        return true;
    }

    private static ArrayList<String> readAllLines(String fileName)
    {
        ArrayList<String> sectData = new ArrayList<String>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            boolean open = true;
            String currentLine = readLine(open,reader);
            while (currentLine != null){
                sectData.add(currentLine);
                currentLine = readLine(open,reader);
            }
        }
        catch(Exception e){
            System.err.println("Problem reading file.");
        }
        return sectData;
    }

    private static void writeArrayList(ArrayList<String> sectData,String fileName)
    {
        try{
            FileWriter myFileWriter = new FileWriter(fileName,false);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            for (String line:sectData){
                myBufferedWriter.write(line);
                myBufferedWriter.newLine();
            }
            myBufferedWriter.close();
        }
        catch(Exception e){
            System.err.println("Problem writing to file.");
        }
    }
    
    public static boolean writeVehicleCosts(ArrayList<Vehicle> vehicles)
    {
        ArrayList<String> vData = readAllLines("data/Vehicles.txt");
        
        for (int i = 0; i < vData.size(); i++)
        {
            for (Vehicle v:vehicles)
            {
                Scanner s = new Scanner(vData.get(i)).useDelimiter("\\s*;\\s*");
                String reg=s.next();
                if (reg.equals(v.getRegistration()))
                {
                    String type = s.next();
                    String line = vData.remove(i);
                    line = reg+";"+type+";"+v.getTotalCost()/100;
                    vData.add(i,line);
                }
            }
        }
        writeArrayList(vData,"data/vehicles.txt");
        return true;
    }

    public static boolean writeEndSectData(String reg, String time)
    {
        ArrayList<String> sectData = readAllLines("data/segmentData.txt");

        outerloop:
        for (int i = 0; i < sectData.size();i++){
            Scanner s = new Scanner(sectData.get(i)).useDelimiter("\\s*;\\s*");
            if (s.next().equals(reg)){
                s.next();//section
                s.next();//start time
                if (!s.hasNext()){
                    String line = sectData.remove(i);
                    line = line +";"+ time;
                    sectData.add(i,line);
                    break outerloop;
                }
            }
        }

        writeArrayList(sectData,"data/segmentData.txt");
        return true;
    }

    /*
     * reinvent the wheel anyone?
     */
    /**
     * This was based off SimpleIO
     * 
     * Use a file choser to allow the user to select a file
     * @return file   the selected file or null if user cancels out
     */
    private static File getFile(String comment) {
        File currentDirectory = null;
        //fix to make sure the file chooser is on top of the BlueJ window
        JFrame jf = new JFrame(comment); 
        jf.setVisible(true);
        jf.setAlwaysOnTop(true);
        jf.setAlwaysOnTop(false);
        JFileChooser fc = new JFileChooser();
        // use the current directory if there is one
        if (currentDirectory != null)
            fc.setCurrentDirectory(currentDirectory);
        int response = fc.showOpenDialog(null); // prompt user for filename
        jf.dispose();
        if (response == JFileChooser.APPROVE_OPTION){  // response ok
            currentDirectory = fc.getCurrentDirectory();
            return fc.getSelectedFile();
        }
        else{
            return null;
        }
    }

    /**
     * This was based off SimpleIO.
     * 
     * Reads a complete line
     * @return  the remainder of the line as a string,
     *          null if the end-of-file is reached
     *          null if an I/O exception is raised
     */
    private static String readLine(boolean open, BufferedReader reader)
    {
        if (open){
            try{
                return reader.readLine();
            } 
            catch (IOException e) {
                System.err.println("TextReader: IOException raised in readLine(): " +e);
                return null;
            }
        }
        else {
            System.err.println("TextReader: File is not open in readLine()");
            return null;
        }       
    }
}
