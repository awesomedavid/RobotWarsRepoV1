package teams.s0.FD;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import core.Utility;
import objects.Point;
import objects.Robot;
import objects.Team;
import teams.s0.FD.FDRunner;
import teams.s0.FD.FDShooter;

public class FDTeam extends Team
{
	Color myColor;
	public FDTeam(int id) {
		super(id);
		setName("FDTeam");
		myColor = new Color(102, 160, 255);
	}

	public Color getColor() 
	{
		return myColor;
	}
	
	public Robot createRobotOne(int x, int y) throws SlickException
	{
		return new FDRunner(x, y, getID(), 1);
	}

	protected Robot createRobotTwo(int x, int y) throws SlickException {
		return new FDShooter(x, y, getID(), 2);
	}

	protected Robot createRobotThree(int x, int y) throws SlickException {
		return new FDShooter(x, y, getID(), 3);
	}

	protected Robot createRobotFour(int x, int y) throws SlickException {
		return new FDShooter(x, y, getID(), 4);
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strategy() {
		// TODO Auto-generated method stub
		
	}
	
	public static int getDistance(Point p1, Point p2) {
		return (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
	}
}
