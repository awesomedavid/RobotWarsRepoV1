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

public class UseSniper extends UseRangedAttack
{
	public UseSniper(Weapon weapon, GameObject target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_SNIPER_USE_TIME, PRIMARY_SNIPER_ANIMATION_SPEED, accuracyPenalty);
		value = weapon.getValue();
		type = weapon.getDamageType();
	}
	
	protected void activate()
	{

			animation(new AnimProjectileSniper(actor, getTarget(), hit()));
			Audio.playSniper(getActor().getPointPixelSlick());
		
	}
	
	protected void shoot()
	{
		
	}
}
