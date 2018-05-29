package item.equipment;

import core.DamageType;
import item.weapon.Weapon;
import objects.Robot;

public class UpgradePierce extends Upgrade 
{
	Weapon upgraded;
	DamageType original;
	
	public UpgradePierce(Robot owner)
	{
		super(owner);
		cost = UPGRADE_PIERCE_COST;
		weight = UPGRADE_PIERCE_WEIGHT;
	}
	
	public void equip()
	{
		super.equip();
		
		if(getOwner().hasPrimary() && getOwner().getPrimary().getDamageType() == DamageType.NORMAL)
		{
			upgraded = getOwner().getPrimary();
			original = getOwner().getPrimary().getDamageType();
			getOwner().getPrimary().setDamageType(DamageType.PIERCE);

		}
		else if(getOwner().hasSecondary() && getOwner().getSecondary().getDamageType() == DamageType.NORMAL)
		{
			upgraded = getOwner().getSecondary();
			original = getOwner().getSecondary().getDamageType();
			getOwner().getSecondary().setDamageType(DamageType.PIERCE);
		}
	}
	
	public void unequip()
	{
		super.unequip();
		upgraded.setDamageType(original);
	}
}
