package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Utility;
import files.Images;
import objects.Point;
import objects.Unit;
import values.CoreValues;
import world.World;
import world.cells.Cell;

public abstract class Plant extends Harvestable
{
	float maturity;
	Image oImage;
	int oldWidth;
	
	public Plant(Cell owner)
	{
		super(owner);
	}
	

}
