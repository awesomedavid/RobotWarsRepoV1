package item.weapon.arc;

import actions.use.arc.UseArtillery;
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

public class PrimaryArtillery extends Arc 
{
	public PrimaryArtillery(Unit owner)
	{
		super(owner);
		cost = PRIMARY_COST;
		weight = PRIMARY_WEIGHT;
		isPrimary = true;
		minRange = PRIMARY_ARTILLERY_MIN_RANGE;
		maxRange = PRIMARY_ARTILLERY_MAX_RANGE;
		cooldown = PRIMARY_ARTILLERY_COOLDOWN;
		accuracy = PRIMARY_ARTILLERY_ACCURACY;
		accuracyDrop = 0;
		value = PRIMARY_ARTILLERY_DAMAGE;
		maxAmmo = PRIMARY_ARTILLERY_AMMO;
		heat = PRIMARY_ARTILLERY_HEAT;
		reloadTime = PRIMARY_ARTILLERY_RELOAD;
		curAmmo = maxAmmo;
		name = "Artillery";
		image = Images.primaryRocketLauncher; 
	}
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		Point p = target.getPoint();
		use(p);
	}

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		super.use(p);
		
		if(p != null && canUse(p))
		{
			action(new UseArtillery(this, p, getAccuracyPenalty(p)));	
		}
	}

}
