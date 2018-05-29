package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import objects.Point;
import objects.Unit;
import world.World;
import world.cells.Cell;

abstract public class Wall extends Constructable
{
	Point spritePos;
	
	 public Wall(Cell owner)
	{
		super(owner);
		isObstacle = true;
		blocksSight = true; 
		spritePos = new Point(-1, -1);
		isCover = true;
	}
	
	public void update()
	{
		super.update();
		Point temp = getSpriteCoordinate();
		if(!spritePos.equals(temp))
		{
			spritePos = temp;
			image = sheet.getSprite(spritePos.getX(), spritePos.getY());
			shadeColor();
		}

	}
	
	public abstract void shadeColor();
	
	public Color getHiddenColor()
	{
		return new Color(110, 115, 110);
	}
	
}
