package display.duo.bar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import display.Camera;
import files.Fonts;
import objects.Robot;
import values.DisplayValues;


public abstract class StatusBar implements DisplayValues
{
	Robot robot;
	float xPos;
	float yPos;
	float current;
	float maximum;
	float cap;
	float width;
	float indent;
	
	Color color;
	
	StatusBar(Robot robot, float xPos, float yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.robot = robot;
		this.indent = DisplayValues.ROBOT_CARD_WIDTH*.05f;
		width = STATUS_BAR_HORIZONTAL_WIDTH - indent * 2;
		update();
	}
	
	abstract void update();
	abstract void render(Graphics g);
	
	public float current()
	{
		return current;
	}
	
	public float percentage()
	{
		return current / maximum;
	}
	
	public float scale()
	{
		return maximum / cap;
	}
	
	public float getX()
	{
		return xPos;
	}
	
	public float getY()
	{
		return yPos;
	}
	
	
}
