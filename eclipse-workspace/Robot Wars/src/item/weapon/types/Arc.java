package item.weapon.types;

import item.weapon.ShotCalculator;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Robot;
import objects.Unit;

public abstract class Arc extends Weapon 
{
	
	public Arc(Unit owner)
	{
		super(owner);
		targetPoint = true;
	}
	

}
