import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

//this class controls the ball, including generating new ones, moving it across the screen and detecting collisions
public class Ball extends Rectangle{
	//declare variables
	private final int DIAMETER = 32;
	private final Image soccerBall = new ImageIcon("ball.png").getImage();
    private double x, y, xVelocity = 0, yVelocity = 1;
    private final double speed = 1.8;
	private double ballCenterX, ballCenterY, dx, dy;
	private final double targetX = 250, targetY = 200; //center point of the screen
    private Random rand;
	private boolean bounced = false;
	private boolean hasCollided = false;
	

	public void newBall(){
        //create ball at new randomized x coordinate
		bounced = false;
		rand = new Random();
        x = rand.nextInt(480 - DIAMETER); // spawn the ball inside frame 
        y = 0; // spawn at the top of the screen

		//if the ball x coordinate is outside the goalie area 
		//create a vector to launch the ball at an angle towards the goal
		if (x < 90 || x > 370) {
		 	// calculate the center point of the ball
			ballCenterX = x + DIAMETER / 2.0;
			ballCenterY = y + DIAMETER / 2.0;
 
		 	//calculate the vector component to target 
			dx = targetX - ballCenterX;
			dy = targetY - ballCenterY;
			double length = Math.sqrt(dx * dx + dy * dy); // pythagorean theorem to determine how far the ball has to travel
		
			//update velocity to match angle launch
			xVelocity = dx / length * speed;
			yVelocity = dy / length * speed;
	 	} 
		else { //if the x coordinate is within the goalie area the ball travels straight down
		 xVelocity = 0; //ball doesn't move in the x direction
		 yVelocity = speed;
			}
		}

	//returns the current y coordinate of the ball
	@Override
	public double getY() {
		return y;
	}

	//move method updates the current location of the ball
	public void move() {
		y += (yVelocity*speed);
		x += xVelocity;
	}

	//displays the ball on the screen
	public void draw(Graphics2D g) {
		g.drawImage(soccerBall, (int) x, (int)y, 32, 32, null); 
	}

	//returns the coordinates and dimensions of the ball
    @Override
	public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, DIAMETER, DIAMETER);
    }
	
	//this funtion checks for the first collision of the ball and player allowing the score to update once per ball
	public boolean ballCollision(Rectangle playerBounds) {
		//if a collision hasn't been detected previously and the ball and player intersect
		if (!hasCollided && getBounds().intersects(playerBounds)) {
			hasCollided = true; //detect collision
			return true; // allow for score to update
		} 
		else if (!this.getBounds().intersects(playerBounds)) { // if there is no intersection
			hasCollided = false; //no collision detected 
		}
		return false; //score stays the same
	}
    

	//this method bounces the ball away from the player
	public void bounceAwayFrom(Rectangle Player) {
        if (!bounced) {
			if (x < Player.x) //bounce ball away to left
               xVelocity = -speed;
           	else 
                xVelocity = speed; //bounce ball away to right 
		}

        yVelocity = -1; // bounce straight upward
        bounced = true; //detect bounce
        
    }


}

