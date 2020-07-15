// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Matt Csukker, Christian Deepe
// RESOURCES: Computer Science Learning Center

/**
 * This class encapsulates data and behaviors for a Paddle within
 * the brick breaker game.  Once created a Paddle can move in the 
 * x dimension, but retains a fixed y coordinate. Further all Paddle
 * objects have a fixed height of 10 pixels and fixed width of 80 pixels.
 * @author Matt Csukker, Christian Deepe
 */
public class Paddle 
{
	/**
	 * The height of the paddle.
	 */
	private static final int HEIGHT = 10;
	/**
	 * The width of the paddle.
	 */
	private static final int WIDTH = 80;
	/**
	 * The position of the x coordinate on the paddle.
	 */
	private int xPos;
	/**
	 * The position of the y coordinate on the paddle.
	 */
	private int yPos;
	
	/**
	 * Creates a new Paddle object at a specified coordinate location in the model.
	 * @param leftIn - The x position of the top-left corner for the new Paddle.
	 * @param topIn - The y position of the top-left corner for the new Paddle.
	 */
	public Paddle(int leftIn, int topIn)
	{
		xPos = leftIn;
		yPos = topIn;
	}
	
	/**
	 * Retrieves the fixed height of this Paddle object.
	 * @return height of the paddle.
	 */
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Retrieves the x coordinate of the current left side of this Paddle.
	 * @return xPos of the paddle.
	 */
	
	public int getLeft()
	{
		return xPos;
	}
	
	/**
	 * Retrieves the y coordinate of the fixed top of this Paddle.
	 * @return yPos of the paddle.
	 */
	
	public int getTop()
	{
		return yPos;
	}
	
	/**
	 * Retrieves the fixed width of this Paddle object.
	 * @return WIDTH of the paddle.
	 */
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	/**
	 * This method implements a collision detection algorithm to identify whether 
	 * this Paddle is currently being hit by a given Ball object.
	 * @param theBall - The Ball to examine for collision with this Paddle.
	 * @return theBall A valid TouchPosition state representing where theBall is 
	 * intersecting this Paddle. When no collision is detected at all NONE should be 
	 * returned. Otherwise, TOP, LEFT, or RIGHT will be returned corresponding to which 
	 * side of this Paddle is currently being hit by theBall. Note, a value of BOTTOM is 
	 * not possible under standard physics rules for brick breaker and thus is not expected.
	 */
	
	public TouchPosition isTouching(Ball theBall)
	{
		TouchPosition bounce = TouchPosition.NONE;
		if (theBall.getX() > getLeft() && theBall.getX() < getLeft() + WIDTH - 1)
		{
			if (yPos + 2 >= theBall.getY() + 10 && yPos <= theBall.getY() + 10)
			{
				bounce = TouchPosition.TOP;
			}		
			else 
			{
				bounce = TouchPosition.NONE;
				bounce = isTouchingLR(theBall);
			}
		}
		else
		{
			bounce = isTouchingLR(theBall);
		}
		return bounce;
	}
	
	/**
	 * This method implements a collision detection algorithm to identify whether 
	 * this Paddle is currently being hit by a given Ball object.
	 * @param theBall - The Ball to examine for collision with this Paddle.
	 * @return strike A valid TouchPosition state representing where theBall is 
	 * intersecting this Paddle. When no collision is detected at all NONE should be 
	 * returned. Otherwise, TOP, LEFT, or RIGHT will be returned corresponding to which 
	 * side of this Paddle is currently being hit by theBall. Note, a value of BOTTOM is 
	 * not possible under standard physics rules for brick breaker and thus is not expected.
	 */
	public TouchPosition isTouchingLR(Ball theBall)
	{
		TouchPosition strike;	
		if (theBall.getY() + theBall.getRadius() > getTop() && theBall.getY() 
				- theBall.getRadius() < getTop() + HEIGHT)
		{
			if (xPos + 2 >= theBall.getX() + 10 && xPos <= theBall.getX() + 10)
			{
				strike = TouchPosition.LEFT;
			}		
			else if (xPos + WIDTH - 3 <= theBall.getX() - 10 && xPos + WIDTH - 1 >= theBall.getX() - 10)
			{
				strike = TouchPosition.RIGHT;
			}	
			else 
			{
				strike = TouchPosition.NONE;
			}
		}
		else 
		{
			strike = TouchPosition.NONE;
		}
		return strike;	
	}
	
	/**
	 * Updates the state of this Paddle object to correspond to a new x-position for its left-side.
	 * @param newLeft - The new x coordinate for the Paddle's left side. A logical minimum of 0 is 
	 * enforced on the Paddle, so negative values will result in a new position of 0.
	 */
	
	public void moveTo(int newLeft)
	{
		int movePaddle = xPos;
		if (newLeft < 0)
		{
			movePaddle = 0;
		}
		else 
		{
			movePaddle = newLeft;
		}
		xPos = movePaddle;
	}
	
	
	
	
}
