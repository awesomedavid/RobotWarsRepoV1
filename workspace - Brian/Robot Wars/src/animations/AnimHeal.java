package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import files.Images;
import objects.GameObject;
import objects.Unit;

public class AnimHeal extends AnimCross 
{
	GameObject actor;
	
	public AnimHeal(float x, float y, float size, GameObject actor, int duration) 
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
		g.fillRect(x, y, w, h);

	}

	void edge(Graphics g, float x, float y, float w, float h) {
		g.drawRect(x, y, w, h);
	}
	

	float getLong() {
		return size;
		
	}

	float getShort() {
		return size/4;		
	}
}