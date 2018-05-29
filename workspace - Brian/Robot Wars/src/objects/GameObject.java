package objects;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


import animations.text.AnimFloatTextCombat;
import animations.text.AnimFloatTextDamage;
import animations.text.AnimFloatTextHealing;
import core.DamageType;
import core.Game;
import effects.Effect;
import values.CoreValues;
import values.RobotValues;
import values.Settings;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;

public abstract class GameObject implements CoreValues
{
	protected SpriteSheet sheet;
	protected Image image;
	protected GameContainer gc;
	private List<Effect> effects;
	
	private int timer = 0;
	protected int x;
	protected int y;
	private Team team;
	protected float theta = 0;

	protected float curHealth;
	protected float maxHealth;
	protected float curShield;
	protected float maxShield;
	protected float curPlating;
	protected float maxPlating;
	protected float dodge;
	protected int kills;
	protected int deaths;
	
	protected boolean isAlive;
	
	private Direction direction;
	
	private int timeSinceLastHit = Integer.MAX_VALUE;
	protected int shieldRegenTimer = 0;
	protected int shieldRegenFrequency = RobotValues.SHIELD_REGEN_FREQUENCY;
	protected int shieldRegenAmount = 0;
	
	public GameObject(int x, int y, int teamID)  {
		image = null;
		effects = new ArrayList<Effect>();
		this.x = x;
		this.y = y;
		direction = Direction.EAST;
		team = Game.getTeam(teamID);
		isAlive = true;
	}
	
	public float getDodge()
	{
		return dodge;
	}
	
	public float getCurDurability()
	{
		return getCurHealth() + getCurPlating() + getCurShield();
	}
	
	public float getMaxDurability()
	{
		return getMaxHealth() + getMaxPlating() + getMaxShield();
	}

	public Direction getDirection()
	{
		return direction;
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public SpriteSheet getSheet()
	{
		return sheet;
	}
	
	public boolean facing(Direction d)
	{
		return getDirection() == d;
	}

	public void turn(Direction d)
	{
		direction = d;
		
		if(getDirection() == Direction.WEST)
		{
			theta = 180;
		}
		else if(getDirection() == Direction.NORTH)
		{
			theta = 270;
		}
		else if(getDirection() == Direction.EAST)
		{
			theta = 0;
		}
		else if(getDirection() == Direction.SOUTH)
		{
			theta = 90;
		}
		
	}
	
	public void manageEffects()
	{
		for(int i = effects.size() - 1; i >= 0; i--)
		{
			effects.get(i).update();
			
			if(effects.get(i).isExpired())
			{
				effects.remove(i);
			}
		}
	}
	
	public boolean hasEffect(Class<? extends Effect> clazz)
	{
		for(Effect e : effects)
		{
			if(clazz.isInstance(e)) {
				return true;
			}
		}
		return false;
	}
	
	public int getEffectDuration(Class<? extends Effect> clazz)
	{
		for(Effect e : effects)
		{
			if(clazz.isInstance(e)) {
				return e.getTimeLeft();
			}
		}
		return -1;
	}
	
	public void addEffect(Effect e)
	{
		if(e.isUnique())
		{
			for(Effect i : effects)
			{
				if(e.getClass().isInstance(i))
				{
					if(e.getTimeLeft() > i.getTimeLeft())
					{
						i = e;
					}
				}
			}
		}
		
		effects.add(e);
	}
	
	public final void turn(int degrees) {

		if (degrees > 360)
			degrees -= 360;
		if (degrees < 0)
			degrees += 360;

		theta = degrees;
	}
	
	public final void turnTo(Point p) {
		turnTo(p.getX(), p.getY());
	}

	public final void turnTo(float x, float y) {
		turn((int) getAngleToward(x, y));
	}

	public final void turnTo(GameObject o) {
		if (o == null)
			return;
		turnTo(o.getXPixel(), o.getYPixel());
	}
	
	public Team getTeam() {
		return team;
	}

	public void render(Graphics g) {
		if (image != null && isAlive()) {
			image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
			image.setRotation(theta);
			image.draw(getXPixel(), getYPixel());
		}
	
	}
	
	public final float getAngleToward(float targetX, float targetY) 
	{		
		float yDiff = targetY - getYPixel();
		float xDiff = targetX - getXPixel();
		
		float angle = 0;

		// Quadrant 1 - Down and Right
		if (xDiff >= 0 && yDiff >= 0) {
			angle = (float) Math.toDegrees(Math.atan(yDiff / xDiff));
		}

		// Quadrant 2 - Down and Left
		else if (xDiff <= 0 && yDiff >= 0) {
			angle = (float) Math.toDegrees(Math.atan(yDiff / xDiff)) + 180;
		}

		// Quadrant 3 - Up and Left
		else if (xDiff <= 0 && yDiff <= 0) {
			angle = (float) Math.toDegrees(Math.atan(yDiff / xDiff)) + 180;
		}

		// Quadrant 4 - Up and Right
		else if (xDiff >= 0 && yDiff <= 0) {
			angle = (float) Math.toDegrees(Math.atan(yDiff / xDiff));
		}

		if (angle < 0) {
			angle = 360 + angle;
		}
		if (angle > 360) {
			angle = angle % 360;
		}

		return angle;
	}
	
	public void update() {
		timer++;
		
		manageEffects();
		
		if(timeSinceLastHit < Integer.MAX_VALUE)
		{
			timeSinceLastHit++;
		}
		
		if(shieldRegenTimer > 0)
		{
			shieldRegenTimer--;
		}
		else if(timeSinceLastHit > RobotValues.SHIELD_RECOVERY_TIME)
		{
			regainShield(shieldRegenAmount);
			shieldRegenTimer = shieldRegenFrequency;
		}
		
		if (theta > 360) {
			theta -= 360;
		}
		if (theta < 0) {
			theta += 360;
		}
	}

	public void takeDamage(float amount, GameObject source, boolean isCritical)
	{
		takeDamage(amount, DamageType.NORMAL, source, isCritical);
	}
	
	public void regainShield(float amount)
	{
		curShield += amount;
		if(curShield > maxShield)
		{
			curShield = maxShield;
		}
	}
	
	
	public void regainPlating(float amount)
	{
		curPlating += amount;
		
		if(curPlating > maxPlating)
		{
			curPlating = maxPlating;
		}
	}
	
	public void regainHealth(float amount)
	{
		curHealth += amount;
		
		if(curHealth > maxHealth)
		{
			curHealth = maxHealth;
		}
		
		if(Settings.showFloatText)
		{
			Game.addAnimation(new AnimFloatTextHealing(amount, this));
		}
		
	}
	
	public void regainAll(float amount)
	{
		float amountLeft = 0;
		curHealth += amount;
				
		if(curHealth > maxHealth)
		{
			amountLeft = curHealth - maxHealth;
			curHealth = maxHealth;
		}
		
		if(amountLeft > 0)
		{			
			curPlating += amountLeft / 2;
			
			if(curPlating > maxPlating)
			{
				amountLeft = (curPlating - maxPlating) * 2;
				curPlating = maxPlating;
			}
		}
		
		if(amountLeft > 0)
		{			
			curShield += amountLeft;
			
			if(curShield > maxShield)
			{
				curShield = maxShield;
			}
		}
		
		
		
		if(Settings.showFloatText)
		{
			Game.addAnimation(new AnimFloatTextHealing(amount, this));
		}
		
	}
	
	public void takeDamage(float amount, DamageType type, GameObject source, boolean isCritical)
	{
		timeSinceLastHit = 0;
		
		float damageRemaining = amount;
		float actualDamageDone = 0;
		
		/*************** SHIELD ****************/
		
		// Energy weapons deal double damage for this phase
		if(type.equals(DamageType.ENERGY))
		{
			damageRemaining *= 2;
		}
		
		// If the shield can absorb it all
		if(curShield > damageRemaining)
		{
			curShield = curShield - damageRemaining;
			actualDamageDone += damageRemaining;
			resolveDamage(actualDamageDone, isCritical);
			return;
		}
		
		// Roll over remaining damage.  Shield is zero.
		else
		{
			damageRemaining = damageRemaining - curShield;
			actualDamageDone += curShield;
			curShield = 0;
		}
		
		// Energy weapon damage is reverted
		if(type.equals(DamageType.ENERGY))
		{
			damageRemaining /= 2;
		}
		
		/*************** PLATING ****************/
		
		// All non-pierce weapons deal half damage for this phase
		if(!type.equals(DamageType.PIERCE))
		{
			damageRemaining /= 2;
		}
		
		// If the plating can absorb it all
		if(curPlating > damageRemaining)
		{
			curPlating = curPlating - damageRemaining;
			actualDamageDone += damageRemaining;
			resolveDamage(actualDamageDone, isCritical);
			return;
		}		
		
		// Roll over the remaining damage.  Plating is zero.
		else
		{
			damageRemaining = damageRemaining - curPlating;
			actualDamageDone += curPlating;
			curPlating = 0;
		}
		
		// All non-pierce weapons damage is reverted
		if(!type.equals(DamageType.PIERCE))
		{
			damageRemaining *= 2;
		}
		
		/*************** HEALTH ****************/
		
		// Explosive weapons deal double damage for this phase
		if(type.equals(DamageType.EXPLOSIVE))
		{
			damageRemaining *= 2;
		}
		
		
		if(curHealth > damageRemaining)
		{
			curHealth = curHealth - damageRemaining;
			actualDamageDone += damageRemaining;
			resolveDamage(actualDamageDone, isCritical);
		}
		else
		{
			resolveDamage(actualDamageDone + damageRemaining, isCritical);
			
			resolveDeath(source);
			
			die();
		}
	}
	
	public void resolveDeath(GameObject source)
	{
		if(isAlive && this instanceof Robot)
		{
			// Check for friendly fire kill;  if so, all other teams get a kill
			if(source.getTeam() != null && getTeam() != null && source.getTeam() == getTeam())
			{
				for(Team t : Game.getTeams())
				{
					t.addKill();
				}
			}
			
			// The attacker gets the kill
			else
			{
				source.addKill();
			}

		}
	}
	
	public void resolveDamage(float damage, boolean isCritical)
	{
		if(Settings.showFloatText && this instanceof Unit)
		{
			Game.addAnimation(new AnimFloatTextDamage(damage, this, isCritical));
		}
	}
	

	
	public void die()
	{
		if(isAlive())
		{
			deaths++;
			isAlive = false;
			curHealth = 0;
			curShield = 0;
			curPlating = 0;
			
			if(this instanceof Unit)
			{
				getCell().removeUnit();
			}
			else if(this instanceof Feature)
			{
				getCell().removeFeature();
			}
		}
	}
		
	public int getDeaths()
	{
		return deaths;
	}
	
	public void addDeath()
	{
		deaths++;
		
		if(getTeam() != null)
		{
			getTeam().addDeath();
		}
	}
	
	public int getKills()
	{
		return kills;
	}
	
	public void addKill()
	{
		kills++;
		
		if(getTeam() != null)
		{
			getTeam().addKill();
		}
		
	}
	
	public Cell getCell()
	{
		return World.getCell(x, y);
	}
	
	public void setCell(Cell c)
	{
		x = c.getX();
		y = c.getY();
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	
	public int getX() {
		return x;
	}

	public int getXPixel()
	{
		return x * CoreValues.CELL_SIZE;
	}
	public int getY() {
		return y;
	}
	

	public int getYPixel()
	{
		return y * CoreValues.CELL_SIZE;
	}
	
	public Point getPoint()
	{
		return new Point(x, y);
	}
	
	public Point getPointPixel()
	{
		return new Point(x * CoreValues.CELL_SIZE, y* CoreValues.CELL_SIZE);
	}
	
	public org.newdawn.slick.geom.Point getPointPixelSlick()
	{
		return new org.newdawn.slick.geom.Point(x * CoreValues.CELL_SIZE, y* CoreValues.CELL_SIZE);
	}

	public float getTheta() {
		return theta;
	}
	
	public float getCurHealth()
	{
		return curHealth;
	}
	
	public float getMaxHealth()
	{
		return maxHealth;
	}
	
	public float getCurPlating()
	{
		return curPlating;
	}
	
	public float getMaxPlating()
	{
		return maxPlating;
	}
	
	public float getCurShield()
	{
		return curShield;
	}
	
	public float getMaxShield()
	{
		return maxShield;
	}

}
