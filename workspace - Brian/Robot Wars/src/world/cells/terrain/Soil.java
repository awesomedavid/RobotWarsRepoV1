package world.cells.terrain;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.World;
import world.cells.Cell;

public class Soil extends Terrain
{
	Image gSheet;
	
	float onEdgeModifier = 1;
	
	public Soil(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_SOIL_DIFFICULTY;

		sheet = Images.terrainSoil;
		
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
	} 
	
	public void update()
	{
	
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		//grass.setAlpha(getGrass() * .7f * onEdgeModifier);
		//grass.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(140, 105, 60);
	}
	

}
