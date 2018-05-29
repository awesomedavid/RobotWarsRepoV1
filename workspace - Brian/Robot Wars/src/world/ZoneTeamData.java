package world;

import core.Game;
import values.CoreValues;
import world.cells.Cell;

public class ZoneTeamData implements CoreValues
{	
	Zone zone;
	int teamID;
	int progress;
	int numUnits;

	public ZoneTeamData(int teamID, Zone zone)
	{
		this.zone = zone;
		this.teamID = teamID;
	}
	
	public int getTeamID()
	{
		return teamID;
	}
	
	public boolean isProgressing()
	{
		return hasFullControl() && zone.getOwnerID() != teamID;	
	}
	
	public void update()
	{
		if(isProgressing())
		{
			progress++;
		}
		else
		{
			progress = 0;
		}
		
		if(progress > FRAMES_TO_FLIP_ZONE)
		{
			zone.flip(teamID);
			progress = 0;
		}
		
	
	}
	
	public int getNumUnits()
	{
		return numUnits;
	}
	
	public int framesUntilControlled()
	{
		return progress;
	}
	
	public boolean hasFullControl()
	{
		return numUnits > 0 && numUnits == zone.getUnitsTotal();
	}
	
//	public float getUnitPercent(int teamID)
//	{
//		if(hasFullControl())
//		{
//			return 1.0f;
//		}
//		else
//		{
//			return (float) numUnits / (float) zone.getUnitsTotal();
//		}
//	}
	
	public void countMyUnitsInZone()
	{
		int count = 0;
		for(Cell c : zone.getCells())
		{
			if(c.hasUnit() && c.getUnit().getTeam() == Game.getTeam(teamID))
			{
				count++;
			}
		}
		
		numUnits = count;
	}
	
	
	
}
