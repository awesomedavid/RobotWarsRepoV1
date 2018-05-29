package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public abstract class Floor extends Terrain
{
	
	public Floor(Cell owner)
	{
		super(owner);
		isSolid = true;
	}
	

}
