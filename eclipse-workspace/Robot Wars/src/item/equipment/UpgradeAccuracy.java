package item.equipment;

import objects.Robot;

public class UpgradeAccuracy extends Upgrade 
{
	public UpgradeAccuracy(Robot owner)
	{
		super(owner);
		cost = UPGRADE_ACCURACY_COST;
		weight = UPGRADE_ACCURACY_WEIGHT;
	}
	
	public void equip()
	{
		super.equip();
		getOwner().increaseBaseAccuracy(UPGRADE_ACCURACY_AMOUNT);
	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseBaseAccuracy(UPGRADE_ACCURACY_AMOUNT);
	}
	
	

}
