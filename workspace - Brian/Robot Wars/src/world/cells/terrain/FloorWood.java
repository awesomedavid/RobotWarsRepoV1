package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public class FloorWood extends Floor
{
	public FloorWood(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_FLOOR_WOOD_DIFFICULTY;
		sheet = Images.terrainFloor;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
		image.setImageColor(.6f,  .3f, .05f);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Color getColor()
	{
		return new Color(180, 105, 60);
	}
	

}
