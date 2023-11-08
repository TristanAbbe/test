import java.util.ArrayList;
import java.io.*;
import java.lang.*;
import java.util.Scanner;

/**
 * The `World` class represents a virtual world where robots can exist and interact.
 * It keeps track of robots and provides methods to manage and simulate robot movements.
 *
 * The world allows adding, removing, and moving robots and provides features to check the availability
 * of a square and determine the number of robots in the world.
 *
 * @author Tristan ABBE
 * @version 03/11/2023
 */
public class World
{
   // Our world is described as an array list of robots
    public ArrayList<Robot> robotsL;
   // We want to give the possiblity to the user to set a maximum number of robots
    private int maxRobots; 
    

    /**
     * Constructor for objects of class World
     */
    public World()
    {
        robotsL = new ArrayList<Robot>();
        
        maxRobots = 10;
    }

    /**
     * Add a new robot to the world.
     *
     * @param robot The new robot to add.
     */
    public void addRobots(Robot robot)
    {
        if(numberOfRobots()<maxRobots){
            robotsL.add(robot);
        }
        
        else{
            System.out.println("Le nombre de robots maximal est atteint.");
            System.exit(0);
        }
        
    }
    
    /**
     * Remove a robot from the world.
     *
     * @param robot The robot to remove.
     */
    public void delRobots(Robot robot)
    {
        robotsL.remove(robot);
    }
    
    /**
     * Add a predefined set of robots to the world.
     */
    public void addAllRobot(){
                    robotsL.add(new GhostRider(8, 8, this));
                    robotsL.add(new GhostRider(4, 7, this));
                    robotsL.add(new GhostRider(5, 2, this));
    }
    
    /**
     * Add a robot to the world at a specified position.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public void addRobotInWorld( int x, int y)
    {       
        if (!isRobotAtPosition(x,y)){
            if (numberOfRobots()<maxRobots){
               System.out.println("\n1-GhostRider");
               Scanner keyboard = new Scanner(System.in);
               int choice = keyboard.nextInt();
               
               switch(choice)
               {
                    case 1 : robotsL.add(new GhostRider(x, y, this));break;
                    default : System.out.print("Cette option n'est pas disponible");
                }
            }
            else
            {
                System.out.print("The world of robots is full");
            }
        }
        else
        {
            System.out.print("There is already a robot at this place");
        }
    }
    
    /**
     * Display the actually number of robots
     * @return The number of robots
    */
    //Method  to return the number of robots in the world (=the size of the array list)
    public int numberOfRobots(){
        return robotsL.size();
    }    
    
    /**
     * Move all robots in the world using their individual `move()` methods.
     */
    // The method moveAll()/automaticMovements(int nb) are our two methods to check is our robot are moving great and if there is no game over
    // We can comare these two methods to the test that user could do
    public void moveAll(){
        for (Robot robo : robotsL)
        {
            robo.move();
            try {
            // thread to sleep for 1000 milliseconds
                Thread.sleep(500);
            } 
            catch (Exception e) 
            {
                System.out.println("Erreur");
            }  
        }
    }
    
    /**
     * Check if there is a robot at a specific position in the world.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return `true` if there is a robot at the specified position, `false` otherwise.
     */
    public boolean isRobotAtPosition(int x, int y)
    {     
        
        for (Robot robo : robotsL)
        {
            if (robo.getXPosition() == x && robo.getYPosition() == y)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Simulate automatic movements for a specified number of iterations.
     *
     * @param nb The number of iterations for automatic movements.
     */    
    public void autoMove(int nb)
    {
        for (int i=0; i<nb; i++)
        {
            moveAll();         
        }
    }
    
    
    /**
     * Check if a square is available based on its coordinates.
     *
     * @param X The x-coordinate of the square.
     * @param Y The y-coordinate of the square.
     * @return `true` if the square is available and within the world's bounds, `false` otherwise.
     */
    public boolean isSquareAvailable(int X, int Y)
    {
    if (X <= Robot.getMaxPosition() && Y <= Robot.getMaxPosition() && X >= Robot.getMinPosition() && Y >= Robot.getMinPosition() && !isRobotAtPosition(X, Y)) 
        {
        return true;
        }
        else 
        {
        return false;
        }
    }
    
}