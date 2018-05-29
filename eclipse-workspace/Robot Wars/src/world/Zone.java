package world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import core.Game;
import core.Utility;
import objects.Point;
import objects.Team;
import values.CoreValues;
import world.cells.Cell;
import world.cells.feature.Beacon;

public class Zone implements CoreValues
{
	List<Cell> cells;
	List<ZoneTeamData> teams;
	
	int teamID;
	int progressID;
	int x;
	int y;
	Beacon beacon;
	int numUnitsTotal;
	int timer;
	
	public Zone(Beacon beacon)
	{
		this.beacon = beacon;
		x = beacon.getX();
		y = beacon.getY();
		
		teamID = -1;
		progressID = -1;
		cells = new ArrayList<Cell>();
		teams = new ArrayList<ZoneTeamData>();
		
		// Create data for each team in the overall game
		for(int i = 0; i < Game.countTeams(); i++)
		{
			teams.add(new ZoneTeamData(i, this));
		}
		
		for(int i = -CONTROL_ZONE_SIZE; i <= CONTROL_ZONE_SIZE; i++)
		{
			for(int j = -CONTROL_ZONE_SIZE; j <= CONTROL_ZONE_SIZE; j++)
			{
				Point me = new Point(x, y);
				Point p = new Point(x+i, y+j);
				
				if(World.inBounds(p) && Utility.distance(me, p) < CONTROL_ZONE_SIZE)
				{
					Cell c = World.getCell(p);
					c.setZone(this);
					cells.add(c);
				}
			}
		}
	}
	
	
	public void flip(int newID)
	{
		// Neutral goes to the new team
		if(teamID == -1)
		{
			teamID = newID;
		}
		
		// Otherwise, it becomes neutral
		else
		{
			teamID = -1;
		}
	}
	
	public void checkProgress()
	{
		for(ZoneTeamData data : teams)
		{
			if(data.isProgressing())
			{
				progressID = data.getTeamID();
				return;
			}
		}
		
		progressID = -1;
	}
	
	public void update()
	{
		timer++;
		
		updateUnitCountTotal();
		updateUnitCountByTeam();
		checkProgress();

		for(ZoneTeamData data : teams)
		{
			data.update();
		}	
		
		
		if(teamID != -1 && timer % POINT_GAIN_FREQUENCY == 0)
		{
			Game.getTeam(teamID).increaseScore(POINT_GAIN_VALUE);
		}
	

	}
	
	
	
	public int getOwnerID()
	{
		return teamID;
	
	}
	
	public Team getOwner()
	{
		return Game.getTeam(teamID);
	}
	
	public boolean isNeutral()
	{
		return teamID == -1;
	}
	
	public Color getColor()
	{
		// A team is currently making progress
		if(progressID > -1)
		{
			// if neutral, move toward the color is flipping toward
			if(isNeutral())
			{
				if(timer % 4 >= 2)
				{
					Color c = Game.getTeam(progressID).getColor();
					return new Color(c.getRed()+50, c.getGreen()+50, c.getBlue()+50, 50);
				}
				else
				{
					Color c = Game.getTeam(progressID).getColor();
					return new Color(c.getRed(), c.getGreen(), c.getBlue(), 50);
				}
			}
			
			// if not neutral, move toward neutral color
			else
			{
				if(timer % 4 >= 2)
				{
					return new Color(255, 255, 255, 100);
				}
				else
				{
					return new Color(255, 255, 255, 50);
				}
			}

		}
		
		// Control is static
		else
		{
			if(teamID == -1)
			{
				return new Color(255, 255, 255, 50);
			}
			else
			{
				Color c = Game.getTeam(teamID).getColor();
				return new Color(c.getRed(), c.getGreen(), c.getBlue(), 50);
			}
		}
		


	}
	
	public void updateUnitCountTotal()
	{
		int count = 0;
		
		for(Cell c : cells)
		{
			if(c.hasUnit())
			{
				count++;
			}
		}
		
		numUnitsTotal = count;
	}
	
	public int getUnitsTotal()
	{
		return numUnitsTotal;
	}
	
	public void updateUnitCountByTeam()
	{		
		for(ZoneTeamData data : teams)
		{
			data.countMyUnitsInZone();
		}
	}
	
	public List<Cell> getCells()
	{
		return cells;
	}
	
	public Beacon getBeacon()
	{
		return beacon;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	


}
