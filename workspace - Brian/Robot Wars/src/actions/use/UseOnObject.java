package actions.use;


import java.awt.Window.Type;

import actions.Action;
import animations.Animation;
import animations.text.AnimFloatTextCombat;
import animations.text.AnimFloatTextDamage;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import effects.Effect;
import item.StoneBlock;
import item.WoodLog;
import objects.Direction;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;

public abstract class UseOnObject extends Use 
{
	protected GameObject targetObject;
	
	public UseOnObject(Unit actor, GameObject targetObject, int useTime, int shotSpeed, float accuracyPenalty) 
	{
		super(actor, useTime, shotSpeed, accuracyPenalty);
		this.targetObject = targetObject;
	}


	

	
	protected void complete()
	{
		//actor.turnTo(target);
		
		makeAttackRoll();
		
		if(crit())
		{
			effect(new Damage(actor, targetObject, calculateDelay(), value * 2, type, true));
		}
		else if(hit())
		{
			effect(new Damage(actor, targetObject, calculateDelay(), value, type, false));
		}
		else
		{
			Game.addAnimation(new AnimFloatTextDamage("Miss!", targetObject, false));
		}
		
		if(getTarget().isAlive())
		{
			activate();
		}
	}
	
	public GameObject getTarget()
	{
		return targetObject;
	}
	
	public int calculateDelay(float speed)
	{
		return calculateDelay(actor.getPoint(), targetObject.getPoint(), speed);
	}
	
	public void update()
	{
		super.update();
				
		if(!targetObject.isAlive())
		{
			cancel();
		}
	}
		
	public void effect(Effect e)
	{
		targetObject.addEffect(e);
	}
	

	

}
