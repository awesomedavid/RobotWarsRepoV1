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
import world.cells.feature.WallStone;
import world.cells.terrain.FloorStone;
import world.cells.terrain.FloorWood;

public class BuildFloorWood extends BuildFloor
{	
	public BuildFloorWood(Unit actor)
	{
		super(actor, BUILD_FLOOR_WOOD_TIME, FloorWood.class);
	}
		
}
