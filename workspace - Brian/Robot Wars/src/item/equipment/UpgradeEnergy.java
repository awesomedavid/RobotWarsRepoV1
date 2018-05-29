package item.equipment;

import core.DamageType;
import item.weapon.Weapon;
import objects.Robot;

public class UpgradeEnergy extends Upgrade 
{
	Weapon upgraded;
	
	public UpgradeEnergy(Robot owner)
	{
		super(owner);
		cost = UPGRADE_ENERGY_COST;
		weight = UPGRADE_ENERGY_WEIGHT;
	}
	
	public void equip()
	{
		super.equip();
		
		if(getOwner().hasPrimary() && getOwner().getPrimary().getDamageType() == DamageType.NORMAL)
		{
			upgraded = getOwner().getPrimary();
			getOwner().getPrimary().setDamageType(DamageType.ENERGY);

		}
		else if(getOwner().hasSecondary() && getOwner().getSecondary().getDamageType() == DamageType.NORMAL)
		{
			upgraded = getOwner().getSecondary();
			getOwner().getSecondary().setDamageType(DamageType.ENERGY);
		}
	}
	
	public void unequip()
	{
		super.unequip();
		upgraded.setDamageType(DamageType.NORMAL);
	}
}
