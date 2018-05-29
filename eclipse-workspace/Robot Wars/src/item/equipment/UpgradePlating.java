package item.equipment;

import objects.Robot;

public class UpgradePlating extends Upgrade 
{
	
	public UpgradePlating(Robot owner)
	{
		super(owner);
		cost = UPGRADE_PLATING_COST;
		weight = UPGRADE_PLATING_WEIGHT;

	}
	public void equip()
	{
		super.equip();
		getOwner().increaseMaxPlating(UPGRADE_PLATING_AMOUNT);
	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseMaxPlating(UPGRADE_PLATING_AMOUNT);

	}
	

}
