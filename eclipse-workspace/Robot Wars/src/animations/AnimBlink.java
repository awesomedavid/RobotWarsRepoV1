package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import files.Images;
import objects.Unit;

public class AnimBlink extends AnimCross 
{
	Unit actor;
	
	public AnimBlink(float x, float y, float size, Unit actor, int duration) 
	{
		super(x, y, size, duration);
		this.actor = actor;
	}

	Color getColor() 
	{
		return actor.getTeam().getColor().brighter();
	}
	
	void fill(Graphics g, float x, float y, float w, float h) 
	{
		g.fillOval(x, y, w, h);

	}

	void edge(Graphics g, float x, float y, float w, float h) {
		g.drawOval(x, y, w, h);
	}

	float getLong() {
		return size;
		
	}

	float getShort() {
		return size/7;		
	}
	
}