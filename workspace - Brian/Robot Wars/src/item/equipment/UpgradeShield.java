package item.equipment;

import objects.Robot;

public class UpgradeShield extends Upgrade 
{
	
	public UpgradeShield(Robot owner)
	{
		super(owner);
		cost = UPGRADE_SHIELD_COST;
		weight = UPGRADE_SHIELD_WEIGHT;

	}
	
	public void equip()
	{
		super.equip();
		getOwner().increaseMaxShield(UPGRADE_SHIELD_AMOUNT);
		getOwner().increaseShieldRegen(UPGRADE_SHIELD_REGEN);
	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseMaxShield(UPGRADE_SHIELD_AMOUNT);
		getOwner().decreaseShieldRegen(UPGRADE_SHIELD_REGEN);
	}


}
