package item.weapon.utility;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseSniper;
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

public class SecondaryRepairKit extends Utility
{
	public SecondaryRepairKit(Unit owner)
	{
		super(owner);
		cost = SECONDARY_COST;
		weight = SECONDARY_WEIGHT;
		maxRange = SECONDARY_REPAIR_KIT_RANGE;
		cooldown = SECONDARY_REPAIR_KIT_COOLDOWN;
		maxAmmo =  SECONDARY_REPAIR_KIT_AMMO;
		heat = SECONDARY_REPAIR_KIT_HEAT;
		reloadTime =  SECONDARY_REPAIR_KIT_RELOAD;
		curAmmo = maxAmmo;
		name = "Repair Kit";
		targetPoint = true;
		image = Images.secondaryRepairKit; 
	}

	
	// Fire at the specified target
	public void use() 
	{	
		if(canUse())
		{
			action(new UseRepairKit(getOwner(), getOwner()));
		}
		
		super.use();
	}
}
