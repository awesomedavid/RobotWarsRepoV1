package animations.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.AnimTargeted;
import animations.Animation;
import core.Utility;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;


public abstract class AnimProjectile extends AnimTargeted {
	
	protected int speed;
	
	public AnimProjectile(Unit actor, GameObject targetUnit, int speed, boolean hit) {
		super(actor.getPoint(), actor, targetUnit.getPoint(), targetUnit, Utility.distance(actor, targetUnit) * CoreValues.CELL_SIZE / speed, hit);
		this.speed = speed;

	}
	
	public void update() {
		super.update();
		
		targetPoint = targetObject.getPointPixel();
		
		
		// Calculate Current X Position
		float xDiff = targetPoint.getX() - xOrigin;
		x = xOrigin + CELL_SIZE/2 + xDiff * (float) ticks / (float) duration;

		float yDiff =  targetPoint.getY() - yOrigin;
		y = yOrigin + CELL_SIZE/2 + yDiff * (float) ticks / (float) duration;

	}



}
