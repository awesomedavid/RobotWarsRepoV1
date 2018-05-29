package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public class WaterDeep extends Terrain
{
	
	public WaterDeep(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.IMPOSSIBLE;
		isPassable = false;
		sheet = Images.terrainWaterDeep;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
	//	image.setImageColor(1f, 1f, .85f);
	}

	public void update()
	{
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(70, 80, 140);
	}
	

	

}
