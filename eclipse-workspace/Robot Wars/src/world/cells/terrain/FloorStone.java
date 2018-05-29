package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public class FloorStone extends Floor
{
	public FloorStone(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_FLOOR_STONE_DIFFICULTY;
		sheet = Images.terrainFloor;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
		image.setImageColor(.8f,  .8f, .8f);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Color getColor()
	{
		return new Color(205, 205, 205);
	}

}
