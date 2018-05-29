package world.cells.terrain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import files.Images;
import values.CoreValues;
import values.Settings;
import world.World;
import world.cells.Cell;

public abstract class Terrain 
{
	protected Cell owner;
	protected int difficulty;
	protected boolean isPassable;
	protected boolean isSolid;
	
	protected Image image;
	protected SpriteSheet sheet;  // 32
	
	Terrain(Cell owner)
	{
		this.owner = owner;
		isPassable = true;
		difficulty = 20;
	}
	
	/*
	 * Blending code written by Aaron Toms
	 */
	
	@SuppressWarnings("static-access")
	public void render(Graphics g)
	{	
		boolean b = true;
		image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
		
		if(Settings.alphaBlending)
		{
		if(!isSolid())
		{
			Cell c;
			c = World.getCell(owner.getX() - 1, owner.getY());
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass()) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alpha.getSubImage(0, 1).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
				b = false;
			}
			c = World.getCell(owner.getX() + 1, owner.getY());
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass()) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alpha.getSubImage(2, 1).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
				b = false;
			}
			c = World.getCell(owner.getX(), owner.getY() - 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass()) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alpha.getSubImage(1, 0).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
				b = false;
			}
			c = World.getCell(owner.getX(), owner.getY() + 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass()) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alpha.getSubImage(1, 2).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
				b = false;
			}
			
			
			c = World.getCell(owner.getX() - 1, owner.getY() - 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass() && b) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alphaCorners.getSubImage(0, 1).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
			}
			c = World.getCell(owner.getX() + 1, owner.getY() - 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass() && b) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alphaCorners.getSubImage(2, 1).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
			}
			c = World.getCell(owner.getX() - 1, owner.getY() + 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass() && b) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alphaCorners.getSubImage(1, 0).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
			}
			c = World.getCell(owner.getX() + 1, owner.getY() + 1);
			if(c != null && !c.getTerrain().isSolid() && c.getTerrain().getClass() != this.getClass() && b) { 
				g.setDrawMode(g.MODE_ALPHA_MAP);
				Images.alphaCorners.getSubImage(1, 2).draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_ALPHA_BLEND);
				c.getTerrain().image.draw(owner.getX() * CoreValues.CELL_SIZE, owner.getY() * CoreValues.CELL_SIZE);
				g.setDrawMode(g.MODE_NORMAL);
			}
		}
		}
		
	

	}
	
	public abstract Color getColor();
	public abstract void update();
	
	
	public float getMoisture()
	{
		return owner.getMoisture();
	}
	
	public float getFertility()
	{
		return owner.getFertility();
	}
	
	public float getElevation()
	{
		return owner.getElevation();
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public boolean isPassable()
	{
		return isPassable;
	}
	
	public boolean isSolid()
	{
		return isSolid;
	}
	
}
