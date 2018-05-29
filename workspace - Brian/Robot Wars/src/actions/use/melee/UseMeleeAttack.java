package actions.use.melee;

import actions.use.UseOnObject;
import animations.text.AnimFloatTextCombat;
import core.Game;
import effects.Damage;
import objects.GameObject;
import objects.Unit;

public abstract class UseMeleeAttack extends UseOnObject
{	
	public UseMeleeAttack(Unit actor, GameObject target, int aimTime) {
		super(actor, target, aimTime, 0, 0);
	}
	

	
	
}
