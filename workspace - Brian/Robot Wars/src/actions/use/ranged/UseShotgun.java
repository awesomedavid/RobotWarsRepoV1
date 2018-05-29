package actions.use.ranged;

import animations.projectile.AnimProjectileShotgun;
import animations.text.AnimFloatTextDamage;
import core.Game;
import core.Utility;
import effects.Damage;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import sound.Audio;
import values.CoreValues;

public class UseShotgun extends UseRangedAttack
{
	int damageCount = 0;
	
	public UseShotgun(Weapon weapon, GameObject target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_SHOTGUN_USE_TIME, PRIMARY_SHOTGUN_ANIMATION_SPEED, accuracyPenalty);
		value = weapon.getValue();
		type = weapon.getDamageType();
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

			calculateDamage();
			applyDamage();

			for(int i = 0; i < PRIMARY_SHOTGUN_ANIMATION_COUNT; i++)
			{
				animation(new AnimProjectileShotgun(actor, targetObject, hit()));
			}
			
			Audio.playShotgun(getActor().getPointPixelSlick());
			
		
	}
	
	public void calculateDamage()
	{
		for(int i = 0; i < PRIMARY_SHOTGUN_ANIMATION_COUNT; i++)
		{
			makeAttackRoll();
			
			if(crit())
			{
				damageCount += PRIMARY_SHOTGUN_DAMAGE / (float) PRIMARY_SHOTGUN_ANIMATION_COUNT * 2;
			}
			else if(hit())
			{
				damageCount += PRIMARY_SHOTGUN_DAMAGE / (float) PRIMARY_SHOTGUN_ANIMATION_COUNT;
			}
		}
	}
	
	public void applyDamage()
	{
		if(damageCount > PRIMARY_SHOTGUN_DAMAGE)
		{
			effect(new Damage(actor, getTarget(), calculateDelay(), damageCount, type, true));
		}
		else if(damageCount > 0)
		{
			effect(new Damage(actor, getTarget(), calculateDelay(), damageCount, type, false));
		}
		else
		{
			Game.addAnimation(new AnimFloatTextDamage("Miss!", getTarget(), false));
		}
	}
}
