package animations.beam;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;

public abstract class AnimBeamConstant extends AnimBeam {

	int width;
	
	public AnimBeamConstant(Point origin, Unit actor, GameObject target, int width, int duration, boolean hit) {
		super(origin, actor, target, duration, hit);
		this.width = width;
	}
	
		
	public Color getColor()
	{
		Color c = actor.getTeam().getColor();
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), 150);
	}
	
	public int getWidth()
	{
		return width;		
	}



}
