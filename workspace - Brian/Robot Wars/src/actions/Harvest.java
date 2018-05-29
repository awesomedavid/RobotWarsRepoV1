package actions;

import item.StoneBlock;
import objects.Direction;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Harvestable;
import world.cells.feature.Mountain;

public abstract class Harvest extends Action 
{
	protected float rate;
	protected float ratePerSkill;
	protected float skill;
	
	Harvestable target;
	
	public Harvest(Unit actor, Harvestable target)
	{
		super(actor, 0);
		this.target = target;
	}
	
	public void update()
	{
		super.update();
		target.harvest(rate, ratePerSkill, skill);
		

	}
	
	

}
