import javax.swing.*;
public class Project2Runner {
     
    public static void main(String[] args) {

            //create and initialize new jframe
            JFrame f = new JFrame("Ultimate Goalie");
            f.add(new Landing(f)); //load landing page onto frame
            f.pack(); // sets the frame size to preferred size of the panel
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button can be used
            f.setLocationRelativeTo(null); // center window on screen
            f.setVisible(true);
            f.setResizable(false); 
        }

    }
