import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//this class controls the main game panel and detects interaction between all the different elements
public class Game extends JPanel implements ActionListener, KeyListener{

    //declare variables
    Timer tm = new Timer(5,this);
    private Timer endDelay;
    private final JFrame f;
    private Player p;
    private Ball b;
    private static int score = 0;
    private int lives;
    private final Image heart, empty;
    private boolean delay = false;

    //constructor to set panel settings and variables
    public Game(JFrame f){

        //set panel size to 500x400, and initialize panel settings
        this.setPreferredSize(new Dimension(500, 400));
        this.setBackground(new Color(120, 174, 62)); 
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setFocusTraversalKeysEnabled(false);
        this.addKeyListener(this);
        
        tm.start(); //start timer

        //initialize variables 
        this.f = f;
        p = new Player();
        b = new Ball();
        heart = new ImageIcon("heart.png").getImage();
        empty = new ImageIcon("emptyHeart.png").getImage();
        lives = 3;

        //spawn a new ball and display all the components
        b.newBall();
        repaint();
    }

    // method to return score
    public static int getScore(){ return score; }

    //this method draws all the elements of the game on the panel
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g); // paints background components
        Graphics2D g2 = (Graphics2D) g; 

        //draw goalie box and restricted area
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(7));
        g2.drawRect(80, 250, 340, 160);
        g2.drawRect(160, 360, 200, 60);
        g2.drawArc(170, 200, 170, 90, 0, 180);

        //draw player and ball
        p.draw(g2);
        b.draw(g2);
        
        //draw score on the top left corner
        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        g2.drawString( "Score: "+ score, 20, 25);
        
        //draw and update hearts in the right corner based on remaining lives
        for (int i = 0; i < 3; i++) {
            if (i < lives) 
                g.drawImage(heart, 390 + i * 35, 15, 25, 25, this);
            else 
                g.drawImage(empty, 390 + i * 35, 15, 25, 25, this);
        
        }
    }

    //this method contains all of the interacting and moving components of the game
    @Override
    public void actionPerformed(ActionEvent e) { 
        
        //update player movement and ball movement
        p.actionPerformed(e);
        b.move();

        //check for collisons
        checkCollison();

        //detecting if the ball reaches the bottom edge or top edge of the screen and generating a new ball
		if(b.getY() >= 380 || b.getY() < 0 ) { b.newBall(); }
        
        //update player animation to show kicking movement
        p.animation();

        //check if lives are used up to end the game
        checkEndGame();

        //update the graphics for each component
        repaint();
        
    }

    //this method checks for collisions between the ball and player or ball and goal
    private void checkCollison(){
        //dectects collision between ball and the goal causing player to lose a life
		if (b.getBounds().intersects(p.blockedArea)) {
			lives--;
			b.newBall(); //generates new ball
		}

        //once the ball is first detected to collide increase score (to ensure its only increasing once/kick and not once/frame)
        if (b.ballCollision(p.getBounds())) {
            score++; //increase score by one
        }

        //detects collision between ball and player causing the ball to bounce away 
        if (b.getBounds().intersects(p.getBounds())) {
            p.kick(); //generates kick animation
            b.bounceAwayFrom(p.getBounds()); //causes ball to reverse away from player
        }

    }

    //this method checks the live count to decide when to end the game
    private void checkEndGame(){
        // if there are no more lives remaining
        if (lives == 0 && !delay) {
            delay = true; //delay is set to occur
            tm.stop(); // stop the main timer for no ball spawning during delay

            // create a 1 second delay before switching to end screen
            endDelay = new Timer(1000, (ActionEvent e) -> {
                f.getContentPane().remove(Game.this); //clear all content from current panel
                f.add(new endGame(f)); // add end game panel
                f.revalidate();
                f.repaint();
                endDelay.stop(); // stop the delay timer
            });

            endDelay.setRepeats(false); // make sure it only delays once
            endDelay.start(); //start the delay
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {  }

    @Override
    public void keyPressed(KeyEvent e) { p.keyPressed(e);} //collect keyboard input for player

    @Override
    public void keyReleased(KeyEvent e) { p.keyReleased(e);}

}
