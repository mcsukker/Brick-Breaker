// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Matt Csukker, Christian Deepe
// RESOURCES: Computer Science Learning Center

import javafx.scene.paint.Color;

/**
 * This class models a basic game level in the brick breaker game. It allows for
 * the creation of new levels, simulation of game steps, and retrieval, of current
 * state information.  All levels contain a grid of 5 rows and 7 columns of Bricks
 * (some of which may have been broken), a single Ball, and a single Paddle.
 * @author Matt Csukker, Christian Deepe
 *
 */
public class Level 
{
	/**
	 * Creates the ball for the level.
	 */
	private Ball theBall;
	/**
	 * Creates the Paddle for the level.
	 */
	private Paddle thePaddle;
	/**
	 * Creates the brick for the level.
	 */
	private Brick[][] theBrick = new Brick[5][7];
	/**
	 * The width of the level.
	 */
	private int lWidth;
	/**
	 * The height of the level.
	 */
	private int lHeight;
	/**
	 * Starting Y location of first brick in standard level.
	 */
	private final int startBrickY = 40;
	/**
	 * Starting X location of first brick in standard level.
	 */
	private final int startBrickX = 10;
	/**
	 * Next Y location of brick in standard level.
	 */
	private final int nextBrickY = 25;
	/**
	 * Next X location of brick in standard level.
	 */
	private final int nextBrickX = 55;
	/**
	 * Sets height of paddle.
	 */
	private final int paddlePosition = 20;
	/**
	 * The number of bricks in the array.
	 */
	private final int brickAmount = 35;
	/**
	 * Creates a default level with a given dimension.
	 * @param widthIn The logical width of the new level in pixels. 
	 * @param heightIn The logical height of the new level in pixels.
	 */
	public Level(int widthIn, int heightIn)
	{
		lWidth = widthIn;
		lHeight = heightIn;
		thePaddle = new Paddle(0, lHeight - paddlePosition);
		for (int row = 0; row < theBrick.length; row++)
		{
			for (int col = 0; col < theBrick[0].length; col++)
			{
				theBrick[row][col] = new Brick(startBrickY + nextBrickY * row, startBrickX
						+ nextBrickX * col);
			}
		}
		theBall = new Ball(lWidth / 2, lHeight / 2);
	}
	
	/**
	 * Creates a level with a given dimension and specified brick configuration.
	 * @param widthIn The logical width of the new level in pixels.
	 * @param heightIn The logical height of the new level in pixels.
	 * @param brickConfig The configuration array specifying the grid of Bricks to 
	 * use in this new level.
	 */
	public Level(int widthIn, int heightIn, java.lang.String[] brickConfig)
	{
		lWidth = widthIn;  
		lHeight = heightIn;  
		thePaddle = new Paddle(0, lHeight - paddlePosition);
		theBall = new Ball(lWidth / 2, lHeight / 2);
		for (int row = 0; row < theBrick.length; row++)
		{
			for (int col = 0; col < theBrick[0].length; col++)
			{
				if (brickConfig[row].charAt(col) == '*')
				{
				
					theBrick[row][col] = new Brick(startBrickY + nextBrickY * row, startBrickX
						+ nextBrickX * col, -1);
				}
				else 
				{
					Brick test = new Brick(startBrickY + nextBrickY * row, startBrickX
							+ nextBrickX * col, 
							Character.getNumericValue(brickConfig[row].charAt(col)));
					System.out.println("" + test.getColor());
					theBrick[row][col] = new Brick(startBrickY + nextBrickY * row, startBrickX
							+ nextBrickX * col,
							Character.getNumericValue(brickConfig[row].charAt(col)));
				}
			}
		}
		
	}
	
	/**
	 * Retrieves the Ball object in this Level.
	 * @return theBall in the level.
	 */
	public Ball getBall()
	{
	
		return theBall;
	}
	
	/**
	 * Retrieves a 5 by 7 array of Brick objects corresponding to the current 
	 * grid of Bricks in the model. 
	 * @return theBrick the array of the bricks in the level.
	 */
	public Brick[][] getBricks()
	{
		return theBrick;
	}
	/**
	 * Retrieves state information about the game's current progress based on the
	 * last simulated step.
	 * @return progress of the level.
	 */
	public GameState getGameStatus()
	{
		GameState progress;
		int bricksBroken = 0;
		for (int row = 0; row < theBrick.length; row++)
		{
			for (int col = 0; col < theBrick[0].length; col++)
			{
				
				if (theBrick[row][col].getColor() == Color.BLACK 
						|| theBrick[row][col].getColor() == Color.WHITE)
				{
					bricksBroken++;
				}
				else 
				{
					bricksBroken--;
				}
			}
		}
		if (bricksBroken == brickAmount)
		{
			progress = GameState.WON;
		}			
		else if (theBall.getY() >= lHeight - theBall.getRadius())
		{
			progress = GameState.LOST;
		}
		else 
		{
			progress = GameState.PLAYING;
		}
		
		return progress;
	}
	
	/**
	 * Retrieves the Paddle object in this Level.
     *@return thePaddle	of the Level.
	 */
	public Paddle getPaddle()
	{
		return thePaddle;
	}

	/**
	 * This method updates this level's model data based on simulating one timestep
	 * in the game.
	 */
	public void updateOneStep()
	{
		TouchPosition paddle;
		theBall.moveOnce();
		if (theBall.getX() + 10 >= lWidth - 1 || theBall.getX() <= 10)
		{
			theBall.bounceHorizontal();	
		}
		if (theBall.getY() <= 10)
		{
			theBall.bounceVertical();	
		}
		paddle = thePaddle.isTouching(theBall);
		if (paddle == TouchPosition.TOP)
		{
			theBall.bounceVertical();
		}
		else if (paddle == TouchPosition.LEFT)
		{
			theBall.bounceHorizontal();
		}
		else if (paddle == TouchPosition.RIGHT)
		{
			theBall.bounceHorizontal();
		}
		updateOneStepBrick();
		getGameStatus();
	}
	/**
	 * This helper method runs the Brick section of the updateOneStep method.
	 */
	public void updateOneStepBrick()
	{
		TouchPosition brick;
		for (int row = 0; row < theBrick.length; row++)
		{
			for (int col = 0; col < theBrick[row].length; col++)
			{
				if (theBrick[row][col].getColor() == Color.WHITE)
				{
					brick = TouchPosition.NONE;
				}
				else
				{
					brick = theBrick[row][col].isTouching(theBall);	
				
					if (brick == TouchPosition.TOP)
					{
						theBall.bounceVertical();
						theBrick[row][col].hit();
					}
					else if (brick == TouchPosition.BOTTOM)
					{
						theBall.bounceVertical();
						theBrick[row][col].hit();
					}
					else if (brick == TouchPosition.LEFT)
					{
						theBall.bounceHorizontal();
						theBrick[row][col].hit();
					}
					else if (brick == TouchPosition.RIGHT)
					{
						theBall.bounceHorizontal();
						theBrick[row][col].hit();
					}
				}
			}
		}
	}
	


}
