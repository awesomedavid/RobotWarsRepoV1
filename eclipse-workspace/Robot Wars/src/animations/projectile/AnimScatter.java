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


public abstract class AnimScatter extends AnimTargeted {
	
	protected int speed;
	protected int scatterX;
	protected int scatterY;
	
	public AnimScatter(Unit actor, GameObject target, int speed, boolean hit) {
		super(actor.getPoint(), actor, target.getPoint(), target, Utility.distance(actor, target) * CoreValues.CELL_SIZE / speed, hit);
		this.speed = speed;
		scatterX = (int) Utility.random(-CoreValues.CELL_SIZE * .75f, CoreValues.CELL_SIZE * .75f);
		scatterY = (int) Utility.random(-CoreValues.CELL_SIZE * .75f, CoreValues.CELL_SIZE * .75f);
	}
	
	public void update() {
		super.update();
	
		targetPoint = targetObject.getPointPixel();
		targetPoint.setX(targetPoint.getX() + scatterX);
		targetPoint.setY(targetPoint.getY() + scatterY);
		
		// Calculate Current X Position
		float xDiff = targetPoint.getX() - xOrigin;
		x = xOrigin + CELL_SIZE/2 + xDiff * (float) ticks / (float) duration;

		float yDiff =  targetPoint.getY() - yOrigin;
		y = yOrigin + CELL_SIZE/2 + yDiff * (float) ticks / (float) duration;

	}

}
