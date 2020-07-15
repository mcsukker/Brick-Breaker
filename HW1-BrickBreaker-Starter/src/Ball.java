// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Matt Csukker, Christian Deepe
// RESOURCES: Computer Science Learning Center

/**
 * The Ball class encapsulates the basic data and behaviors to model the
 * ball within the brick breaker game. All ball objects have a fixed radius 
 * of 10 pixels.
 * @author Matt Csukker, Christian Deepe 
 *
 */
public class Ball
{
	/**
	 * Dimension of x coordinate.
	 */
	private int xDim;
	/**
	 * Dimension of y coordinate.
	 */
	private int yDim;
	/**
	 * Trajectory of x coordinate.
	 */
	private int xTraj;
	/**
	 * Trajectory of y coordinate.
	 */
	private int yTraj;
	/**
	 * Radius of the ball.
	 */
	private int radius;
	
	/**
	 * Creates a default Ball object with an initial location of (100, 100) and an initial 
	 * trajectory of 3 pixels in both x and y dimensions.
	 */
	public Ball()
	{
		xDim = 100;
		yDim = 100;
		xTraj = 3;
		yTraj = 3;
		radius = 10;	
	}
	/**
	 * Creates a Ball object with a custom initial location and a default trajectory of 3 
	 * pixels in both the x and y dimensions.
	 * @param x The initial x position of this Ball.
	 * @param y The initial y position of this Ball.
	 */
	
	public Ball(int x, int y)
	{
		xDim = x;
		yDim = y;
		radius = 10;
		xTraj = 3;
		yTraj = 3;
	}

	/**
	 * Causes this Ball's current horizontal trajectory to reverse direction by 180 degrees.
	 */
	
	public void bounceHorizontal()
	{
		xTraj = xTraj * -1;
	}

	/**
	 * Causes this Ball's current vertical trajectory to reverse direction by 180 degrees.
	 */
	
	public void bounceVertical()
	{
		yTraj = yTraj * -1;
	}

	/**
	 * Retrieves the radius of this Ball.
	 * @return radius of the ball.
	 */
	
	public int getRadius()
	{
		return radius;
	}

	/**
	 * Retrieves the current x position of this Ball's center.
	 * @return xDim of the ball.
	 */
	
	public int getX()
	{
		return xDim;
	}

	/**
	 * Retrieves the current y position of this Ball's center.
	 * @return yDim of the ball.
	 */
	
	public int getY()
	{
		return yDim;
	}
	
	/**
	 * This mutator method will move the position of this Ball through one timestep in the game 
	 * by applying the x and y trajectory offsets to compute the new center position of this Ball.
	 */
	
	public void moveOnce()
	{
		xDim = xDim + xTraj;
		yDim = yDim + yTraj;
	}
	
	/**
	 * Retrieves a string representation of this Ball formatted as below.
	 *@return stateBall - String describing the state of the ball.
	 */
	
	public java.lang.String toString()
	{
		String stateBall = "Ball at (" + xDim + ", " + yDim + ") moving (" + xTraj + ", " + yTraj + ")";
		return stateBall;		
	}
	
}
