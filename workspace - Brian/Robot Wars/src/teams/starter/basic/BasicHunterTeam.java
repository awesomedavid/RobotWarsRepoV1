package teams.starter.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.Robot;
import objects.Team;
import teams.starter.basic.robots.BasicHunter;

public class BasicHunterTeam extends BasicTeam
{
	public BasicHunterTeam(int id) {
		super(id);
		setName("Basic - Hunters");

	}
	
	public Robot createRobotOne(int x, int y) throws SlickException
	{
		return new BasicHunter(x, y, getID(), 1);
	}

	protected Robot createRobotTwo(int x, int y) throws SlickException {
		return new BasicHunter(x, y, getID(), 2);
	}

	protected Robot createRobotThree(int x, int y) throws SlickException {
		return new BasicHunter(x, y, getID(), 3);
	}

	protected Robot createRobotFour(int x, int y) throws SlickException {
		return new BasicHunter(x, y, getID(), 4);
	}

	public void strategy()
	{
		
	}
	
	public void setup() 
	{
		
	}
}
