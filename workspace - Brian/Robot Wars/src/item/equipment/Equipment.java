package item.equipment;

import item.Item;
import objects.Unit;

public abstract class Equipment extends Item 
{
	protected int weight;
	protected int cost;
	private Unit owner;
	
	Equipment(Unit owner) 
	{
		this.owner = owner;
	}

	public void equip() 
	{
		getOwner().getInventory().addWeight(weight);
	}
	
	public void unequip() 
	{
		getOwner().getInventory().loseWeight(weight);
	}

	public int getCost()
	{
		return cost;
	}
	
	public Unit getOwner()
	{
		return owner;
	}
	
	public int getWeight()
	{
		return weight;
	}

	
}
