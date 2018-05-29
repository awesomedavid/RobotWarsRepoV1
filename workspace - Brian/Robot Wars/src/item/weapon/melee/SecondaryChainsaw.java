package item.weapon.melee;

import actions.use.melee.UseChainsaw;
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

public class SecondaryChainsaw extends Weapon 
{
	public SecondaryChainsaw(Unit owner)
	{
		super(owner);
		cost = SECONDARY_COST;
		weight = SECONDARY_WEIGHT;
		
		maxRange = SECONDARY_CHAINSAW_RANGE;
		cooldown = SECONDARY_CHAINSAW_COOLDOWN;
		heat = SECONDARY_CHAINSAW_HEAT;
		name = "Chainsaw";
		image = Images.primaryChainsaw;
		needsAmmo = false;
		value = SECONDARY_CHAINSAW_DAMAGE;


	}

	public void equip() 
	{
		super.equip();
		getOwner().enableChop();
	}
	
	public void unequip() 
	{
		super.unequip();
		getOwner().disableChop();
	}
	
	public void use(GameObject target) 
	{
		super.use(target);
		
		if(canUse(target))
		{
			action(new UseChainsaw(this, target));
		}
	}
	
	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		GameObject target = getObjectFromCell(p);
		super.use(target);
		
		if(target != null && canUse(target))
		{
			action(new UseChainsaw(this, target));
		}
	}	


}
