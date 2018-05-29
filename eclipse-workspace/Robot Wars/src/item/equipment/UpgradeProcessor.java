package item.equipment;

import objects.Robot;

public class UpgradeProcessor extends Upgrade 
{
	
	public UpgradeProcessor(Robot owner)
	{
		super(owner);
		cost = UPGRADE_PROCESSOR_COST;
		weight = UPGRADE_PROCESSOR_WEIGHT;

	}
	
	public void equip()
	{
		super.equip();
		getOwner().increaseProcessorLevel(Upgrade.UPGRADE_PROCESSOR_AMOUNT);
	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseProcessorLevel(Upgrade.UPGRADE_PROCESSOR_AMOUNT);
	
	}
	
}
