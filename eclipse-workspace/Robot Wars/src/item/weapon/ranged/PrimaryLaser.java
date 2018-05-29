package item.weapon.ranged;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Ranged;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class PrimaryLaser extends Ranged
{
	public PrimaryLaser(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		maxRange = PRIMARY_LASER_RANGE;
		accuracy = PRIMARY_LASER_ACCURACY;
		accuracyDrop = PRIMARY_LASER_ACCURACY_DROP;
		cooldown = PRIMARY_LASER_COOLDOWN;
		heat = PRIMARY_LASER_HEAT;
		value = PRIMARY_LASER_DAMAGE;

		curAmmo = maxAmmo;
		name = "Laser";
		image = Images.primaryLaser; 
		needsAmmo = false;
	}


	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canUse(target))
		{
			action(new UseLaser(this, target, getAccuracyPenalty(target)));
		}
	}
	

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseLaser(this, target, getAccuracyPenalty(target)));
		}
	}

}
