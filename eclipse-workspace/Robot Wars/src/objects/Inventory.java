package objects;

import java.util.ArrayList;
import java.util.List;

import item.Item;
import item.Resource;
import item.equipment.Equipment;
import item.equipment.Upgrade;
import item.weapon.Weapon;

public class Inventory 
{
	Unit owner;
	Weapon primary;
	Weapon secondary;
	List<Upgrade> upgrades;
	private int weightCapacity;
	private int weightCarried;
	
	
	Inventory(Unit owner)
	{
		this.owner = owner;
		upgrades = new ArrayList<Upgrade>();
	}
	
	public int getWeightCapacity()
	{
		return weightCapacity;
	}
	
	public int getWeightCapacityFullyEncumbered()
	{
		return getWeightCapacity() * 2;
	}

	public int getWeightCarried()
	{
		return weightCarried;
	}
	
	public int getWeightCapacityLeftFullyEncumbered()
	{
		return getWeightCapacityFullyEncumbered() - getWeightCarried();
	}
	
	public int getWeightCapacityLeft()
	{
		return getWeightCapacity() - getWeightCarried();
	}
	
	public float getWeightSpeedPenalty()
	{	
		if(getWeightCarried() >= getWeightCapacityFullyEncumbered())
		{
			return 1;
		}
		else if(getWeightCarried() > getWeightCapacity())
		{
			return getWeightCarried() / getWeightCapacity() - 1;
		}
		else
		{
			return 0;
		}
	}
	public boolean canCarry(Equipment e)
	{
		return getWeightCapacityLeft() >= e.getWeight();
	}
	
	public void loseWeight(int amount)
	{
		weightCarried -= amount;

	}
	public void addWeight(int amount)
	{
		weightCarried += amount;
	}
	
	public int countUpgrades()
	{
		return upgrades.size();
	}	
	
	public int getValue()
	{
		int value = 0;
		
		for(Upgrade u : upgrades)
		{
			value += u.getCost();
		}
		
		if(hasPrimary())
		{
			value += getPrimary().getCost();
		}
		
		if(hasSecondary())
		{
			value += getSecondary().getCost();
		}
		
		return value;
	}	

	
	public void scrap(Equipment e)
	{
		if(e instanceof Weapon)
		{
			if(hasPrimary() && e == getPrimary())
			{
				e.unequip();
				primary = null;
			}
			
			if(hasSecondary() && e == getSecondary())
			{
				e.unequip();
				secondary = null;
			}
		}
		else
		{
			for(int i = 0; i < upgrades.size(); i++)
			{
				if(e == upgrades.get(i))
				{
					e.unequip();
					upgrades.remove(e);
				}
			}
		}
	}
	
	public void add(Equipment e)
	{
		if(canCarry(e))
		{
			// Weapon
			if(e instanceof Weapon)
			{
				// Secondary tries to go in secondary slot;  if not, fills primary slot
				if(((Weapon) e).isSecondary())
				{
					if(secondary == null)
					{
						secondary = (Weapon) e;
						secondary.equip();
					}
					else if(primary == null)
					{
						primary = (Weapon) e;
						primary.equip();
					}
				}
				
				// Primary can only go in primary weapon slot
				else if(((Weapon) e).isPrimary())
				{
					if(primary == null)
					{
						primary = (Weapon) e;
						primary.equip();
					}
				}
			}
			
			// Upgrades
			else
			{
				upgrades.add((Upgrade) e);
				e.equip();
			}
		}
	}
	
	public void remove(Equipment e)
	{
		if(getPrimary() == e)
		{
			primary = null;
			weightCarried -= e.getWeight();
		}
		else if(getSecondary() == e)
		{
			secondary = null;
			weightCarried -= e.getWeight();
		}
	}
	
	public void update()
	{
		if(hasPrimary())
		{
			primary.update();
		}
		if(hasSecondary())
		{
			secondary.update();
		}

	}
	
	public boolean isEmpty()
	{
		return primary == null && secondary == null && upgrades.isEmpty();
	}
	
	public boolean hasPrimary()
	{
		return primary != null;
	}
	
	public boolean hasSecondary()
	{
		return secondary != null;
	}
	
	public Weapon getPrimary()
	{
		return primary;
	}
	
	public Weapon getSecondary()
	{
		return secondary;
	}
	
	public void increaseMaxWeight(int amount)
	{
		weightCapacity += amount;
	}
	
	
	
	
}
