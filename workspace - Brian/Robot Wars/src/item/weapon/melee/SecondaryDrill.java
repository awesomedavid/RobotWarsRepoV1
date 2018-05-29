package item.weapon.melee;

import actions.use.melee.UseDrill;
import actions.use.ranged.UseLaser;
import actions.use.ranged.UseMachineGun;
import animations.beam.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class SecondaryDrill extends Weapon 
{
	public SecondaryDrill(Unit owner)
	{
		super(owner);
		cost = SECONDARY_COST;
		weight = SECONDARY_WEIGHT;
		
		maxRange = SECONDARY_DRILL_RANGE;
		cooldown = SECONDARY_DRILL_COOLDOWN;
		heat = SECONDARY_DRILL_HEAT;
		name = "Drill";
		image = Images.primaryDrill; 
		needsAmmo = false;
		targetObject = true;
		value = SECONDARY_DRILL_DAMAGE;

	}

	public void equip() 
	{  
		super.equip();
		getOwner().enableMine();
	}
	
	public void unequip() 
	{
		super.unequip();
		getOwner().disableMine();
	}
	
	public void use(GameObject target) 
	{
		super.use(target);

		if(canUse(target))
		{
			action(new UseDrill(this, target));
		}
	}
	
	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseDrill(this, target));
		}
	}	

}
