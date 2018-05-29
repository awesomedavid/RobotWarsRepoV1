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

public class GrassLight extends Grass
{

	public GrassLight(Cell owner)
	{
		super(owner);

		sheet = Images.terrainGrassLight;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(110, 140, 40);
	}

	

}
