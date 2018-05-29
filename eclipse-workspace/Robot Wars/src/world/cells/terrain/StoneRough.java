package world.cells.terrain;


import org.newdawn.slick.Color;

import files.Images;
import values.CoreValues;
import values.WorldValues;
import world.cells.Cell;

public class StoneRough extends Terrain
{
	
	public StoneRough(Cell owner)
	{
		super(owner);
		difficulty = WorldValues.TERRAIN_STONE_ROUGH_DIFFICULTY;
		sheet = Images.terrainStoneRough;
		image = sheet.getSprite(owner.getX() % (sheet.getWidth() / CoreValues.CELL_SIZE), owner.getY() % (sheet.getHeight() / CoreValues.CELL_SIZE));
		float r = .8f - ((1-owner.getMoisture())*.2f);
		float g = .8f - ((1-owner.getMoisture())*.35f);
		float b = .8f - ((1-owner.getMoisture()) * .45f);
		image.setImageColor(r, g ,b );
	}
	

	public void update() {
		
	}


	@Override
	public Color getColor() {
		float rMod = .9f - ((1-owner.getMoisture())*.2f);
		float gMod = .9f - ((1-owner.getMoisture())*.35f);
		float bMod = .9f - ((1-owner.getMoisture()) * .45f);
		return new Color((int) (150 * rMod), (int) (145 * gMod), (int)(140 * bMod));
	
	}


	

}
