package actions;

import item.Item;
import item.Metal;
import item.StoneBlock;
import item.WoodLog;
import item.equipment.Equipment;
import objects.Direction;
import objects.Robot;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;

public class Pickup extends Action 
{
	public Pickup(Unit actor)
	{
		super(actor, 1);
	}
		
	protected void complete()
	{
		Item i = World.takeItemFromCell(actor.getX(), actor.getY());
		
		if(i instanceof StoneBlock)
		{
			actor.getTeam().addStone(5);
		}
		else if(i instanceof WoodLog)
		{
			actor.getTeam().addWood(5);
		}
		else if(i instanceof Metal)
		{
			actor.getTeam().addMetal(10);
		}	

	}
}
