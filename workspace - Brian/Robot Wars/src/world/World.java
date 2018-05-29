package world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Utility;
import display.Camera;
import files.Fonts;
import item.Item;
import objects.GameObject;
import objects.Point;
import values.CoreValues;
import values.Settings;
import world.cells.Cell;
import world.cells.feature.Beacon;
import world.cells.feature.Feature;

public class World implements CoreValues
{	
	private static Biome biome;
	private static List<Point> controlPointCenter;
	private static List<Zone> zones;
	
 	private static Cell[][] tiles;
	
	private final int TERRAIN_DIFFICULTY_MAX = 10;
	SimplexNoise noise;
	final float SCALE_WATER_LEVEL = 30f;
	final float SCALE_ELEVATION = 15f;
	final float SCALE_FERTILITY = 30f;

	
	
	public World()
	{
		tiles = new Cell[World.getWidth()][World.getHeight()];
			
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j] = new Cell(i, j);
			}
		}
	
		biome = Biome.getRandom();
	//	biome = Biome.MOUNTAINS;
		controlPointCenter = new ArrayList<>();
		zones = new ArrayList<>();

		

		
	}
	
	public static Biome getBiome()
	{
		return biome;
	}
	
	public static int getWidth()
	{
		return Settings.mapSize.getSize();
	}
	
	public static int getHeight()
	{
		return Settings.mapSize.getSize();
	}
	
	public void clear()
	{
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j] = null;
			}
		}
	}
	
	/*********************** WORLD GENERATION ****************************/
	
	private void defineWorldWaterLevelRandom()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				float moisture = (float) ((noise.eval(i / SCALE_WATER_LEVEL, j / SCALE_WATER_LEVEL) + 1) / 2);
				tiles[i][j].setMoisture(moisture + biome.getWaterLevel());
			}
		}
	}
	
	private void defineWorldWaterLevelMirrorDiagonal()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				float moisture = (float) ((noise.eval(i / SCALE_WATER_LEVEL, j / SCALE_WATER_LEVEL) + 1) / 2);
				tiles[i][j].setMoisture(moisture + biome.getWaterLevel());
				tiles[World.getWidth()-1-j][World.getHeight()-1-i].setMoisture(moisture + biome.getWaterLevel());

			}
		}
	}
	
	
	private void defineWorldFertilityRandom()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				float fertility = (float) ((noise.eval(i / SCALE_FERTILITY, j / SCALE_FERTILITY) + 1) / 2);
				tiles[i][j].setFertility(fertility + biome.getFertility());
			}
		}
	}
		
	
	private void defineWorldFertilityMirrorDiagonal()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				float fertility = (float) ((noise.eval(i / SCALE_FERTILITY, j / SCALE_FERTILITY) + 1) / 2);
				tiles[i][j].setFertility(fertility + biome.getFertility());
				tiles[World.getWidth()-1-j][World.getHeight()-1-i].setFertility(fertility + biome.getFertility());

			}
		}
	}
		
	
	private void defineWorldElevationRandom()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				float mountain = (float) ((noise.eval(i / SCALE_WATER_LEVEL, j / SCALE_WATER_LEVEL) + 1) / 2);
				tiles[i][j].setMountain(mountain + biome.getMountain());
				
			}
		}
		
	}

	private void defineWorldElevationMirrorDiagonal()
	{
		noise = new SimplexNoise(Utility.random(100000));

		for(int j = 0; j < World.getHeight(); j++)
		{
			for(int i = 0; i < World.getWidth(); i++)
			{
				float mountain = (float) ((noise.eval(i / SCALE_WATER_LEVEL, j / SCALE_WATER_LEVEL) + 1) / 2);
				tiles[i][j].setMountain(mountain + biome.getMountain());
				tiles[World.getWidth()-1-j][World.getHeight()-1-i].setMountain(mountain + biome.getMountain());
			}
		}
		
	}
	
	
	private void spawnTerrain()
	{
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j].spawnTerrain();
			}
		}
	}

	private void spawnWater()
	{
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j].spawnWater();
			}
		}
	}
		
	private void spawnMountains()
	{
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j].spawnMountain();
				
			}
		}
	}
	
	private Point getNewBeaconPoint()
	{
		int x = (int) Utility.random(CoreValues.CONTROL_ZONE_SIZE, World.getWidth()-CONTROL_ZONE_SIZE-1);
		int y = (int) Utility.random(CoreValues.CONTROL_ZONE_SIZE, World.getHeight()-CONTROL_ZONE_SIZE-1);
		return new Point(x, y);
	}
	
	private void spawnBeaconsTournament()
	{
		Point p1 = new Point(getWidth()/2,getHeight()/2);
		controlPointCenter.add(new Point(p1.getX(), p1.getY()));
		
		Point p2 = new Point((int) (p1.getX()+getWidth() * .12), (int) (p1.getY() - getHeight() * .36));
		controlPointCenter.add(new Point(p2.getX(), p2.getY()));
		controlPointCenter.add(new Point(getWidth()-1-p2.getX(), getHeight()-1-p2.getY()));

		Point p3 = new Point((int) (p1.getX() + getWidth() *.36), (int) (p1.getY() - getHeight() * .12));
		controlPointCenter.add(new Point(p3.getX(), p3.getY()));
		controlPointCenter.add(new Point(getWidth()-1-p3.getX(), getHeight()-1-p3.getY()));
		
		for(Point p : controlPointCenter)
		{
			tiles[p.getX()][p.getY()].spawnControlPoint();
			zones.add(tiles[p.getX()][p.getY()].getZone());
		}
		
	}
	
	private void spawnBeaconsRandom()
	{
		for(int i = 0; i < CONTROL_ZONE_NUMBER; i++)
		{
			spawnBeacon(getNewBeaconPoint());
		}
		
		for(Point p : controlPointCenter)
		{
			zones.add(tiles[p.getX()][p.getY()].getZone());
		}
	}
	
	public void spawnBeacon(Point p)
	{
		// Beacons cannot be too close together
		for(int i = 0; i < controlPointCenter.size(); i++)
		{
			if(Utility.distance(p, controlPointCenter.get(i)) < CONTROL_ZONE_MIN_DISTANCE)
			{
				spawnBeacon(getNewBeaconPoint());
				return;
			}
		}
		
		tiles[p.getX()][p.getY()].spawnControlPoint();
		controlPointCenter.add(p);
	}
	
		
	public static Cell getCell(int x, int y)
	{
		if(World.inBounds(x, y))
		{
			return tiles[x][y];
		}
		else
		{
			return null;
		}
	}
	
	public static List<Point> getControlPoints()
	{
		return controlPointCenter;
	}
	
	public static List<Zone> getZones()
	{
		return zones;
	}

	public static Cell getCell(Point p)
	{
		return getCell(p.getX(), p.getY());
	}
	
	
	public static boolean hasFeature(int x, int y)
	{
		if(World.inBounds(x, y))
		{
			return tiles[x][y].hasFeature();
		}
		else
		{
			return false;
		}
	}
	
	public static boolean hasFeature(int x, int y, Class<? extends Feature> clazz)
	{
		if(World.inBounds(x, y))
		{
			return tiles[x][y].hasFeature(clazz);
		}
		else
		{
			return false;
		}
	}

	
	public static void addItemToCell(Item i, int x, int y)
	{
		if(World.inBounds(x, y))
		{
			getCell(x, y).addItem(i);
		}
	}
	
	public static Item takeItemFromCell(int x, int y)
	{
		if(World.inBounds(x, y))
		{
			return getCell(x, y).takeItem();
		}
		else
		{
			return null;
		}
	}
	
	public void init()
	{
		if(Settings.tournamentMode)
		{
			defineWorldWaterLevelMirrorDiagonal();
			defineWorldFertilityMirrorDiagonal();
			defineWorldElevationMirrorDiagonal();
			spawnWater();
			spawnMountains();
			spawnTerrain();
			spawnBeaconsTournament();

		}
		else
		{
			defineWorldWaterLevelRandom();
			defineWorldFertilityRandom();
			defineWorldElevationRandom();
			spawnWater();
			spawnMountains();
			spawnTerrain();
			spawnBeaconsRandom();
		}
		
	}
	
	public void update()
	{
		for(int i = 0; i < World.getWidth(); i++)
		{
			for(int j = 0; j < World.getHeight(); j++)
			{
				tiles[i][j].update();
			}
		}
		
		for(Zone z : zones)
		{
			z.update();
		}
	}
	
	public void render(Graphics g)
	{		
		for(int i = Camera.tileFirstX(); i < Camera.tileLastX(); i++)
		{
			for(int j = Camera.tileFirstY(); j < Camera.tileLastY() + 2; j++)
			{
				if(j < Camera.tileLastY())
				{
					tiles[i][j].renderLayerOne(g);
				}
			}
		}
		
		for(int i = Camera.tileFirstX(); i < Camera.tileLastX(); i++)
		{
			for(int j = Camera.tileFirstY(); j < Camera.tileLastY() + 2 ; j++)
			{
				if(j < Camera.tileLastY())
				{
					tiles[i][j].renderLayerTwo(g);
				}
			}
		}
		
		for(int i = Camera.tileFirstX(); i < Camera.tileLastX(); i++)
		{
			for(int j = Camera.tileFirstY(); j < Camera.tileLastY() + 2 ; j++)
			{
				if(j < Camera.tileLastY())
				{
					tiles[i][j].renderLayerThree(g);
				}
			}
		}
		
		g.setFont(Fonts.arial16Bold);
		g.setColor(Color.white);
	//	g.drawString(""+biome, Camera.getX() + 10, Camera.getY() + Values.RESOLUTION_Y - 30);
	}
	
	public void renderAbove(Graphics g)
	{		
		for(int i = Camera.tileFirstX(); i < Camera.tileLastX(); i++)
		{
			for(int j = Camera.tileFirstY(); j < Camera.tileLastY() + 2 ; j++)
			{
				if(j < Camera.tileLastY())
				{
					tiles[i][j].renderLayerFour(g);
				}
			}
		}
	}
	
	public static boolean canEnter(int x, int y, GameObject actor)
	{
		return inBounds(x, y) && tiles[x][y].canEnter(actor);
	}
	
	public static boolean inBounds(int x, int y)
	{
		return x >= 0 && x < World.getWidth() && y >= 0 && y < World.getHeight();
	}
	
	public static boolean inBounds(Point p)
	{
		return inBounds(p.getX(), p.getY());
	}
	
	
	public static boolean inBounds(Cell c)
	{
		return inBounds(c.getX(), c.getY());
	}
	
	
	public static boolean hasObstacle(int x, int y)
	{
		return inBounds(x, y) && tiles[x][y].hasObstacle();
	}
	

	public static boolean blocksSight(int x, int y)
	{
		if(!inBounds(x, y))
		{
			return true;
		}
		else
		{
			return tiles[x][y].hasFeature() && tiles[x][y].getFeature().blocksSight();
		}
		
	}
	
	
	public static boolean hasUnit(int x, int y)
	{
		return tiles[x][y].hasUnit();
	}
	
	
	
	

}
