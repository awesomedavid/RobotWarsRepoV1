package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.Game;
import files.Fonts;
import objects.Unit;
import values.CoreValues;
import values.Settings;
import world.MapSize;
import world.World;
import world.Zone;
import world.cells.Cell;
import world.cells.feature.Beacon;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;
import world.cells.terrain.Floor;
import world.cells.terrain.Grass;
import world.cells.terrain.GrassHeavy;
import world.cells.terrain.GrassLight;
import world.cells.terrain.GrassMedium;
import world.cells.terrain.Mud;
import world.cells.terrain.Sand;
import world.cells.terrain.Soil;
import world.cells.terrain.StoneRough;
import world.cells.terrain.WaterDeep;
import world.cells.terrain.WaterShallow;

public class Minimap implements CoreValues{
	
	Color[][] map;
	
	final int MAP_CELLS = 40;
	int MINI_CELL_SIZE = 5;
	
	int step;
	
	private final int MAP_BORDER = 3;
	private final int MAP_INDENT = 15;
	
	// Locked in upper right corner for now
	private float xPos;
	private float yPos;

	final Color BACKGROUND_COLOR = new Color(20, 20, 20);
	final Color SCREEN_LOCATION_COLOR = new Color(220, 220, 220);
	final Color NO_COLOR = new Color(0,0,0);
	
	Minimap()
	{
		step = World.getHeight() / MAP_CELLS;
		
		xPos = Game.getScreenWidth() - MAP_INDENT - MAP_BORDER - getMinimapWidth();
		yPos = MAP_INDENT - MAP_BORDER;
		
		map = new Color[MAP_CELLS][MAP_CELLS];
		
		for(int x = 0; x < MAP_CELLS; x++)
		{
			for(int y = 0; y < MAP_CELLS; y++)
			{
				map[x][y] = NO_COLOR;
			}
		}
	}
	
	public void update()
	{
		for(int x = 0; x < MAP_CELLS; x++)
		{
			for(int y = 0; y < MAP_CELLS; y++)
			{
				Cell c = World.getCell(x * step, y * step);
				map[x][y] = null;
				
				if(c.hasSmoke())
				{
					map[x][y] = Color.lightGray;
				}
				else if(c.hasFeature())
				{
					map[x][y] = c.getFeature().getColor();
				}
				else if(c.hasTerrain())
				{
					map[x][y] = c.getTerrain().getColor();
				}
			}
		}
		
		// Pass for walls + floors
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				int x = i / step;
				int y = j / step;
				Cell c = World.getCell(i, j);
				
				if(c.hasFeature() && c.getFeature() instanceof Wall)
				{
					map[x][y] = c.getTerrain().getColor();
				}
				else if(c.hasTerrain() && c.getTerrain() instanceof Floor)
				{
					map[x][y] = c.getTerrain().getColor();
				}
				else if(c.hasFeature())
				{
					map[x][y] = c.getFeature().getColor();
				}
			}
		}		
	}
			
	public void render(Graphics g)
	{		
		g.setLineWidth(1);
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(Camera.getX() + xPos, 
					Camera.getY() + yPos, 
					getMinimapWidth()  + MAP_BORDER*2, 
					getMinimapHeight() + MAP_BORDER*2);
		
		for(int x = 0; x < MAP_CELLS; x++)
		{
			for(int y = 0; y < MAP_CELLS; y++)
			{
				if(map[x][y] != null)
				{
					fillMiniCell(g, x, y, map[x][y]);
				}
			}
		}
		
		renderBeacons(g);
		renderUnits(g);
		renderMapType(g);
		
		g.setColor(SCREEN_LOCATION_COLOR);
		g.drawRect(xPos + Camera.getX() + Camera.getX() * getMinimapWidth() / (World.getWidth() * CoreValues.CELL_SIZE), 
				   yPos + Camera.getY() + Camera.getY() * getMinimapHeight() / (World.getHeight() * CoreValues.CELL_SIZE), 
				   getMinimapWidth() / Camera.getZoom() * Game.getScreenWidth() / (World.getWidth() * CoreValues.CELL_SIZE), 
				   getMinimapHeight() / Camera.getZoom() * Game.getScreenHeight() / (World.getHeight() * CoreValues.CELL_SIZE));

		
	}
	
	public void renderMapType(Graphics g)
	{
		float x = xPos + Camera.getX() + getMinimapWidth()/2;
		float y = yPos + Camera.getY() + getMinimapHeight() + 25;

		String b = World.getBiome().toString();
		Font f = Fonts.arial16Bold;
		g.setFont(f);

		int w = f.getWidth(b);
		int h = f.getHeight(b);
		
		g.setColor(Color.black);
		g.drawString(b, x - w/2 + 2, y - h/2 + 2);
		g.setColor(Color.white);
		g.drawString(b, x - w/2, y - h/2);
		
	}
	
	public void renderBeacons(Graphics g)
	{
		// Draw units last on top of everything
		for(Zone z : World.getZones())
		{
			int x = z.getBeacon().getX()/step;
			int y = z.getBeacon().getY()/step;
			Color c = z.getColor();
			c = new Color(c.getRed(), c.getGreen(), c.getBlue());
			
			for(int i = -2; i <= 2; i++)
			{
				for(int j = -2; j <= 2; j++)
				{
					if(!(i == -2 && j == -2) && !(i == 2 && j == -2) && !(i == -2 && j == 2) && !(i == 2 && j == 2))
					{
						if(map[x+i][y+j] != Color.lightGray)
						{
							fillMiniCell(g, x+i, y+j, c);
						}
					}
				}
			}
		}
	}
	
	public void renderUnits(Graphics g)
	{
		// Draw units last on top of everything
		for(Unit u : Game.getUnits())
		{
			if(u.isAlive())
			{
				int x = u.getX()/step;
				int y = u.getY()/step;
				Color c = u.getTeam().getColor().brighter();
		
				fillMiniCell(g, x, y, c);
				drawMiniCell(g, x, y, Color.black);
			}

		}
	}
	
	public void fillMiniCell(Graphics g, int x, int y)
	{
		g.fillRect(Camera.getX() + xPos + x * MINI_CELL_SIZE + MAP_BORDER, 
				   Camera.getY() + yPos + y * MINI_CELL_SIZE + MAP_BORDER, 
				   MINI_CELL_SIZE, MINI_CELL_SIZE);
	}
	
	public void fillMiniCell(Graphics g, int x, int y, Color c)
	{
		g.setColor(c);
		fillMiniCell(g, x, y);
	}
	
	public void drawMiniCell(Graphics g, int x, int y)
	{
		g.setColor(Color.black);
		g.drawRect(Camera.getX() + xPos + x * MINI_CELL_SIZE + MAP_BORDER, 
				   Camera.getY() + yPos + y * MINI_CELL_SIZE + MAP_BORDER, 
				   MINI_CELL_SIZE, MINI_CELL_SIZE);
	}
	
	public void drawMiniCell(Graphics g, int x, int y, Color c)
	{
		g.setColor(c);
		drawMiniCell(g, x, y);
	}
	
	public float getMinimapWidth()
	{
		return MINI_CELL_SIZE * MAP_CELLS;
	}
	
	public float getMinimapHeight()
	{
		return MINI_CELL_SIZE * MAP_CELLS;
	}
	

}
