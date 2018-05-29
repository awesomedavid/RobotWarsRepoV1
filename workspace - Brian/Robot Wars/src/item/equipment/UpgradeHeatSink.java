package item.equipment;

import objects.Robot;

public class UpgradeHeatSink extends Upgrade 
{
	
	public UpgradeHeatSink(Robot owner)
	{
		super(owner);
		cost = UPGRADE_SHIELD_COST;
		weight = UPGRADE_HEAT_SINK_WEIGHT;
	}
	
	public void equip()
	{
		super.equip();
		getOwner().increaseCoolRate(.1f);
	}
	
	public void unequip()
	{
		super.unequip();
		getOwner().decreaseCoolRate(.1f);
	}

}
