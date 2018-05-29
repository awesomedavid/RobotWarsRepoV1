package item.weapon.ranged;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import actions.use.ranged.UseSniper;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Ranged;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class PrimaryMachineGun extends Ranged 
{
	public PrimaryMachineGun(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		maxRange = PRIMARY_MG_RANGE;
		cooldown = PRIMARY_MG_COOLDOWN;
		accuracy = PRIMARY_MG_ACCURACY;
		accuracyDrop = PRIMARY_MG_ACCURACY_DROP;

		maxAmmo = PRIMARY_MG_AMMO;
		heat = PRIMARY_MG_HEAT;
		reloadTime = PRIMARY_MG_RELOAD;
		value = PRIMARY_MG_DAMAGE;

		curAmmo = maxAmmo;
		name = "MG";
		image = Images.primaryMachineGun; 

	}


	
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canUse(target))
		{
			action(new UseMachineGun(this, target, getAccuracyPenalty(target)));
		}
	}

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseMachineGun(this, target, getAccuracyPenalty(target)));
		}
	}
}
