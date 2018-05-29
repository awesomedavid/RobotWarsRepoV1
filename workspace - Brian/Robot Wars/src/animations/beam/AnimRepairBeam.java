package animations.beam;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.AnimHeal;
import animations.Animation;
import core.Game;
import core.Utility;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimRepairBeam extends AnimBeamConstant
{	
	int timer = 0;
	
	public AnimRepairBeam(Point origin, Unit actor, GameObject target, boolean hit) {
		super(origin, actor, target, PRIMARY_REPAIR_BEAM_ANIMATION_WIDTH, PRIMARY_REPAIR_BEAM_ANIMATION_DURATION, hit);
	}
	
	public void update()
	{
		super.update();
		
		float xDiff = actor.getXPixel() - target.getXPixel();
		float yDiff = actor.getYPixel() - target.getYPixel();
		
		float r = (float) Math.random();
		
		float x = actor.getXPixel() - xDiff * r;
		float y = actor.getYPixel() - yDiff * r;
		
		//if(ticks % 2 == 0)
		{
			animHeal(x, y);
			
			//System.out.println(x + " " + y);
		}
		
	
	}
	
	public void animHeal(float x, float y)
	{
			float scale = Utility.random(.5f, 1.5f);
			int duration = (int) (PRIMARY_REPAIR_BEAM_ANIMATION_CROSS_DURATION * scale);
			int size = (int) (PRIMARY_REPAIR_BEAM_ANIMATION_CROSS_SIZE * scale);
			x = x - size + Utility.random(-size, size);
			y = y - size + Utility.random(-size, size);
			Game.addAnimation(new AnimHeal(x, y, size, actor, duration));
		
	}
}
