package display.duo.bar;

import org.newdawn.slick.Color;

import objects.Robot;
import values.CoreValues;
import values.RobotValues;

public class Healthbar extends HorizontalBar {
	
	public Healthbar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
		color = COLOR_HEALTH;
	}

	
	public void update()
	{
		current = robot.getCurHealth();
		maximum = robot.getMaxHealth();
		cap = RobotValues.MAXIMUM_HEALTH_CAP;
	}
}
