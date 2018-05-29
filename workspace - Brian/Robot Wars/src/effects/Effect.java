package effects;

import objects.GameObject;
import objects.Unit;

public abstract class Effect 
{
	GameObject target;
	Unit source;
	
	int timer = -1;
	int duration;
	
	int tick;
	int delay = 1;
	
	boolean unique = false;
	boolean hasPeriodic = false;
	boolean hasInitial = false;
	
	public Effect(Unit source, GameObject target) 
	{
		this.source = source;
		this.target = target;

	}
		
	public boolean isActive() {
		return timer >= delay;
	}

	public boolean isExpired() {
		return duration != -1 && timer >= duration;
	}

	public boolean isInitial() {
		return hasInitial && timer == delay;
	}

	public boolean isPeriodic() {
		return hasPeriodic && (timer - delay) % tick == 0;
	}
	
	public boolean isUnique()
	{
		return unique;
	}
	
	public int getTimeLeft()
	{
		return duration - timer;
	}
	
	abstract public void initialEffect();
	abstract public void periodicEffect();

	
	
	
//	public void initialEffect() {
//		if (damageInitial > 0)
//			u.takeDamage(damageInitial, source);
//		if (healInitial > 0)
//			u.regainHealth(healInitial);
//
//		// System.out.println("heal initial in effect: " + healInitial);
//
//	}
//
//	public void periodicEffect() {
//		if (damagePeriodic > 0)
//			u.takeArmorPiercingDamage(damagePeriodic, source);
//		if (healPeriodic > 0)
//			u.regainHealth(healPeriodic);
//
//	}

	public void update() {
		timer++;

//		System.out.println(timer + " / " + duration + " updating the damage effect object thing");
		
		if (isInitial()) {
			initialEffect();
		}

		if (isPeriodic()) {
			periodicEffect();
		}

	}
	

}
