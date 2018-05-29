package item.equipment;

import objects.Robot;
import objects.Unit;
import values.UpgradeValues;

public abstract class Upgrade extends Equipment implements UpgradeValues
{

	protected Upgrade(Unit owner)
	{
		super(owner);
	}
	
	

}
