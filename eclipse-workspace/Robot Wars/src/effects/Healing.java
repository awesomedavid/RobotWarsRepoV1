package effects;

import core.DamageType;
import objects.GameObject;
import objects.Unit;

public class Healing extends Effect 
{
	float healingInitial;
	
	public Healing(Unit source, GameObject target, int delay, float healingInitial) {
		super(source, target);
		hasInitial = true;
		this.delay = delay;
		this.healingInitial = healingInitial;
		this.duration = delay + 1;
	}

	public void initialEffect() {
		target.regainHealth(healingInitial);
	}

	public void periodicEffect() {
		
	}
}
