import javax.swing.*;

/**
 * Defines simple dialog boxes for interacting with the user, by extending JOptionPane. 
 * 
 * It is able to show a simple string message, get an integer, float, double, boolean or string from the user, or get the user to confirm "yes" or "no". 
 * User input is converted to the appropriate type. If an inappropriate type is input, an error dialog box is displayed, and a default value returned. 
 * 
 * @version 1.2 07/12/03
 * @author (adapted from an original example by Ken Brown)
 */

public class UserDialog extends JOptionPane {

    public int getInt(String output) 
    {
        String inputStr = showInputDialog(null, output);
        try 
        {
            return Integer.parseInt(inputStr);
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, inputStr + " is not an integer", "ERROR", ERROR_MESSAGE);
            return -1;
        }
    }
  
}