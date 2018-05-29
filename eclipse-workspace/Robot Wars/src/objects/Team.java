package objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Game;
import values.CoreValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Wall;
import world.cells.feature.WallStone;
import world.cells.terrain.Floor;
import world.cells.terrain.FloorStone;

public abstract class Team 
{
	private String name;
	private int id;
	private float score;
	private int baseX;
	private int baseY;
	private List<Robot> robots;
	private int kills;
	private int deaths;
	
	private int stone;
	private int metal;
	private int wood;
//	Latency latency;
	
	public Team(int id)
	{
		this.id = id;
		robots = new ArrayList<Robot>();
	//	latency = new Latency();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void init()
	{
		setup();
		metal = CoreValues.TEAM_STARTING_METAL;
		wood = CoreValues.TEAM_STARTING_WOOD;
		stone = CoreValues.TEAM_STARTING_STONE;

		score = 0;
	}

	public abstract void setup();
	public abstract void strategy();
	
	public void setOrigin(int x, int y)
	{
		baseX = x;
		baseY = y;
	}
	
	public int getBaseX()
	{
		return baseX;
	}

	public int getBaseY()
	{
		return baseY;
	}
	
	public Point getBaseLocation()
	{
		return new Point(baseX, baseY);
	}

	public int getID()
	{
		return id;
	}

	public float getScore()
	{
		return score;
	}
	
	public float getScorePercent()
	{
		return Math.min((float) score / (float) CoreValues.POINTS_TO_WIN, 1);
	}
	
	public boolean hasStone()
	{
		return hasStone(1);
	}
	
	public boolean hasStone(int amount)
	{
		return stone >= amount;
	}
	
	public int getStone()
	{
		return stone;
	}
	
	public int getMetal()
	{
		return metal;
	}
	
	public boolean hasMetal()
	{
		return hasMetal(1);
	}
	
	public boolean hasMetal(int amount)
	{
		return metal >= amount;
	}
	
	public int getWood()
	{
		return wood;
	}
	
	public boolean hasWood()
	{
		return hasWood(1);
	}
	
	public boolean hasWood(int amount)
	{
		return wood >= amount;
	}

	public void addStone(int amount)
	{
		stone += amount;
	}
	
	public void addWood(int amount)
	{
		wood += amount;
	}
	
	public void addMetal(int amount)
	{
		metal += amount;
	}
	
	public void loseStone(int amount)
	{
		stone = Math.max(0, stone - amount);
	}
	
	public void loseWood(int amount)
	{
		wood = Math.max(0, wood - amount);
	}
	
	public void loseMetal(int amount)
	{
		metal = Math.max(0, metal - amount);
	}
	
	public void increaseScore(float points)
	{
		score += points;
	}
	
	public void addKill()
	{
		kills++;
		increaseScore(CoreValues.POINTS_FOR_KILL);
	}
	
	public void addDeath()
	{
		deaths++;
	}
	
//	public long getRecentLatency()
//	{
//		return latency.getRecentLatency();
//	}
//	public void addLatency(long amount)
//	{
//		latency.addLatency(amount);
//	}
	
	public boolean win()
	{
		return score >= CoreValues.POINTS_TO_WIN;
	}

	public void spawnRobots()
	{
		try 
		{
			Robot r;
			
			r = createRobotOne(baseX-2, baseY-2);
			Game.addUnit(r);
			robots.add(r);
						
			r = createRobotTwo(baseX+2, baseY-2);
			Game.addUnit(r);
			robots.add(r);
			
			r = createRobotThree(baseX-2, baseY+2);
			Game.addUnit(r);
			robots.add(r);

			r = createRobotFour(baseX+2, baseY+2);
			Game.addUnit(r);
			robots.add(r);			
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void spawnBase()
	{
		for(int i = -5; i < 6; i++)
		{
			for(int j = -5; j < 6; j++)
			{
				Cell c = World.getCell(baseX+i, baseY+j);
				c.removeFeature();
			}
		}
		
		for(int i = -3; i < 4; i++)
		{
			for(int j = -3; j < 4; j++)
			{
				Cell c = World.getCell(baseX+i, baseY+j);

				c.removeTerrain();
				c.addTerrain(new FloorStone(c));
				
				if((Math.abs(i) >= 3 || Math.abs(j) >= 3) && !(i == 0 || j == 0))
				{
					c.addFeature(new WallStone(c));
				}
			}
		}
	}

	public List<Robot> getRobots()
	{
		return robots;
	}	
	
	public void clear()
	{
			robots.clear();
	}
	
	abstract protected Robot createRobotOne(int x, int y)  throws SlickException;
	abstract protected Robot createRobotTwo(int x, int y)  throws SlickException;
	abstract protected Robot createRobotThree(int x, int y)  throws SlickException;
	abstract protected Robot createRobotFour(int x, int y)  throws SlickException;

	abstract public Color getColor();
}
