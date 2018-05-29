package actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import item.Item;
import item.Metal;
import item.StoneBlock;
import item.WoodLog;
import item.equipment.Equipment;
import item.weapon.Weapon;
import objects.Direction;
import objects.Robot;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;

public class Scrap extends Action 
{
	Equipment gear;
	
	public Scrap(Robot actor, Equipment e)
	{
		super(actor, SCRAP_TIME_PER_METAL_COST * e.getCost());
		actionColor = new Color(255, 0, 0);

		gear = e;	
	}
		
	protected void complete()
	{
		((Robot) actor).getInventory().scrap(gear);
		
		// Get half the cost back from a scrapped item.
		// No refund for consumables
		if(gear instanceof Weapon && ((Weapon) gear).canReload())
		{
			actor.getTeam().addMetal(gear.getCost() / 2);
		}
	
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		renderActionProgress(g);

	}
	
}
