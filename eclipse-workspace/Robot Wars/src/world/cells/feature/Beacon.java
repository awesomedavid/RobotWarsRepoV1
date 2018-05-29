package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Utility;
import files.Images;
import objects.Point;
import objects.Unit;
import values.CoreValues;
import world.World;
import world.Zone;
import world.cells.Cell;

public class Beacon extends Feature
{
	Zone zone;
	final float BEACON_HEALTH = 50;
	
	public Beacon(Cell owner)
	{
		super(owner);
		isObstacle = true;
		blocksSight = false;
		isOversized = true;
		curHealth = BEACON_HEALTH;
		maxHealth = curHealth;
		image = Images.beacon;
		zone = new Zone(this);
	}
		
	
	public Zone getZone()
	{
		return zone;
	}
	public void render(Graphics g)
	{
		if(image != null)
		{
			image.draw(x * CELL_SIZE - CELL_SIZE/2, y * CELL_SIZE - CELL_SIZE/2);
		}
	}


	public Color getColor() {
		return new Color(255, 255, 255);
	}
}
