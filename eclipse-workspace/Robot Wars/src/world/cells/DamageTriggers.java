package world.cells;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;

import animations.Animation;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import objects.Unit;
import values.CoreValues;
import world.cells.feature.Beacon;
import world.cells.feature.Feature;

class DamageTriggers implements CoreValues {
	private int timer;

	ArrayList<DamageEvent> events;
	Cell myCell;

	
	DamageTriggers(Cell c)
	{
		events = new ArrayList<DamageEvent>();
		myCell = c;
	}
	
	public boolean isActive()
	{
		return timer > 0;	
	}
	
	public void addDamageEvent(Unit source, int amount, int delay, DamageType type, Animation animation)
	{
		events.add(new DamageEvent(source, amount, delay, type, animation));
	}
	
	public void update()
	{
		if(timer > 0)
		{
			timer--;
		}
		
		for(int i = 0; i < events.size(); i++)
		{
			DamageEvent d = events.get(i);
			d.delayLeft--;
			
			if(d.delayLeft == 0)
			{
				timer += d.amount;
				
				if(myCell.hasUnit())
				{
					Unit target = myCell.getUnit();
					target.addEffect(new Damage(d.source, target, 1, d.amount, d.type, false));
				}
				if(myCell.hasFeature() && !(myCell.getFeature() instanceof Beacon))
				{
					Feature target = myCell.getFeature();
					target.addEffect(new Damage(d.source, target, 1, d.amount, d.type, false));
				}
				
				Game.addAnimation(d.animation);
				
				//myCell.setDebugNum(d.amount);

				events.remove(d);
			}
		}
	}
	
	class DamageEvent
	{
		public int amount;
		public int delayLeft;
		public Unit source;
		public DamageType type;
		Animation animation;
		
		DamageEvent(Unit source, int amount, int delayLeft, DamageType type, Animation animation)
		{
			this.amount = amount;
			this.delayLeft = delayLeft;
			this.source = source;
			this.type = type;
			this.animation = animation;
		}
	}
}
