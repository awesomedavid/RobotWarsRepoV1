package item.equipment;

import objects.Robot;

public class UpgradeAgility extends Upgrade 
{
	public UpgradeAgility(Robot owner)
	{
		super(owner);
		cost = UPGRADE_AGILITY_COST;
		weight = UPGRADE_AGILITY_WEIGHT;

	}
	
	public void equip()
	{
		super.equip();
		getOwner().increaseBaseSpeed(UPGRADE_AGILITY_SPEED);
		getOwner().increaseDodge(UPGRADE_AGILITY_DODGE);	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseBaseSpeed(UPGRADE_AGILITY_SPEED);
		getOwner().decreaseDodge(UPGRADE_AGILITY_DODGE);	
	}
	
	

}
