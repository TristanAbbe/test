import java.util.Random;
/**
 * The `Robot` class represents a robot in the world. It can move, turn, and interact with the environment.
 *
 * This class provides essential methods and attributes for controlling a robot's position, orientation, and appearance.
 *
 * @author Tristan ABBE
 * @version (03/11/2023)
 */
public class Robot
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    protected int xPos;
    protected int yPos;
    protected int orientation = 0;
    private static int numero = 0 ;
    protected static int MIN_POSITION = 0;
    protected static int MAX_POSITION = 10;
    protected static int MIN_NAME_LENGTH = 3 ; 
    protected String name;
    protected CanvasRobot canvasR;
    protected String colorBody ;
    protected boolean isVisible;
    private static Colour color; 
    private World world;
    /**
     * Constructeur d'objets de classe Robot
     */
    public Robot(String name, String color)
    {
        setName(name);
        setColourBody(color.toUpperCase());
        canvasR = new CanvasRobot(this.colorBody);
        canvasR.drawRobot(xPos,yPos);
        orientation=0;
    }
    
    public Robot(String name, String color,int x , int y, World w){ 
        setName(name);
        setMoveX(x);
        setMoveY(y);
        color = color.toUpperCase();
        setColourBody(color);
        randomDirection();
        canvasR = new CanvasRobot(this.colorBody);
        canvasR.drawRobot(xPos,yPos);
        world = w;
    }
    
    /**
     * Move the robot within the world according to its logic.
     */
    public void move(){
        
    }
    
    /**
     * Turn the robot's orientation to the next direction.
     */
    public void turn() 
    {

    }
    
    /**
     * Set the X position of the robot. Ensure the position is within the world bounds.
     *
     * @param x The new X position for the robot.
     */
    public void setMoveX (int x) 
    {
        if (x > MAX_POSITION)
            xPos = MAX_POSITION;
        else if (x < MIN_POSITION)
            xPos = MIN_POSITION;
        else
            xPos = x;
    }
    
    /**
     * Set the Y position of the robot. Ensure the position is within the world bounds.
     *
     * @param y The new Y position for the robot.
     */
        public void setMoveY (int y) 
    {
        if (y > MAX_POSITION)
            yPos = MAX_POSITION;
        else if (y < MIN_POSITION)
            yPos = MIN_POSITION;
        else
            yPos = y;
    }
    
    
    /**
     * Get the X position of the robot.
     *
     * @return The X position of the robot.
     */
    public int getXPosition(){
        return xPos;
    }
    
    
    /**
     * Get the Y position of the robot.
     *
     * @return The Y position of the robot.
     */
    public int getYPosition(){
        return yPos ;
    }
    
    /**
     * Set the visibility state of the robot.
     *
     * @param state The new visibility state for the robot.
     */
    public void isVisible (boolean state)
    {
         isVisible=state;
    }
    
    /**
     * Get the visibility state of the robot.
     *
     * @return The visibility state of the robot.
     */    
    public boolean getVisible()
    {
        return isVisible;
    }
    
    /**
     * Get the world where the robot is placed.
     *
     * @return The world where the robot is placed.
     */
    public World getWorld()
    {
        return world;
    }
    
    
    /**
     * Get the current orientation of the robot.
     *
     * @return The orientation of the robot.
     */
    public int getOrientation(){
        return orientation;
    }
    
    /**
    * Set the orientation of the robot to one of the four possible directions.
    *
    * @param direction The new orientation for the robot. Valid values are 0 (North), 1 (East), 2 (South), or 3 (West).
    */
    public void setOrientation(int dir)
    {
        if (dir <=3 && dir >=0)
        {
        orientation=dir;
        }
    }
    
    /**
     * Set the name of the robot. If the provided name is less than the minimum length (3 characters),
     * a default name in the format "iRobotX" is assigned, where X is an incremented number.
     *
     * @param name The new name for the robot.
     */
    public void setName(String name){
        name = name.trim() ;
        if ( name.length() >= MIN_NAME_LENGTH ){
        name = name ;    
        }
        else {
        numero++ ;    
        String tempname= "iRobot"+ numero ;
        name = tempname ;
        }
    }
    
    
    /**
     * Set the color of the robot's body. If the provided color is not one of the predefined colors,
     * the default color is set to "BLUE".
     *
     * @param colorBody The new color for the robot's body.
     */
    public void setColourBody(String couleurBody)
    {
        couleurBody = couleurBody.toUpperCase();
        if (contiens(couleurBody) == true)
        {    
            this.colorBody = couleurBody; 
        }
        else
        {
            this.colorBody = "BLUE"; 
            
        }
    }
    
    /**
    * Check if a given string is present in the list of valid color names.
    *
    * @param test The color name to check.
    * @return True if the given color name is valid and present in the list of valid color names, false otherwise.
    */
    public static boolean contiens(String test) 
    {
        for (Colour c : Colour.values()) 
        { 
            if (c.name().equals(test)) 
            { 
                return true; 
            } 
        } 
        return false; 
    }
    
    /**
     * Draw the robot on the canvas at its current position.
     */
    public void draw()
    {
        canvasR.drawRobot(xPos,yPos);
    }
    
    /**
     * Check if the robot is at the border of the world.
     *
     * @param value The value to check against the maximum and minimum positions.
     * @return True if the robot's position matches the maximum or minimum position, indicating it's at the border; false otherwise.
     */    
    public boolean touchBorder(int value)
    {
        if (value == MAX_POSITION || value == MIN_POSITION)
            return true;
        else
            return false;
    }
    
    /**
    * Get the minimum position allowed in the world.
     *
     * @return The minimum position value allowed in the world.
     */
    public static int getMinPosition()
    {
        return MIN_POSITION;
    }
    
    /**
     * Get the maximum position allowed in the world.
     *
     * @return The maximum position value allowed in the world.
     */
    public static int getMaxPosition()
    {
        return MAX_POSITION;
    }
    /**
     * Set the orientation of the robot to a random direction.
     *
     * This method randomly sets the orientation of the robot to one of the four cardinal directions (North, South, East, or West).
     */
    public void randomDirection (){
         Random rand = new Random(); 
         int max = rand.nextInt(3);
         this.setOrientation(max);
    }
    
    /**
     * Rotate the robot to the left (counter-clockwise).
     *
     * This method rotates the robot's orientation to the left, changing its direction to the next counter-clockwise cardinal direction (e.g., North to West).
     */
    public void tournerD() {
        orientation = (orientation + 1) % 4;
        draw();
    }
    
    /**
     * Rotate the robot to the left (counter-clockwise).
     *
     * This method rotates the robot's orientation to the left, changing its direction to the next counter-clockwise cardinal direction (e.g., North to West).
     */
    public void tournerG() {
        orientation--;
        if (orientation == 0) orientation = 4;
        draw();
    }
    
    /**
     * Move the robot forward in its current orientation.
     *
     * This method moves the robot one step forward in its current orientation. The robot's position is updated based on its direction (North, South, East, or West) while checking for any obstacles that may block its path. If the robot encounters an obstacle or reaches the world's boundaries, it will not move.
     */
    public void avancer() {
        int MAX_POSITION = 10;
        int MIN_POSITION = 0;  
            switch(orientation) {
                case 0 : if (getWorld().isSquareAvailable(this.xPos+1, this.yPos)){
                    this.xPos++;break;
                }else{
                    System.out.println("Je suis bloquer !");
                }
                case 1 :if (getWorld().isSquareAvailable(this.xPos, this.yPos+1)){
                    this.yPos++;break;
                }else{
                    System.out.println("Je suis bloquer !");
                }
                case 2 :if (getWorld().isSquareAvailable(this.xPos-1, this.yPos)){
                    this.yPos--;break;
                } else{
                    System.out.println("Je suis bloquer !");
                }
                case 3 :if (getWorld().isSquareAvailable(this.xPos, this.yPos-1)){
                    this.yPos--;break;
                }else{
                    System.out.println("Je suis bloquer !");
                }
                default : break;
        }
        draw();
    }

}