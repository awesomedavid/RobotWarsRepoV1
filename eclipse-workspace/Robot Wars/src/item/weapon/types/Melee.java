package item.weapon.types;

import item.weapon.Weapon;
import objects.Robot;
import objects.Unit;

public abstract class Melee extends Weapon 
{
	public Melee(Unit owner)
	{
		super(owner);
		targetObject = true;
	}
	
}
