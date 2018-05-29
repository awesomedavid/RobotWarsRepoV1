package actions;

import item.StoneBlock;
import item.WoodLog;
import objects.Direction;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;

public class Chop extends Harvest 
{
	public Chop(Unit actor, Tree tree)
	{
		super(actor, tree);
		this.rate = CHOP_RATE_BASE;
		this.ratePerSkill = CHOP_RATE_PER_SKILL;
		this.skill = actor.getChopSkill();
		duration = target.getHarvestTime(rate, ratePerSkill, skill);
		
	}
	
	protected void complete()
	{
		World.addItemToCell(new WoodLog(), target.getX(), target.getY());
	}
	

}
