package actions.use.melee;

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

public class UseDrill extends UseMeleeAttack
{
	public UseDrill(Weapon weapon, GameObject target) {
		super(weapon.getOwner(), target, SECONDARY_DRILL_USE_TIME);
		value = weapon.getValue();
		type = weapon.getDamageType();
	}
	
	protected void activate()
	{
		
	}

}
