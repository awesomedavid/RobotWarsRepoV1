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


public class Heatbar extends StatusBar implements DisplayValues
{	
	float height;
	
	final Color COLOR_HEAT_BACKGROUND = new Color(60, 60, 60, 150);
	
	public Heatbar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
		height = HEAT_BAR_HEIGHT - indent * 2;
		color = COLOR_HEAT;
	}
	
	public void render(Graphics g)
	{
		if(maximum > 0)
		{
			// Maximum
			g.setColor(COLOR_HEAT_BACKGROUND);
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), HEAT_BAR_WIDTH, -height * scale());
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), HEAT_BAR_WIDTH,  -height * scale());

			// Current
			if( percentage()  == 1)
			{
				g.setColor(new Color(255, 150 - Utility.random(55), 0));
			}
			else
			{
				g.setColor(color.brighter(percentage()));
			}
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), HEAT_BAR_WIDTH, -height * percentage() * scale());
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(),HEAT_BAR_WIDTH,  -height * percentage() * scale());
		
			if(robot.hasEffect(Stun.class))
			{
				g.setFont(Fonts.arialblack36);
				int w = Fonts.arialblack36.getWidth("!");
				int h = Fonts.arialblack36.getHeight("!");
				
				float x = xPos + Camera.getX() + HEAT_BAR_WIDTH/2 + w/2 - 3;
				float y = yPos + Camera.getY() - HEAT_BAR_HEIGHT / 2 - h/2 + 4;
				g.setColor(Color.black);
				g.drawString("!", x+2, y+2);
				
				g.setColor(new Color(255, Utility.random(55), 0));
				g.drawString("!", x, y );

			}
		
		}	
	}
	
	public float percentage()
	{
		return Math.min(1, current / maximum);
	}
	
	public void update()
	{
		current = robot.getCurHeat();
		maximum = robot.getMaxHeat();
		cap = RobotValues.MAXIMUM_HEAT_CAP;
	}
	
	
}
