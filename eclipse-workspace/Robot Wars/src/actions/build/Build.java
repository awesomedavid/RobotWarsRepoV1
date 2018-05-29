package actions.build;

import java.lang.reflect.InvocationTargetException;

import actions.Action;
import item.Item;
import item.StoneBlock;
import objects.Direction;
import objects.GameObject;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;
import world.cells.feature.Mountain;
import world.cells.terrain.Terrain;

public class Build extends Action 
{
	Class<?> type;
	
	public Build(Unit actor, int duration, Class<?> type)
	{
		super(actor, duration);
		this.type = type;
	}
		
	protected void complete()
	{
			try {
				
				
				Cell targetCell = actor.getCell();
				
				
				if(Feature.class.isAssignableFrom(type))
				{

					Feature f = (Feature)(type.getConstructor(Cell.class)).newInstance((targetCell));
					targetCell.addFeature(f);
				}
				else if(Terrain.class.isAssignableFrom(type))
				{
					Terrain t = (Terrain)(type.getConstructor(Cell.class)).newInstance((targetCell));
					targetCell.removeTerrain();
					targetCell.addTerrain(t);
				}
				
	
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	}
}
