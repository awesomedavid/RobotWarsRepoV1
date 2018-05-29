package item.weapon.arc;

import actions.use.arc.UseSmokeGrenade;
import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import actions.use.ranged.UseSniper;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.types.Arc;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import world.World;

public class SecondarySmokeGrenade extends Arc 
{
	public SecondarySmokeGrenade(Unit owner)
	{
		super(owner);
		cost = SECONDARY_COST;
		weight = SECONDARY_WEIGHT;
		isPrimary = false;
		maxRange = SECONDARY_SMOKE_GRENADE_RANGE;
		cooldown = SECONDARY_SMOKE_GRENADE_COOLDOWN;
		accuracy = SECONDARY_SMOKE_GRENADE_ACCURACY;
		accuracyDrop = 0;

		maxAmmo = SECONDARY_SMOKE_GRENADE_AMMO;
		heat = SECONDARY_SMOKE_GRENADE_HEAT;
		reloadTime = -1;
		curAmmo = maxAmmo;
		name = "Smoke Grenade";
		image = Images.secondarySmokeGrenade; 
	}
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target.getPoint());
		action(new UseSmokeGrenade(this, target.getPoint(), getAccuracyPenalty(target.getPoint())));
		
	}

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		super.use(p);
		action(new UseSmokeGrenade(this, p, getAccuracyPenalty(p)));	
	}

}
