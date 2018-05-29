package actions.use.arc;

import java.util.ArrayList;

import animations.Boom;
import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
import animations.projectile.AnimArtillery;
import animations.projectile.AnimProjectileMachineGun;
import animations.projectile.AnimSmokeGrenade;
import animations.text.AnimFloatTextCombat;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import sound.Audio;
import values.CoreValues;
import world.cells.Cell;

public class UseArtillery extends UseArc
{
	int timer = 0;
	
	public UseArtillery(Weapon weapon, Point target, float accuracyPenalty) {
		super(weapon.getOwner(), target, PRIMARY_ARTILLERY_USE_TIME, PRIMARY_ARTILLERY_PROJECTILE_SPEED, accuracyPenalty, PRIMARY_ARTILLERY_SCATTER);
		value = weapon.getValue();
		type = weapon.getDamageType();
		radius = PRIMARY_ARTILLERY_RADIUS;
		animationSize = 4.2f;
		spreadDelayRate = 10;
	}
	
	protected void activate()
	{
					
		// Create smoke in each cell
		for(Cell c : cellsInRadius())
		{
			// Deals damage based on distance from center
			int damageLevel = (int) (value * getProximity(c));
			c.addDamageEvent(actor, damageLevel, calculateDelay() + getSpreadDelay(c), type, makeBoomAnimation(c));
			
			// Creates a small amount of smoke in some cells
			int smokeLevel = (int) (350 * Math.max(getProximity(c)-.5, 0));
			c.addSmoke(smokeLevel, calculateDelay() +  getSpreadDelay(c));
		}
			
		// Play sound and projectile animation
		animation(new AnimArtillery(getActor(), getTarget().getPoint()));
		Audio.playGrenade(getActor().getPointPixelSlick());
	}

}
