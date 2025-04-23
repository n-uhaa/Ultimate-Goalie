import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//this class displays the end game panel that shows the score once the game is over
public class endGame extends JPanel implements ActionListener{

    //declare variables
    private JFrame f;
    private JButton replay;

    public endGame(JFrame f){
        
        this.f = f;
        //set panel settings
        setBackground(new Color(120, 174, 62)); 
        requestFocusInWindow();
        setFocusable(true);
        
        //initialize button to show on the center of the panel
        setLayout(null);
        replay = new JButton("Play Again");
        replay.setBounds(205, 205, 100, 30);

        //detect input from button
        replay.addActionListener(this);
        //display button
        add(replay);
    }

    //method to draw the elements on the panel
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        String s = "Score: " + Game.getScore();
        Graphics2D g2 = (Graphics2D) g;

        //display score and game over
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.drawString("GAME OVER", 170, 155);
        g2.drawString(s, 200, 190);

    }

    //method to perform action when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        //when the button is clicked we move back to the game panel
        if (e.getSource() == replay){ 
            f.getContentPane().remove(this); //clears all content from current panel
            Game g = new Game(f); //new game panel
            f.add(g);// displays all content from game panel
            //allows input to be accepted from panel
            f.revalidate();  
            f.repaint();
            SwingUtilities.invokeLater(() -> g.requestFocusInWindow()); //allow for key input on new panel
        }
        repaint(); //paint elements
     }
}
