package display.duo.bar;

import org.newdawn.slick.Color;

import objects.Robot;
import values.CoreValues;
import values.RobotValues;

public class Shieldbar extends HorizontalBar {
	
	public Shieldbar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
		color = COLOR_SHIELD;
	}

	
	public void update()
	{
		current = robot.getCurShield();
		maximum = robot.getMaxShield();
		cap = RobotValues.MAXIMUM_SHIELD_CAP;
	}
}
