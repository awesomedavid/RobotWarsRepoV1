package item.weapon;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import actions.Action;
import actions.Reload;
import animations.Animation;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import item.equipment.Upgrade;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import values.WeaponValues;
import world.World;
import world.cells.Cell;

public abstract class Weapon extends Upgrade  implements WeaponValues
{
	protected boolean justFired;
	protected int minRange;
	protected int maxRange;
	protected int cooldown;
	protected int cooldownLeft;
	protected int speed;
	protected int heat;
	protected boolean needsAmmo;
	protected boolean targetObject;
	protected boolean targetPoint;
	protected boolean targetAuto;
	protected int curAmmo;
	protected int maxAmmo;
	protected int reloadTime = -1;
	protected String name;
	protected Image image;
	protected boolean isPrimary;
	protected float accuracy = 1.0f;
	protected float accuracyDrop = 0f;
	protected DamageType type;
	protected float value;

	public Weapon(Unit owner)
	{
		super(owner);
		name = this.getClass().getSimpleName();
		needsAmmo = true;
		type = DamageType.NORMAL;
	}
	
	
	public float getHitChance(GameObject target)
	{
		return 1 - getAccuracyPenalty(target);
	}
	
	public float getAccuracyPenalty(GameObject target)
	{
		return  ShotCalculator.calcShot(getOwner(), target).getPenalty() + 		// Cover + Concealment
				getOwner().getAccuracy() * -1 +									// Base player accuracy
				(1 - accuracy) + 											    // Base weapon accuracy
				accuracyDrop*getOwner().getDistance(target) + 					// Distance Drop
				target.getDodge();												// Dodge
	}
	
	public float getAccuracyPenalty(Point p)
	{
		return  getOwner().getAccuracy() * -1 +									// Base player accuracy
				(1 - accuracy);												    // Base weapon accuracy	
	}
	
	public boolean isPrimary()
	{
		return isPrimary;
	}
	
	public boolean isSecondary()
	{
		return !isPrimary;
	}
	
	public float getRange() {
		return getMaxRange();
	}
	
	
	public float getMinRange() {
		return minRange;
	}
	
	public float getMaxRange() {
		return maxRange;
	}
	
	
	public int getReloadTime()
	{
		 return reloadTime; 			 
	}
	
	public boolean usesAmmo()
	{
		return needsAmmo;
	}
	
	public boolean canReload()
	{
		return reloadTime > 0;
	}
	
	public boolean targetPoint()
	{
		return targetPoint;
	}
	
	public boolean targetAuto()
	{
		return targetAuto;
	}
	
	public boolean targetObject()
	{
		return targetObject;
	}
	
	public boolean hasAmmo()
	{
		return curAmmo > 0 || !needsAmmo;
	}
	
	public boolean needsReload()
	{
		return curAmmo == 0 && needsAmmo;
	}
	
	public int getHeatCost()
	{
		return heat;
	}
	
	public int getCurAmmo()
	{
		return curAmmo;
	}
	
	public int getMaxAmmo()
	{
		return maxAmmo;
	}
	


	public float getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
		
	public DamageType getDamageType()
	{
		return type;
	}
	
	public void setDamageType(DamageType type)
	{
		this.type = type;
	}
	
	public void reload()
	{
		getOwner().reload(this);
	}
	
	public void reloadActionComplete()
	{
		if(!getOwner().isAlive() || getOwner().hasAction() && Reload.class.isInstance(getOwner().getAction()))
		{
			curAmmo = maxAmmo;
		}
	}
	
	public void setMaxRange(int value)
	{
		maxRange = value;
	}
	
	public void setMinRange(int value)
	{
		minRange = value;
	}

	public void update() {
		if (cooldownLeft > 0 && getOwner().isAlive()) {
			cooldownLeft--;
		}
		
		justFired = false;
	}

	public boolean canUse(GameObject a) {
		return  canUse() &&													// Check all basic requirements of use
				getOwner().isAlive() && a.isAlive() &&						// Check if the owner and target are alive
				getOwner().inRange(a, this)  &&								// Check that the target is in range
				!noClearShot(a);					    					// Check that the target isn't fully blocked by cover
	}
	
	public boolean canUse(Point p) {
		return  canUse() &&													// Check all basic requirements of use
				getOwner().inRange(p, this)  &&								// Check that the point is in range
				World.inBounds(p);											// Point is valid
	} 
	
	public boolean canUse() {
		return  getOwner() != null &&										// Check null
				getOwner().isAlive() &&										// Check if the owner and target are alive
				!isOnCooldown()  && 										// Check that the shot isn't on cooldown
				 (justFired || (needsAmmo && hasAmmo()) || !needsAmmo) &&   // Check that the weapon needs ammo
				 !getOwner().isOverheated();								// Check that the robot is not overheated
	} 
	
	public Image getImage()
	{
		return image;
	}
	
	public boolean noClearShot(GameObject u)
	{
		return ShotCalculator.calcShot(this.getOwner(), u).getPenalty() >= 1; 
	}
	
	public boolean isOnCooldown()
	{
		return cooldownLeft > 0;
	}	

	// How many frames does it take to reach the target at my speed?
	public int getDelay(Unit a) {
		return (int) (Utility.distance(getOwner(), a) / speed);
	}

	public void action(Action a)
	{
		getOwner().setAction(a);
		cooldownLeft = cooldown;
	}
	
	public String toString()
	{
		return name;
	}
		
	public void expendAmmo()
	{
		curAmmo--;
		justFired = true;
		
		if(!canReload() && curAmmo == 0)
		{
			getOwner().getInventory().remove(this);
		}
	}
	
	public void use(GameObject target)
	{
		if(targetObject() && canUse(target))
		{
			if(usesAmmo())
			{
				expendAmmo();
			}		

			getOwner().addHeat(heat);
		}
	}
	
	public void use()
	{
		if(targetAuto())
		{
			if(usesAmmo())
			{
				expendAmmo();
			}	
			getOwner().addHeat(heat);
		}
	}

	public void use(Point p)
	{
		if(targetPoint())
		{
			if(usesAmmo())
			{
				expendAmmo();
			}	
			getOwner().addHeat(heat);
		}
		else if(targetObject && getObjectFromCell(p) != null && canUse(getObjectFromCell(p)))
		{
			if(usesAmmo())
			{
				curAmmo--;
			}	
			getOwner().addHeat(heat);
		}
	}

	
	public GameObject getObjectFromCell(Point p)
	{
		if(World.getCell(p) != null)
		{
			if(World.getCell(p).hasUnit())
			{
				return World.getCell(p).getUnit();
			}
			else if(World.getCell(p).hasFeature())
			{
				return World.getCell(p).getFeature();
			}			
		}
		return null;
	}

}
