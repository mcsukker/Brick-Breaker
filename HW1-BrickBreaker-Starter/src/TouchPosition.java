// COURSE: CSCI1620
// TERM: Spring 2019
// 
// NAME: Dr. Dorn
// RESOURCES: No outside resources were used in the creation of this enum.

/////////////////////////////////////////////////
//  DO NOT EDIT THIS FILE.  CHANGING THIS CODE
//  IS NOT REQUIRED BY THE ASSIGNMENT.
/////////////////////////////////////////////////

/**
 * This enumerated type represents the possible locations where two objects in the
 * game are touching one another.
 * 
 * <P>THIS ENUM DEFINITION IS PROVIDED AND SHOULD NOT BE EDITED.
 * 
 * @author bdorn
 *
 */
public enum TouchPosition
{
	/**
	 * Used to signal that the Ball has collided with the top of another object.
	 */
	TOP,
	
	/**
	 * Used to signal that the Ball has collided with the bottom of another object.
	 */
	BOTTOM,
	
	/**
	 * Used to signal that the Ball has collided with the left side of another object.
	 */
	LEFT,
	
	/**
	 * Used to signal that the Ball has collided with the right side of another object.
	 */
	RIGHT,
	
	/**
	 * Used to signal that the Ball has not collided with another object at this time.
	 */
	NONE
}
