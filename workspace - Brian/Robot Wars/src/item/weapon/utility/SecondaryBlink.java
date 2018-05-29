package item.weapon.utility;

import actions.use.arc.UseSmokeGrenade;
import actions.use.ranged.UseLaser;
import actions.use.ranged.UseSniper;
import actions.use.utility.UseBlink;
import actions.use.utility.UseRepairKit;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Utility;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import values.WeaponValues;
import world.World;

public class SecondaryBlink extends Utility
{
	public SecondaryBlink(Unit owner)
	{
		super(owner);
		cost = SECONDARY_COST;
		weight = SECONDARY_WEIGHT;
		maxRange = SECONDARY_BLINK_RANGE;
		cooldown = SECONDARY_BLINK_COOLDOWN;
		heat = SECONDARY_BLINK_HEAT;
		needsAmmo = false;
		targetAuto = true;
		name = "Blink";
		image = Images.secondaryBlink; 
	}
	
	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		if(canUse(p))
		{
			action(new UseBlink(this, p));	
		}
		
		super.use();
		
	}
	
	public boolean canUse(Point p) {
		return  super.canUse(p) && !World.getCell(p).hasUnit() && !World.getCell(p).hasObstacle();
	} 
	
}
