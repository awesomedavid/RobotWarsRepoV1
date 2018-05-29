package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.World;
import world.cells.Cell;

public class Mud extends Terrain
{
	
	public Mud(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_MUD_DIFFICULTY;
		sheet = Images.terrainMud;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(110, 70, 40);
	} 
	

	

}
