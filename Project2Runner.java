import javax.swing.*;
public class Project2Runner {
    /*
     * Name: Nuha Samen
     * Student ID: 501292711
     * 
     ******** Project Description ********
     * 
     * Describe in plain English the overall program/program in a paragraph or 2.
     * 
     * The purpose of this program is to give users a fun way to test thier reaction time and goal-keeping skills. 
     * Ultimate Goalie uses the keyboard arrows to allow the player to move around a goalie avatar. The player cannot enter the restricted goal area, this makes the game more challenging.
     * As soccer balls come hurtling towards the goal from the top of the screen, the player must move around to block the ball from entering the goal.
     * The balls spawn at random positions on the x-axis and depending on their location, they either launch straight or curve toward the center of the screen. 
     * When the player collides with a ball, the ball bounces away, and the player gets a point. If the player fails to block a ball, 
     * they lose one of 3 lives. Some balls miss the goal (similar to a real soccer game) and these stray balls dont cause the score or live count to change.
     * The game displays the players score with text and remaining lives using a heart icon.
     * Once all the lives are lost, the game is over. The player is shown their final score and prompted to play again.
     *
     ******** Swing Requirement ********
     * 
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 3 unique components. Be clear to identify in what
     * files and the lines number (just the starting line is fine) that the
     * components are defined on.
     * 
     * My program uses the following swing components:
     * 1 - JButton is used in the Landing class to create a start button. (line 22)  
     * 2 - JPanel is used to make the three different screens: Landing (line 17 on Landing.java), Game (line 44 on Landing.java), and endGame (line 16 on endGame.java).  
     * 3 - JFrame is used to manage the main window, which is passed into the panels and defined below in this class (line 60).
     * 
     ******** 2D Graphics Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 1 JPanel used for drawing something. Be clear to
     * identify in what files and the line numbers that this panel is defined on.
     * 
     * My program uses alot of different 2D graphics. First, in the Game class (line 55) the paintComponent method is used to 
     * draw the field boundaries, soccer balls, player aminations, goals, score, and heart icons on the Game panel. 
     * To draw these I used both images (soccer ball, player, lives) and 2D shapes (goalie box, goal, score) with the Graphics2D class. 
     * The graphics update to show the ball, player movement/animation and the score and remaining lives.
     * This satisfies the requirement of using a JPanel for 2D drawings
     * 
     ******** Event Listener Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least one ActionListener, and there is additionally at least
     * one MouseListener or ActionListener. Be clear to identify in what file
     * and the line numbers that these listeners are defined in.
     * 
     * My program includes multiple event listeners. Firstly, ActionListener is used in the Game class (line 134) to create a delay using a Timer
     * Next, the Landing class also implements an ActionListener (line 28) to detect and respond to the start game button. 
     * Next, the Player class uses KeyListener (line 67) to allow player movement based on input from the arrow keys
     * Both action and key events are used in Ultimate Goalie, satisfying this requirement.
     */

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