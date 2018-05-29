package actions.use.utility;

import actions.use.UseOnObject;
import animations.AnimHeal;
import core.Utility;
import effects.Repair;
import objects.GameObject;
import objects.Unit;

public class UseRepairKit extends UseOnObject
{
	public UseRepairKit(Unit actor, GameObject target) {
		super(actor, target, SECONDARY_REPAIR_KIT_USE_TIME, SECONDARY_REPAIR_KIT_ANIMATION_DURATION, 0);
		value = SECONDARY_REPAIR_KIT_HEAL_AMOUNT;
	}
	
	
	protected void complete()
	{
		if(getTarget().isAlive())
		{
			activate();
		}
	}
	
	protected void activate()
	{
		effect(new Repair(actor, actor, 1, value));		
		animHeal();
	}
	
	public void animHeal()
	{
		for(int i = 0; i < 9; i++)
		{
			float x = actor.getXPixel() + Utility.random(-CELL_SIZE/2, CELL_SIZE/2);
			float y = actor.getYPixel() + Utility.random(-CELL_SIZE*2, 0);

			float scale = Utility.random(.5f, 1.5f);
			int duration = (int) (SECONDARY_REPAIR_KIT_ANIMATION_DURATION * scale);
			int size = (int) (SECONDARY_REPAIR_KIT_ANIMATION_SIZE * scale);
			animation(new AnimHeal(x, y, size, actor, duration));
		}
	}
}
