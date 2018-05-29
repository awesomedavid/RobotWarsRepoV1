package actions.use.ranged;

import actions.use.UseOnObject;
import animations.text.AnimFloatTextCombat;
import core.DamageType;
import core.Game;
import effects.Damage;
import objects.GameObject;
import objects.Unit;

public abstract class UseRangedAttack extends UseOnObject
{		
	public UseRangedAttack(Unit actor, GameObject target, int aimTime, int shotSpeed, float accuracyPenalty) {
		super(actor, target, aimTime, shotSpeed, accuracyPenalty);
	}
	

}
