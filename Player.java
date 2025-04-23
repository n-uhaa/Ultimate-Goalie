import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;

//this class controls all the player operations including movement, collisions and avatar animation
public class Player extends Rectangle implements ActionListener, KeyListener{

    //declare variables
    private int x, y;
    private final int SPEED = 1;
    private final int SIZE = 45;
    private final Set<Integer> pressedKeys = new HashSet<>(); //hashset to cue movements before they happen for smooth animation
    private final Image p;
    private final Image pKick;
    private Image currentSprite;
    private int kickTimer = 0;
    //the goal area that the player cant enter
    Rectangle blockedArea = new Rectangle(165, 360, 195, 60);
    
    public Player(){  

        //set animation images
        p = new ImageIcon("player.png").getImage();
        pKick = new ImageIcon("kick.png").getImage();
        currentSprite = p; //to update animation
        //set spawning point for player
        x = 250;
        y = 300;

    }

    //method to return dimensions of player
    @Override
	public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    //method to draw player at various locations
    public void draw(Graphics2D g){
        g.drawImage(currentSprite, x, y, SIZE, SIZE, null); 
    }

    //method to create kick animation for 50 miliseconds
    public void kick(){
        currentSprite = pKick; //switch image 
        kickTimer = 50; //timer
    }


    //method to control movement of the player and block the player from entering certain area
    @Override
    public void actionPerformed(ActionEvent e) {

        //restrict player from exiting the goalie area
        if (x <= 80) x = 80;
        if (x > 390) x = 390;
        if (y < 250) y = 250;
        if (y >= 360 ) y = 360;
        
       //set current position based on key input
       int dx = 0, dy = 0;
       if (pressedKeys.contains(KeyEvent.VK_LEFT)) dx = -SPEED;
       if (pressedKeys.contains(KeyEvent.VK_RIGHT)) dx = SPEED;
       if (pressedKeys.contains(KeyEvent.VK_UP)) dy = -SPEED;
       if (pressedKeys.contains(KeyEvent.VK_DOWN)) dy = SPEED;

       // set next position based on input
       Rectangle nextPos = new Rectangle(x + dx, y + dy, SIZE, SIZE);
   
       // only allow movement if player doesn't intersect the blocked area
       if (!nextPos.intersects(blockedArea)) {
           x += dx; //keep the same coordinates
           y += dy;
       }
        
    }

    //create kick animation 
    public void animation() {
        if (kickTimer > 0) { //subtract kick timer until 0
            kickTimer--;
            if (kickTimer == 0) //revert sprite back to not kicking
                currentSprite = p;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { pressedKeys.add(e.getKeyCode()); }

    @Override
    public void keyReleased(KeyEvent e) { pressedKeys.remove(e.getKeyCode()); }


}
