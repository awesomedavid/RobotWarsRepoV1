package display.duo.bar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import display.Camera;
import files.Fonts;
import objects.Robot;
import values.DisplayValues;


public abstract class HorizontalBar extends StatusBar implements DisplayValues
{

	
	HorizontalBar(Robot robot, float xPos, float yPos)
	{
		super(robot, xPos, yPos);
	}
	
	
	public void render(Graphics g)
	{
		if(maximum > 0)
		{
			// Maximum
			g.setColor(color.darker());
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), width * scale() , STATUS_BAR_HORIZONTAL_HEIGHT);
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), width * scale() , STATUS_BAR_HORIZONTAL_HEIGHT);

			// Current
			g.setColor(color);
			g.fillRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), width * percentage() * scale(), STATUS_BAR_HORIZONTAL_HEIGHT);
			g.setColor(Color.black);
			g.drawRect(xPos + Camera.getX() + indent, yPos + Camera.getY(), width * percentage() * scale(), STATUS_BAR_HORIZONTAL_HEIGHT);
		}
	}
	
	
}
