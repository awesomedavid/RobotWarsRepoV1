package display.duo.bar;

import org.newdawn.slick.Color;

import objects.Robot;
import values.CoreValues;
import values.RobotValues;

public class Platingbar extends HorizontalBar {
	
	public Platingbar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
		color = COLOR_PLATING;
	}

	
	public void update()
	{
		current = robot.getCurPlating();
		maximum = robot.getMaxPlating();
		cap = RobotValues.MAXIMUM_PLATING_CAP;

	}
}
