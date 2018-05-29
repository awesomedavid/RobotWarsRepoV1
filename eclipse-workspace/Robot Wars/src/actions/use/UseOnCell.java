package actions.use;


import java.awt.Window.Type;

import actions.Action;
import animations.Animation;
import animations.text.AnimFloatTextCombat;
import animations.text.AnimFloatTextDamage;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import effects.Effect;
import item.StoneBlock;
import item.WoodLog;
import objects.Direction;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;

public abstract class UseOnCell extends Use 
{
	protected Cell target;
	protected int maxScatter;
	
	public UseOnCell(Unit actor, Cell target, int useTime)
	{
		super(actor, useTime, 1, 0);
		this.target = target;
	}
	
	public UseOnCell(Unit actor, Cell target, int useTime, int shotSpeed, float accuracyPenalty, int scatterDistance) 
	{
		super(actor, useTime, shotSpeed, accuracyPenalty);
		this.target = target;
		this.maxScatter = scatterDistance;
	}
	
	public void makeAttackRoll()
	{		
		float scatterScale = 1f - (float) Math.min(1, Math.random() - accuracyPenalty);			
		int scatterDistance = (int) (maxScatter * scatterScale);	
		target = getRandomCellInRange(target, scatterDistance);
	}
	
	public Cell getRandomCellInRange(Cell c, int range)
	{
		int x = 0;
		int y = 0;
		
		do
		{
			x = c.getX() + Utility.random(-range, range);
			y = c.getY() + Utility.random(-range, range);
			
		} while(Utility.distance(c.getX(), c.getY(), x, y) > range);
		
		return World.getCell(x, y);
	}

	protected void complete()
	{		
		makeAttackRoll();
		activate();
	}

	public Cell getTarget()
	{
		return target;
	}
	
	public int calculateDelay(float speed)
	{
		return calculateDelay(actor.getPoint(), target.getPoint(), speed);
	}
		

}
