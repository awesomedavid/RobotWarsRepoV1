package item.weapon.ranged;

import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import actions.use.ranged.UseRepairBeam;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Ranged;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class PrimaryRepairBeam extends Ranged
{
	public PrimaryRepairBeam(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		maxRange = PRIMARY_REPAIR_BEAM_RANGE;
		accuracy = PRIMARY_REPAIR_BEAM_ACCURACY;
		accuracyDrop = PRIMARY_REPAIR_BEAM_ACCURACY_DROP;
		cooldown = PRIMARY_REPAIR_BEAM_COOLDOWN;
		heat = PRIMARY_REPAIR_BEAM_HEAT;
		value = PRIMARY_REPAIR_BEAM_AMOUNT;

		curAmmo = maxAmmo;
		name = "Repair Beam";
		image = Images.primaryRepairBeam; 
		needsAmmo = false;
	}


	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canUse(target) && getOwner() != target)
		{
			action(new UseRepairBeam(this, target, getAccuracyPenalty(target)));
		}
	}
	

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target) && getOwner() != target)
		{
			action(new UseRepairBeam(this, target, getAccuracyPenalty(target)));
		}
	}

}
