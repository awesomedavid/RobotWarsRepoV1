package teams.s0.name;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.Robot;
import objects.Team;

public class NameTeam extends Team
{
	Color myColor;
	
	public NameTeam(int id) {
		super(id);
		setName("MyTeamName");
		myColor = new Color(Utility.random(255), Utility.random(255), Utility.random(255));
	}

	public Color getColor() 
	{
		return myColor;
	}
	
	public Robot createRobotOne(int x, int y) throws SlickException
	{
		return new NameRobotLaser(x, y, getID(), 1);
	}

	protected Robot createRobotTwo(int x, int y) throws SlickException {
		return new NameRobotLaser(x, y, getID(), 2);
	}

	protected Robot createRobotThree(int x, int y) throws SlickException {
		return new NameRobotLaser(x, y, getID(), 3);
	}

	protected Robot createRobotFour(int x, int y) throws SlickException {
		return new NameRobotLaser(x, y, getID(), 4);
	}

	public void strategy() 
	{
		
	}
	
	public void setup() 
	{
		
	}
}
