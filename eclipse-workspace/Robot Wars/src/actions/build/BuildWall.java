package actions.build;

import java.lang.reflect.InvocationTargetException;

import item.Item;
import item.StoneBlock;
import objects.Direction;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;
import world.cells.feature.Mountain;
import world.cells.feature.Wall;

public class BuildWall extends Build 
{	
	public BuildWall(Unit actor,  int time, Class<? extends Feature> type)
	{
		super(actor, time, type);
	}
		
}
