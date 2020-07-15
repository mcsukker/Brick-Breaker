// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Matt Csukker, Christian Deepe
// RESOURCES: Computer Science Learning Center

import javafx.scene.paint.Color;
/**
 * This class is used to store state information corresponding to a single Brick within
 * the brick breaker game model. It provides basic information about the Brick's position
 * as well as information about the current strength of a Brick. All Bricks in brick breaker
 * have a height of 20 and a width of 50, regardless of state information.
 * @author Matt Csukker, Christian Deepe
 *
 */
public class Brick 
{
	/**
	 * Final height of brick.
	 */
	private static final int HEIGHT = 20;
	/**
	 * Final width of brick.
	 */
	private static final int WIDTH = 50;
	/**
	 * The x position of the left side of this Brick in the model.
	 */
	private int xCoor;
	/**
	 * The y position of the top of this Brick in the model.
	 */
	private int yCoor;
	/**
	 * The initial strength of this Brick. A value greater than zero represents that this Brick will break 
	 * after that number of hits. A value of 0 represents a "non-brick" or empty space in the model. 
	 * A value of -1 represents that this Brick cannot be broken at all,
	 * regardless of the number of hits.
	 */
	private int strength;
	
	/**
	 * Creates a basic Brick object at a specified position.
	 * @param topIn The y position of the top of this Brick in the model. 
	 * @param leftIn The x position of the left side of this Brick in the model.
	 */
	public Brick(int topIn, int leftIn)
	{
		yCoor = topIn;
		xCoor = leftIn;
		strength = 3;
	}
	
	/**
	 * Creates a custom Brick object at a specified position with a specified strength.
	 * @param topIn The y position of the top of this Brick in the model.
	 * @param leftIn The x position of the left side of this Brick in the model.
	 * @param hitsIn The initial strength of this Brick. A value greater than zero represents 
	 * that this Brick will break after that number of hits. A value of 0 represents a "non-brick" 
	 * or empty space in the model. A value of -1 represents that this Brick cannot be broken at all, 
	 * regardless of the number of hits.
	 */
	
	public Brick(int topIn, int leftIn, int hitsIn)
	{
		yCoor = topIn;
		xCoor = leftIn;
		strength = hitsIn;		
	}
	
	/**
	 * Retrieves the y coordinate of the top of this Brick.
	 * @return yCoor of Brick.
	 */
	
	public int getTop()
	{
		return yCoor;
	}
	
	/**
	 * Retrieves the x coordinate of the top of this Brick.
	 * @return xCoor of Brick.
	 */
	
	public int getLeft()
	{
		return xCoor;
	}
	
	/**
	 * Retrieves the width of this Brick.
	 * @return WIDTH of Brick.
	 */
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	/**
	 * Retrieves the height of this Brick.
	 * @return HEIGHT of Brick.
	 */
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * The current color to represent this Brick's breakability state.
	 * @return health of Brick.
	 */
	public javafx.scene.paint.Color getColor()
	{
		Color health;
		if (strength <= -1)
		{
			health = Color.BLACK;
		}
		else if (strength == 0)
		{
			health = Color.WHITE;
		}
		else if (strength == 1)
		{
			health = Color.RED;
		}
		else if (strength == 2)
		{
			health = Color.YELLOW;
		}
		else 
		{
			health = Color.GREEN;
		}
		return health;
	}
	/**
	 * This mutator method will update this Brick's state data to account
	 * for being hit by the Ball once.
	 * @return destroyed - whether the brick is destroyed or not.
	 */
	public boolean hit()
	{
		boolean destroyed = false;
		if (strength == 1)
		{
			strength--;
			destroyed = true;
		}
		else if (strength > 1)
		{
			strength--;
			destroyed = false;
		}
		return destroyed;
	}
	/**
	 * This method implements a collision detection algorithm to identify whether this Brick is currently
	 * being hit by a given Ball object. It will produce a return value to signal which side, if any, is
	 * currently being hit.
	 * @return A valid TouchPosition state representing where theBall is intersecting this Brick. When no 
	 * collision is detected at all or this Brick is already broken, NONE should be returned. Otherwise, 
	 * TOP, BOTTOM, LEFT, or RIGHT will be returned corresponding to which side of this Brick is currently 
	 * being hit by theBall.
	 * @param theBall The ball to examine for collision with this Brick.
	 */
	public TouchPosition isTouching(Ball theBall)
	{
		TouchPosition point;
		if (theBall.getX() >= getLeft() && theBall.getX() <= getLeft() + WIDTH)
		{
			if (yCoor + 2 >= theBall.getY() + 10 && yCoor <= theBall.getY() + 10) 
			{
				point = TouchPosition.TOP;
			}
			else if (getTop() + HEIGHT - 3 <= theBall.getY() - 10 && getTop()
					+ HEIGHT - 1 >= theBall.getY() - 10) 
			{
				point = TouchPosition.BOTTOM;					
			}
			else 
			{
				point = TouchPosition.NONE;
				point = isTouchingSides(theBall);
			}		
		}
		else
		{
			point = isTouchingSides(theBall);
		}
		return point;
	}
	
	/**
	 * This method implements a collision detection algorithm to identify whether this Brick is currently
	 * being hit by a given Ball object. It will produce a return value to signal which side, if any, is
	 * currently being hit.
	 * @param theBall The ball to examine for collision with this Brick.
	 * @return dot A valid TouchPosition state representing where theBall is intersecting this Brick. When no 
	 * collision is detected at all or this Brick is already broken, NONE should be returned. Otherwise, 
	 * LEFT, or RIGHT will be returned corresponding to which side of this Brick is currently 
	 * being hit by theBall.
	 */
	public TouchPosition isTouchingSides(Ball theBall)
	{
		TouchPosition dot;
		if (theBall.getY() + 10 >= getTop() && theBall.getY() - 10 <= getTop() + HEIGHT)
		{
			if (xCoor + 2 >= theBall.getX() + 10 && xCoor <= theBall.getX() + 10)
			{
				dot = TouchPosition.LEFT;
			}
			else if (getLeft() + WIDTH - 3 <= theBall.getX() - 10 && getLeft()
					+ WIDTH - 1 >= theBall.getX() - 10)
			{
				dot = TouchPosition.RIGHT;				
			}
			else 
			{
				dot = TouchPosition.NONE;
			}
		}
		else
		{
			dot = TouchPosition.NONE;
		}
		return dot;
	}
	/**
	 * Retrieves a String representation of this Brick's current object state.
	 * @return String describing state of brick.
	 */
	public java.lang.String toString()
	{
		String brickState = "Brick at (" + xCoor + ", " + yCoor + ", " + (xCoor - WIDTH - 1)  
				+ ", " + (yCoor - HEIGHT - 1) + ")";
		return brickState;
	}
	
}
