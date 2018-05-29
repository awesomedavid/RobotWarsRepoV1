package world.cells.feature;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import item.StoneBlock;
import objects.GameObject;
import objects.Point;
import values.CoreValues;
import values.WorldValues;
import world.World;
import world.cells.Cell;

public abstract class Feature extends GameObject implements CoreValues, WorldValues
{
	protected Cell owner;
	protected boolean isObstacle;
	protected boolean blocksSight;
	protected boolean isOversized;
	protected boolean isIndestructable;
	protected boolean canChop;
	protected boolean canMine;
	protected boolean isCover;
	
	protected Image image;
	protected SpriteSheet sheet;
	
	public abstract Color getColor(); 
	
	Feature(Cell owner)
	{
		super(owner.getX(), owner.getY(), -1);
		this.owner = owner;
	}
	
	public void render(Graphics g)
	{
		if(image != null)
		{
			g.drawImage(image, owner.getX() * CELL_SIZE, owner.getY()  * CELL_SIZE);
		}
	}
	
	public void update()
	{
		super.update();
		if(curHealth <= 0)
		{
			curHealth = 0;
			owner.removeFeature();
		}
	}
	
	public int getX()
	{
		return owner.getX();
	}
	
	public int getY()
	{
		return owner.getY();
	}
	
	public boolean isObstacle()
	{
		return isObstacle;
	}
	
	public boolean isPermanent()
	{
		return isObstacle;
	}
	
	public boolean blocksSight()
	{
		return blocksSight;
	}
	
	public boolean canChop()
	{
		return canChop;
	}
	
	public boolean canMine()
	{
		return canMine;
	}
	
	public boolean isCover()
	{
		return isCover;
	}
	
	public Color getHiddenColor()
	{
		return new Color(255, 0, 255);
	}
	
	public boolean isOversized()
	{
		return isOversized;
	}
	
	public void drawSightBlock(Graphics g)
	{
		g.setColor(getHiddenColor());
		
		int x = owner.getX();
		int y = owner.getY();
		
		int xOff = -2;
		int yOff = -4;
		
		float scale = .5f;
		
		// Bottom right
		if(blocksSight() && 
		   World.blocksSight(x+1, y) && owner.sameFeature(World.getCell(x+1, y)) &&
		   World.blocksSight(x, y+1) && owner.sameFeature(World.getCell(x, y+1)) &&
		   World.blocksSight(x+1, y+1) && owner.sameFeature(World.getCell(x+1, y+1)))
		{			
			g.fillRect(x * CoreValues.CELL_SIZE + CoreValues.CELL_SIZE/2 + xOff, y * CoreValues.CELL_SIZE + CoreValues.CELL_SIZE/2 + yOff, CoreValues.CELL_SIZE*scale, CoreValues.CELL_SIZE*scale);
		

//			// If on the bottom edge
//			if(!World.inBounds(x, y+1))
//			{
//				g.fillRect(x * Values.TILE_SIZE + Values.TILE_SIZE/2 + xOff, y * Values.TILE_SIZE, Values.TILE_SIZE, Values.TILE_SIZE);
//			}
			
	    }
		
		// Bottom Left
		if (blocksSight() && 
		   World.blocksSight(x-1, y) && owner.sameFeature(World.getCell(x-1, y)) &&
		   World.blocksSight(x, y+1) && owner.sameFeature(World.getCell(x, y+1)) &&
		   World.blocksSight(x-1, y+1) && owner.sameFeature(World.getCell(x-1, y+1)))
		{			
			g.fillRect(x * CoreValues.CELL_SIZE + xOff, y * CoreValues.CELL_SIZE + CoreValues.CELL_SIZE/2 + yOff, CoreValues.CELL_SIZE*scale, CoreValues.CELL_SIZE*scale);
	
//			if(!World.inBounds(x, y+1))
//			{
//				g.fillRect(x * Values.TILE_SIZE + Values.TILE_SIZE/2 + xOff, y * Values.TILE_SIZE, Values.TILE_SIZE, Values.TILE_SIZE);
//			}
		
		}
		
		// Top Left
		if (blocksSight() && 
		   World.blocksSight(x-1, y) && owner.sameFeature(World.getCell(x-1, y))&&
		   World.blocksSight(x, y-1) && owner.sameFeature(World.getCell(x, y-1))&& 
		   World.blocksSight(x-1, y-1) && owner.sameFeature(World.getCell(x-1, y-1)))
		{
			g.fillRect(x * CoreValues.CELL_SIZE + xOff, y * CoreValues.CELL_SIZE + yOff, CoreValues.CELL_SIZE*scale, CoreValues.CELL_SIZE*scale);
		
		
//			if(!World.inBounds(x, y-1))
//			{
//				g.fillRect(x * Values.TILE_SIZE + Values.TILE_SIZE/2 + xOff, y * Values.TILE_SIZE, Values.TILE_SIZE, Values.TILE_SIZE);
//			}
		}

		
		// Top Right
		if (blocksSight() && 
		    World.blocksSight(x+1, y) && owner.sameFeature(World.getCell(x+1, y))&&
		    World.blocksSight(x, y-1) && owner.sameFeature(World.getCell(x, y-1))&&
		    World.blocksSight(x+1, y-1) && owner.sameFeature(World.getCell(x+1, y-1)))
		{
			g.fillRect(x * CoreValues.CELL_SIZE + CoreValues.CELL_SIZE/2 + xOff, y * CoreValues.CELL_SIZE + yOff, CoreValues.CELL_SIZE*scale, CoreValues.CELL_SIZE*scale);
	
//			if(!World.inBounds(x, y-1))
//			{
//				g.fillRect(x * Values.TILE_SIZE + Values.TILE_SIZE/2 + xOff, y * Values.TILE_SIZE, Values.TILE_SIZE, Values.TILE_SIZE);
//			}
		
		}
	
	}
	
	
	public Point getSpriteCoordinate()
	{
		int x = owner.getX();
		int y = owner.getY();
		
		boolean[] connectors = {owner.sameFeature(World.getCell(x, y - 1)), owner.sameFeature(World.getCell(x + 1, y)), owner.sameFeature(World.getCell(x, y + 1)), owner.sameFeature(World.getCell(x - 1, y))};
		
		int xSprite = (connectors[0] ? 1 : 0) | (connectors[1] ? 2 : 0);
		int ySprite = 3 - ((connectors[2] ? 1 : 0) | (connectors[3] ? 2 : 0)); 
		
		return new Point(xSprite, ySprite);
	}
	

	

	
}
