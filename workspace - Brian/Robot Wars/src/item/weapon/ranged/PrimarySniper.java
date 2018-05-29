package item.weapon.ranged;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseSniper;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Ranged;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class PrimarySniper extends Ranged
{
	public PrimarySniper(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		maxRange = PRIMARY_SNIPER_RANGE;
		accuracy = PRIMARY_SNIPER_ACCURACY;
		accuracyDrop = PRIMARY_SNIPER_ACCURACY_DROP;
		cooldown = PRIMARY_SNIPER_COOLDOWN;
		maxAmmo = PRIMARY_SNIPER_AMMO;
		heat = PRIMARY_SNIPER_HEAT;
		reloadTime = PRIMARY_SNIPER_RELOAD;
		value = PRIMARY_SNIPER_DAMAGE;
		type = PRIMARY_SNIPER_TYPE;

		curAmmo = maxAmmo;
		name = "Sniper";
		image = Images.primarySniperRifle; 
	}

	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);
		
		if(canUse(target))
		{
			action(new UseSniper(this, target, getAccuracyPenalty(target)));
		}
	}
	
	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseSniper(this, target, getAccuracyPenalty(target)));
		}
	}


}
