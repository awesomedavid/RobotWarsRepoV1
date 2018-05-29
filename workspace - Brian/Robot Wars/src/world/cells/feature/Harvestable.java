package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import objects.Unit;
import values.Settings;
import world.cells.Cell;

public abstract class Harvestable extends Feature
{

	
	public Harvestable(Cell owner)
	{
		super(owner);
	}
	
	public void harvest(float base, float ratePerRank, float skill)
	{
		curHealth = curHealth - (base + ratePerRank* skill);
		
		if(curHealth <= 0)
		{
			curHealth = 0;
			owner.removeFeature();
		}
	}
	
	public int getHarvestTime(float base, float ratePerRank, float skill)
	{
		return (int) (curHealth / (base + ratePerRank * skill));
	}
	

}
