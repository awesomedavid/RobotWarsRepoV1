package item.weapon.ranged;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import actions.use.ranged.UseShotgun;
import actions.use.ranged.UseSniper;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Ranged;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class PrimaryShotgun extends Ranged 
{
	public PrimaryShotgun(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		maxRange = PRIMARY_SHOTGUN_RANGE;
		cooldown = PRIMARY_SHOTGUN_COOLDOWN;
		accuracy = PRIMARY_SHOTGUN_ACCURACY;
		accuracyDrop = PRIMARY_SHOTGUN_ACCURACY_DROP;

		maxAmmo = PRIMARY_SHOTGUN_AMMO;
		heat = PRIMARY_SHOTGUN_HEAT;
		reloadTime = PRIMARY_SHOTGUN_RELOAD;
		value = PRIMARY_MG_DAMAGE;

		curAmmo = maxAmmo;
		name = "Shotgun";
		image = Images.primaryShotgun; 

	}

	
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canUse(target))
		{
			action(new UseShotgun(this, target, getAccuracyPenalty(target)));
		}
	}

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseShotgun(this, target, getAccuracyPenalty(target)));
		}
	}
}
