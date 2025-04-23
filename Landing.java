
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//this class creates the landing page and allows users to start the game
public class Landing extends JPanel implements ActionListener{

    //declare variables
    private final JButton play;
    private final JFrame f;
    private final Image background;

    public Landing(JFrame f)
    {
        //panel settings
        setPreferredSize(new Dimension(500, 400));
        
        //define frame
        this.f = f;

        //initialize button to show at the bottom of the screen
        setLayout(null);
        play = new JButton("START!");
        play.setBounds(350, 360, 100, 30);

        //detect input from buttons
        play.addActionListener(this);
        
        //display buttons
        add(play);

        //Load background image
        background = new ImageIcon("start.png").getImage();

    }
   
    //this method creates a new panel once the button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        //if the start button is pushsed we switch to game panel
        if (e.getSource() == play){ 
            f.getContentPane().remove(this); //clears all content from current panel
            Game g = new Game(f); //new game panel
            f.add(g);// displays all conetnt from game panel
            f.revalidate();  
            f.repaint(); //paint new elements
            SwingUtilities.invokeLater(() -> g.requestFocusInWindow()); //allow for input from the new panel
        }
        repaint(); //draw elements
    }

    //method to paint elements on panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 500, 400, this); //draw background image
    }

}
