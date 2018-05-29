package animations.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimProjectileMachineGun extends AnimProjectile
{	
	public AnimProjectileMachineGun(Unit actor, GameObject targetObject, boolean hit) {
		super(actor, targetObject, PRIMARY_MG_ANIMATION_SPEED, hit);
	}
	
	public void render(Graphics g)
	{
		g.setColor(getColor());
		g.fillOval(x, y, 7, 7);
		
		g.setLineWidth(2);
		g.setColor(Color.black);
		g.drawOval(x, y, 7, 7);
	}
}
