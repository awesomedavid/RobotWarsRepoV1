package display.duo.bar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Utility;
import display.Camera;
import effects.Stun;
import files.Fonts;
import objects.Robot;
import values.CoreValues;
import values.DisplayValues;
import values.RobotValues;
import values.WeaponValues;


public class OverclockBar extends StatusBar implements DisplayValues
{	
	float height;
	
	final Color COLOR_LATENCY_BACKGROUND = new Color(60, 60, 60, 150);
	
	public OverclockBar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
		height = OVERCLOCK_BAR_HEIGHT - indent * 2;
		color = COLOR_OVERCLOCK;
	}
	
	public void render(Graphics g)
	{
		if(maximum > 0)
		{
			// Maximum
			g.setColor(COLOR_LATENCY_BACKGROUND);
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), OVERCLOCK_BAR_WIDTH, -height * scale());
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), OVERCLOCK_BAR_WIDTH,  -height * scale());

			// Current
			if(current > robot.getOverclockLevel())
			{
				g.setColor(new Color(50, 150 - Utility.random(55), 255));
			}
			else
			{
				g.setColor(color.brighter(percentage()));
			}
			
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), OVERCLOCK_BAR_WIDTH, -height * percentage() * scale());
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(),OVERCLOCK_BAR_WIDTH,  -height * percentage() * scale());		
		
			

			// Level
			
			g.setColor(new Color(200,200,200));
			g.fillRect(xPos + Camera.getX() + indent, 
					   yPos - height * (robot.getOverclockLevel() / cap) * scale() + Camera.getY() - 1, 
					   OVERCLOCK_BAR_WIDTH, 3);
			
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, 
					   yPos - height * (robot.getOverclockLevel() / cap) * scale() + Camera.getY() - 1, 
					   OVERCLOCK_BAR_WIDTH, 3);
		
		}	
	}
	
	public float percentage()
	{
		return Math.min(1, current / maximum);
	}
	
	public void update()
	{
		current = robot.getRecentLatency();
		maximum = RobotValues.MAXIMUM_LATENCY;
		cap = RobotValues.MAXIMUM_LATENCY;
	}
	
	
}
