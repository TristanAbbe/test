import java.util.Random;
/**
 * The `GhostRider` class represents a specialized robot called GhostRider that extends the base Robot class.
 * GhostRider has the ability to move forward and backward while avoiding obstacles and the world's borders.
 *
 * This class provides methods and attributes specific to the GhostRider robot's movement behavior.
 * It keeps track of its steps, maximum steps, and whether it is currently moving forward or backward.
 * GhostRider also calculates its maximum step count based on its orientation.
 *
 * @author Tristan ABBE
 * @version 03/11/2023
 */
public class GhostRider extends Robot {
    private int originalX;
    private int originalY;
    private int steps;
    private int maxSteps;
    private int reset;
    private boolean movingForward;
    
    /**
     * Creates a new GhostRider instance with the specified initial position and world.
     *
     * @param x     The initial x-coordinate of GhostRider.
     * @param y     The initial y-coordinate of GhostRider.
     * @param world The world in which GhostRider exists.
     */
    public GhostRider(int x, int y,World world) {
        
        super("GhostRider", "black", x, y, world);
        originalX = x;
        originalY = y;
        steps = 0;
        reset = 0;
        movingForward = true;
    }
    
    /**
     * Calculates and sets the maximum number of steps that GhostRider can take based on its orientation.
     */
    public void maxStep()
    {
            switch (orientation) {
                case 0: // south
                    maxSteps = MAX_POSITION - originalY;

                    break;
                case 1: // west
                    maxSteps = originalX - MIN_POSITION;
                    break;
                case 2: // north
                    maxSteps = originalY- MIN_POSITION;
                    break;
                case 3: // East
                    maxSteps = MAX_POSITION - originalX;
                    break;
            }        
    }
    
    /**
     * Moves GhostRider forward or backward, depending on its current orientation and the available space.
     * If the robot reaches the world's borders, it changes its direction.
     */

    @Override
    public void move() {
        maxStep();
        System.out.println("Le Ghost-Rider est en mouvement");
        if (movingForward) {
            int orientation = getOrientation();
            int newX = getXPosition();
            int newY = getYPosition();
            switch (orientation) {
                case 0: // south
                    newY++;
                    break;
                case 1: // west
                    newX--;
                    break;
                case 2: // north
                    newY--;
                    break;
                case 3: // East
                    newX++;
                    break;
            }

            if (getWorld().isSquareAvailable(newX, newY) && steps < maxSteps) {
                setMoveX(newX);
                setMoveY(newY);
                draw();
                steps++;
            }
            else if (getXPosition() == MIN_POSITION || getYPosition() == MIN_POSITION || getXPosition() == MAX_POSITION || getYPosition() == MAX_POSITION){
                movingForward = false;
                steps = 0;
            }else{
                System.out.println("Je suis bloquer ! mais sur l'aller");
            }
        } else {
            int newX = getXPosition();
            int newY = getYPosition();
            int orientation = (getOrientation() + 2) % 4; // Go backward (opposite direction)

            switch (orientation) {
                case 0: // south
                    newY++;
                    break;
                case 1: // west
                    newX--;
                    break;
                case 2: // north
                    newY--;
                    break;
                case 3: // East
                    newX++;
                    break;
            }

            if (getWorld().isSquareAvailable(newX, newY) && steps < maxSteps) {
                setMoveX(newX);
                setMoveY(newY);
                draw();
                steps++;
            } else if (getXPosition() == originalX && getYPosition() == originalY) {
                movingForward = true;
                steps = 0;
                int newOrientation = (getOrientation() + 1) % 4; // Rotate to the right (clockwise)
                setOrientation(newOrientation);
            }else {
                System.out.println("Je suis bloquer ! mais sur le retour ");
            }
            
        }
    }
}
