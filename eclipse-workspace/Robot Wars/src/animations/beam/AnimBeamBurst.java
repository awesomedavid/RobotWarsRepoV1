package animations.beam;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public abstract class AnimBeamBurst extends AnimBeam {

	int width;
	
	public AnimBeamBurst(Point origin, Unit actor, GameObject target, int width, int duration, boolean hit) {
		super(origin, actor, target, duration, hit);
		this.width = width;
	}
	
	public Color getColor()
	{
		Color c = actor.getTeam().getColor();
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), getFadeAlphaValue());
	}
	
	public int getWidth()
	{
		return (int) (width * (1.0 - percentComplete()));
	}

}
