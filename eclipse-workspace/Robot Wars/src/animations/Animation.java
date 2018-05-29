package animations;

import org.newdawn.slick.Graphics;

import core.Game;
import objects.Point;
import values.CoreValues;
import values.WeaponValues;
import values.Settings;

abstract public class Animation implements WeaponValues, CoreValues
{
	protected int ticks;
	protected int duration;
	protected float x;
	protected float y;
	protected float size;
	protected float xOrigin;
	protected float yOrigin;
	protected int delay;
		
	public Animation(float x, float y, int duration) 
	{
		this.x = x;
		this.y = y;
		this.xOrigin = x;
		this.yOrigin = y;
		this.duration = duration;
		this.ticks = 0;
	}

	public Animation(float x, float y, int duration, int delay) 
	{
		this(x, y, duration);
		this.delay = delay;
	}
	
	public boolean isDone() 
	{
		// Negative values represent an object that never goes away
		if(duration < 0)
		{
			return false;
		}
		else
		{
			return ticks > duration;
		}

	}
	
	public void update()
	{		
		if(delay > 0)
		{
			delay--;
			return;
		}
		
		if (!isDone() && ticks >= 0)
		{
			ticks++;
		}	
	}
	

	public int getFadeAlphaValue()
	{
		return (int) (255.0f * percentLeft());
	}
	
	public float percentLeft()
	{
		return 1 - ((float) ticks) / ((float) duration);
	}
	
	public float percentComplete()
	{
		return ((float) ticks) / ((float) duration);
	}	
	
	public int getTicks()
	{
		return ticks;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public abstract void render(Graphics g);

}
