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
import world.cells.feature.WallWood;

public class BuildWallWood extends BuildWall 
{	
	public BuildWallWood(Unit actor)
	{
		super(actor, BUILD_WALL_WOOD_TIME, WallWood.class);
	}
		
}
