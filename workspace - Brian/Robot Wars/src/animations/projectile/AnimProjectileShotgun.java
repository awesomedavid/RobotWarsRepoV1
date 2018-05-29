package animations.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimProjectileShotgun extends AnimScatter
{	
	public AnimProjectileShotgun(Unit actor, GameObject target, boolean hit) {
		super(actor, target, PRIMARY_SHOTGUN_ANIMATION_SPEED, hit);
	}
	
	public void render(Graphics g)
	{
		g.setColor(getColor().brighter());
		g.fillRect(x, y, 5, 5);
		
		g.setLineWidth(1);
		g.setColor(Color.black);
		g.drawRect(x, y, 5, 5);
	}
}
