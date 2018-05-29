package actions.use;


import java.awt.Window.Type;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import actions.Action;
import actions.use.arc.UseSmokeGrenade;
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

public abstract class Use extends Action 
{
	protected float accuracyPenalty;
	protected Unit actor;
	protected float value;
	protected boolean hit;
	protected boolean crit;
	protected int speed;
	protected DamageType type;
	
	public Use(Unit actor, int useTime, int shotSpeed, float accuracyPenalty)
	{
		super(actor, useTime);
		
		actionColor = new Color(255, 135, 0);

		this.actor = actor;
	
		this.accuracyPenalty = accuracyPenalty;
		this.speed = shotSpeed;
		type = DamageType.NORMAL;

	}
	
	public Unit getActor()
	{
		return actor;
	}

	protected abstract void complete();
	protected abstract void activate();

	public int calculateDelay()
	{
		
		return calculateDelay(speed);
	}
	
	// Takes points on grid, not pixels
	public int calculateDelay(Point p, Point t, float speed)
	{
		if(speed <= 0)
		{
			//System.out.println(this + " " + 0);
			return 0;
		}
		else
		{
			//System.out.println(this + " " + (Math.round((Utility.distance(p,  t) * ((float) CoreValues.CELL_SIZE) / speed))));
			return Math.round((Utility.distance(p,  t) * ((float) CoreValues.CELL_SIZE) / speed)); 
		}
	}
	
	public abstract int calculateDelay(float speed);	

	
	public void render(Graphics g)
	{
		super.render(g);
		
		if(duration > 120)
		{
			renderActionProgress(g);
		}

	}

	public void makeAttackRoll()
	{
		double roll = Math.random() - accuracyPenalty;
		
		if(roll > 1)
		{
			crit = true;
			hit = true;
		}
		else if(roll > 0)
		{
			crit = false;
			hit = true;
		}
		else
		{
			crit = false;
			hit = false;
			value = 0;
		}
	}
	
	public boolean hit()
	{
		return hit;
	}
	
	public boolean crit()
	{
		return crit;
	}
	
	public void update()
	{
		super.update();
			
		
		if(!getActor().isAlive())
		{
			cancel();
		}
	}
	

	public void animation(Animation a)
	{
		Game.addAnimation(a);
	}
	
	

}
