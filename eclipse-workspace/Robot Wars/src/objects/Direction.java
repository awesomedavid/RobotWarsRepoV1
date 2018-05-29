package objects;

import core.Utility;


public enum Direction 
{
	NORTH, EAST, SOUTH, WEST;
		
	public static Direction getRandom() {
			return Direction.values()[(Utility.random(Direction.values().length))];
		}

	
	
	
}
