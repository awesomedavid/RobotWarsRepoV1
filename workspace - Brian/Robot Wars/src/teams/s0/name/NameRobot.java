package teams.s0.name;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import item.Item;
import item.StoneBlock;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class NameRobot extends Robot 
{
	
	public NameRobot(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
	}
	
	// Action method is called when a robot has no current action
	public void action()
	{		

	}

	
	// A supporting method for the basic robot
	public void turnRandom()
	{	
		int r = Utility.random(4);
		
		switch(r)
		{
		case 0:
			turnLeft();
			break;
		case 1:
			turnRight();
			break;
		case 2:
			turnAround();
			break;
		}
	}

	
	// This method is called when the robot dies
	public void onDeath()
	{
		
	}
	
	public void display(Graphics g) {
		
	}
}
