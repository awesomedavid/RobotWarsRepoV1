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
import world.cells.Cell;

public class Tree extends Plant
{
	
	public Tree(Cell owner)
	{
		super(owner);
		isObstacle = true;
		blocksSight = false;
		isOversized = true;
		curHealth = TREE_HEALTH;
		maxHealth = curHealth;
		canChop = true;
		oImage = Images.treeOak[Utility.random(2)];
		maturity = (float) Utility.random(.5f, 1.0f);
		image = oImage.getScaledCopy(maturity);
		oldWidth = oImage.getWidth();
		isCover = true;
	}
		
	public void render(Graphics g)
	{
		if(image != null)
		{
			image.draw(x * CELL_SIZE - CELL_SIZE/2 * maturity + CELL_SIZE/2 * (1-maturity),  
					   y * CELL_SIZE - CELL_SIZE * maturity + CELL_SIZE * (1-maturity));
		}
	}
	
	public Color getColor()
	{
		return new Color(10, 90, 30);
	}
}
