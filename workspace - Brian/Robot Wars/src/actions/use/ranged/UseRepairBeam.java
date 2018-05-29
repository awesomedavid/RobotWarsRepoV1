package actions.use.ranged;

import animations.AnimHeal;
import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
import animations.beam.AnimRepairBeam;
import animations.projectile.AnimProjectileSniper;
import animations.text.AnimFloatText;
import animations.text.AnimFloatTextCombat;
import animations.text.AnimFloatTextDamage;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import effects.Repair;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Unit;
import sound.Audio;

public class UseRepairBeam extends UseRangedAttack
{
	
	public UseRepairBeam(Weapon weapon, GameObject target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_REPAIR_BEAM_USE_TIME, 0, accuracyPenalty);
		value = weapon.getValue();
		type = weapon.getDamageType();
	}
	
	protected void complete()
	{
		makeAttackRoll();
		
		if(hit())
		{
			effect(new Repair(actor, targetObject, calculateDelay(), value));
		}
		
		
		if(getTarget().isAlive())
		{
			activate();
		}
	}
	
	protected void activate()
	{
			animation(new AnimRepairBeam(actor.getPointPixel(), actor, getTarget(), hit()));
			Audio.playHeal(getActor().getPointPixelSlick());
	}
		

}
