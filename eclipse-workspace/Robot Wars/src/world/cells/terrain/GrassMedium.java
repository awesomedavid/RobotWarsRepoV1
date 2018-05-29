package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.World;
import world.cells.Cell;

public class GrassMedium extends Grass
{
	
	public GrassMedium(Cell owner)
	{
		super(owner);

		sheet = Images.terrainGrassMedium;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(90, 160, 40);
	} 

}
