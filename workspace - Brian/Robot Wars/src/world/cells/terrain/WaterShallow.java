package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public class WaterShallow extends Terrain
{	
	int timer = 0;
	int off = 0;
	
	public WaterShallow(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_WATER_SHALLOW_DIFFICULTY;
		sheet = Images.terrainWaterShallow;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
	}

	public void update()
	{

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(100, 120, 180);
	}
	

	

	

}
