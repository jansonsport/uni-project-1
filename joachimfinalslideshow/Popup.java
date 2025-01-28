import javax.swing.JOptionPane;

/**
 * my attempt at the final challenge question
 * this class serves to create the statistics pop-up when the statistics button is pressed
 * seperate class was made to keep changes to gui class to a minimum
 */
public class Popup 
{
    private int imagesViewed;
    private int averageWidth;
    /**
     * initialising the pop-up and the data that is being stored
     */
    public Popup() 
    {
    }
    
    /**
     * this will display the statistics pop-up
     */
    public void displayPopup(int imagesViewed, int avgWidth)
    {
        String message = "<html>Number of images viewed: " + imagesViewed + "<br>"
            + "Average Width of images viewed: " + avgWidth + " pixels"; // Message
        Object[] options = {"Close"};
        
        JOptionPane.showOptionDialog(
            null,                               // displays text in the centre
            message,                            
            "Statistics",                       // title
            JOptionPane.DEFAULT_OPTION,         // Option type
            JOptionPane.INFORMATION_MESSAGE,    // Message type
            null,                               // Icon (null for default)
            options,                            // Options
            options[0]                          // Default option
        );
    }
}
