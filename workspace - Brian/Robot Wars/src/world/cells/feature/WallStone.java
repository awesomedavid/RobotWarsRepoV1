package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import objects.Point;
import objects.Unit;
import values.WorldValues;
import world.World;
import world.cells.Cell;

public class WallStone extends Wall
{	
	public WallStone(Cell owner)
	{
		super(owner);
		curHealth = WorldValues.WALL_STONE_HEALTH;
		maxHealth = curHealth;
		sheet = Images.wallBrick;
	}

	public void shadeColor() 
	{
		image.setImageColor(.8f,  .8f, .8f);
	}


	public Color getColor() {
		return new Color(140, 140, 140);
	}

	
	
}
