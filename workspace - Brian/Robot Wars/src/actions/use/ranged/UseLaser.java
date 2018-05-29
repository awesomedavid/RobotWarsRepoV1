package actions.use.ranged;

import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
import animations.projectile.AnimProjectileSniper;
import animations.text.AnimFloatText;
import animations.text.AnimFloatTextCombat;
import core.DamageType;
import core.Game;
import effects.Damage;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Unit;
import sound.Audio;

public class UseLaser extends UseRangedAttack
{
	
	public UseLaser(Weapon weapon, GameObject target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_LASER_USE_TIME, 0, accuracyPenalty);
		value = weapon.getValue();
		type = weapon.getDamageType();
	}
	
	protected void activate()
	{
			animation(new AnimBeamBurstLaser(actor.getPointPixel(), actor, getTarget(), hit()));
			Audio.playLaser(getActor().getPointPixelSlick());
	}
}
