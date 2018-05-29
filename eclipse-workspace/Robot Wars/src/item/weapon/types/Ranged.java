package item.weapon.types;

import item.weapon.ShotCalculator;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Robot;
import objects.Unit;

public abstract class Ranged extends Weapon 
{
	public Ranged(Unit owner)
	{
		super(owner);
		targetObject = true;
	}
}
