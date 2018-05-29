package actions.use.arc;


import java.util.ArrayList;

import actions.use.UseOnCell;
import actions.use.UseOnObject;
import animations.Boom;
import animations.text.AnimFloatTextCombat;
import core.Game;
import core.Utility;
import effects.Damage;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import world.World;
import world.cells.Cell;

public abstract class UseArc extends UseOnCell
{	
	protected int radius;
	protected float animationSize;
	protected float spreadDelayRate;
	
	public UseArc(Unit actor, Point target, int aimTime, int shotSpeed, float accuracyPenalty, int scatterDistance) {
		super(actor, World.getCell(target), aimTime, shotSpeed, accuracyPenalty, scatterDistance);
	}
	
	public ArrayList<Cell> cellsInRadius()
	{		
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				Point p = new Point(-1,-1);
				
				if(getTarget() != null)
				{
					int x = getTarget().getX()+i;
					int y = getTarget().getY()+j;
					p = new Point(x, y);
	
				}
//				if(World.inBounds(p))
//				{
//					World.getCell(p).setDebugNum((int) Utility.distance(getTarget().getPoint(), p));
//				}
				
				if(World.inBounds(p) && Utility.distance(getTarget().getPoint(), p) <= radius)
				{
					cells.add(World.getCell(p));
				}
			}
		}
		
		return cells;
	}
	
	public float getProximity(Cell c)
	{
		return 1 - ((float) Utility.distance(c.getPoint(), getTarget().getPoint()) / (float) (radius + 1));
	}
	
	public int getSpreadDelay(Cell c)
	{
		return (int) Math.max(1,((1-getProximity(c))*spreadDelayRate));
	}
	
	public Boom makeBoomAnimation(Cell c)
	{
		return new Boom(c.getXPixel() + Utility.random(-CELL_SIZE/2, CELL_SIZE), 
				  c.getYPixel() + Utility.random(-CELL_SIZE/2, CELL_SIZE), 
				  Utility.random(animationSize*.75f, animationSize*1.25f) * (float) Math.sqrt(getProximity(c)));
	}
	
	
}
