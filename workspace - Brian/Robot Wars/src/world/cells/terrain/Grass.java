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

public abstract class Grass extends Terrain
{
	public Grass(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_SOIL_DIFFICULTY;
	} 
	
	public void update()
	{
	
	}
	

	
//	public void render(Graphics g)
//	{
//		super.render(g);

	//	grass.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);

//	}
	

}
