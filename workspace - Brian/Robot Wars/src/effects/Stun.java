package effects;

import objects.GameObject;
import objects.Unit;

public class Stun extends Effect 
{
	public Stun(Unit source, GameObject target, int delay, int duration) {
		super(source, target);
		hasInitial = true;
		this.delay = delay;
		this.duration = duration;
		this.unique = true;

	}

	public void initialEffect() {
		
	}


	public void periodicEffect() {
		
	}
}
