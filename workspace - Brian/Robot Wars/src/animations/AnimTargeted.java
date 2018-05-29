package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import objects.GameObject;
import objects.Point;
import objects.Unit;

public abstract class AnimTargeted extends Animation{

	private Unit actor;
	protected GameObject targetObject;
	protected Point targetPoint;
	
	private boolean hit;
	
	public AnimTargeted(Point origin, Unit actor, Point targetPoint, GameObject targetObject, int duration, boolean hit) {
		super(origin.getX() * CELL_SIZE, origin.getY() * CELL_SIZE, duration);
		this.actor = actor;
		this.targetObject = targetObject;
		this.hit = hit;
		this.targetPoint = targetPoint;
		this.targetPoint.setX(targetPoint.getX() * CELL_SIZE);
		this.targetPoint.setY(targetPoint.getY() * CELL_SIZE);

	}

	public Unit getActor()
	{
		return actor;
	}
	
	public Color getColor()
	{
		return actor.getTeam().getColor();
	}
	
	public Point getTargetPoint()
	{
		if(targetObject != null)
		{
			return targetObject.getPoint();
		}
		else
		{
			return targetPoint;
		}

	}
	
	public boolean isHit()
	{
		return hit;
	}

}
