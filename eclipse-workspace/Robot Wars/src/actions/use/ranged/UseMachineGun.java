package actions.use.ranged;

import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
import animations.projectile.AnimProjectileMachineGun;
import animations.text.AnimFloatTextCombat;
import core.DamageType;
import core.Game;
import effects.Damage;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Unit;
import sound.Audio;

public class UseMachineGun extends UseRangedAttack
{
	public UseMachineGun(Weapon weapon, GameObject target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_MG_USE_TIME, PRIMARY_MG_ANIMATION_SPEED, accuracyPenalty);
		value = weapon.getValue();
		type = weapon.getDamageType();
	}
	
	protected void activate()
	{
		super.complete();
		
		if(getTarget().isAlive())
		{
			animation(new AnimProjectileMachineGun(actor, getTarget(), hit()));
			Audio.playMG(getActor().getPointPixelSlick());
		}
	}
}
