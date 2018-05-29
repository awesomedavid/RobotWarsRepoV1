package actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

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

public class Upgrade extends Action 
{
	Equipment gear;
	
	public Upgrade(Robot actor, Equipment e)
	{
		super(actor, UPGRADE_TIME_PER_METAL_COST * e.getCost());
		actionColor = new Color(0, 255, 0);

		gear = e;
		
		// Pay the cost at the start
		actor.getTeam().loseMetal(gear.getCost());
	}
		
	protected void complete()
	{
		((Robot) actor).getInventory().add(gear);
	}
		
	public void render(Graphics g)
	{
		super.render(g);
		
		renderActionProgress(g);

	}
}
