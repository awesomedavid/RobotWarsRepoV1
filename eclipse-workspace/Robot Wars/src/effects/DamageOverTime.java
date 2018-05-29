package effects;

import core.DamageType;
import objects.Unit;

public class DamageOverTime extends Damage 
{
	float damageInitial;
	float damagePeriodic;
	DamageType type;
	boolean crit;
	
	public DamageOverTime(Unit source, Unit target, int delay, float damageInitial, DamageType type, boolean crit, float damagePeriodic, int duration, int tick) {
		super(source, target, delay, damageInitial, type, crit);
		hasPeriodic = true;
		this.damagePeriodic = damagePeriodic;
		this.duration = duration;
		this.tick = tick;
		this.type = type;
		this.crit = crit;
	}


	@Override
	public void periodicEffect() {
		// TODO Auto-generated method stub
		
	}
}
