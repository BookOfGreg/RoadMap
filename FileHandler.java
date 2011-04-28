import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
/**
 * Abstract class FileHandler - write a description of the class here
 * 
 * @author Greg Myers
 * @version 0
 */
public abstract class FileHandler
{
    /*
     * code based off the SuperSim project, based off SimpleIO
     */
    public ArrayList<Route> loadRoutes()
    {
        ArrayList<Route> routes = new ArrayList<Route>();

        boolean open = false;
        String currentLine;
        BufferedReader reader;
        File file = getFile("Select Segments.txt");
        if (file != null){
            try {
                reader = new BufferedReader(new FileReader(file));
                open = true;
                currentLine = readLine(open,reader);
                while (currentLine != null){
                    Scanner s = new Scanner(currentLine).useDelimiter("\\s*;\\s*");
                    try{
                        Route r;
                        String type = s.next();
                        if (type.equals("motorway")){
                            r = new Motorway();
                        }
                        else if (type.equals("dual carriageway")){
                            r = new DualCarriageway();
                        }
                        else{
                            r = new OtherRoad();
                        }
                        r.setLength(s.nextInt());
                        r.setNodes(s.next());
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
                System.err.println("TextReader: Problem opening file for reading: "+ file.getAbsolutePath());
            }
        }
        return routes;
    }

    public ArrayList<Vehicle> loadVehicles()
    {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        boolean open = false;
        String currentLine;
        BufferedReader reader;
        File file = getFile("Select Vehicles.txt");
        if (file != null){
            try {
                reader = new BufferedReader(new FileReader(file));
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
                        }
                        else{
                            r = new Commercial();
                        }
                        r.setRegistration(registration);
                        r.addCost(s.nextFloat());
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
                System.err.println("TextReader: Problem opening file for reading: "+ file.getAbsolutePath());
            }
        }
        return vehicles;
    }

    public static boolean writeStartSectData(String reg, String sect, String time)
    {
        //EOF, write reg, write sect, write time
        try{
            FileWriter myFileWriter = new FileWriter("data/SegmentData.txt",true);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            String line = reg+";"+sect+";"+time+":";
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

    public static boolean writeEndSectData(String reg, String time)
    {
        ArrayList<String> sectData = new ArrayList<String>();;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data/SegmentData.txt"));
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

        outerloop:
        for (String line:sectData){

            Scanner s = new Scanner(line).useDelimiter("\\s*;\\s*");
            if (s.next().equals(reg)){
                s.next();//section
                s.next();//start time
                if (s.next() != null){
                    line = line + time;
                    break outerloop;
                }
            }
        }
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
