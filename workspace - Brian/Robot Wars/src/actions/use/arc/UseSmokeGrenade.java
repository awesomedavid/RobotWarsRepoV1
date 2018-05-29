package actions.use.arc;

import java.util.ArrayList;

import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
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
import world.cells.Cell;

public class UseSmokeGrenade extends UseArc
{
	public UseSmokeGrenade(Weapon weapon, Point target, float accuracyPenalty) {
		super(weapon.getOwner(), target, SECONDARY_SMOKE_GRENADE_USE_TIME, SECONDARY_SMOKE_GRENADE_ANIMATION_SPEED, accuracyPenalty, SECONDARY_SMOKE_GRENADE_SCATTER);
		radius = SECONDARY_SMOKE_GRENADE_RADIUS;
		spreadDelayRate = 40;
	}
	
	protected void activate()
	{					
		// Create smoke in each cell
		for(Cell c : cellsInRadius())
		{
			// The amount of smoke varies per cell, by roll and proximity
			float roll = Utility.random(SECONDARY_SMOKE_GRENADE_DURATION_MIN, SECONDARY_SMOKE_GRENADE_DURATION_MAX);
			int smokeLevel = (int) (roll * getProximity(c));
			
			c.addSmoke(smokeLevel, calculateDelay() + getSpreadDelay(c));
		}
		
		// Play sound and projectile animation
		animation(new AnimSmokeGrenade(getActor(), getTarget().getPoint()));
		Audio.playGrenade(getActor().getPointPixelSlick());
	}	
}

