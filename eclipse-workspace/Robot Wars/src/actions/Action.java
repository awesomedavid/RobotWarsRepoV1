package actions;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import objects.Unit;
import values.ActionValues;
import values.CoreValues;
import values.WeaponValues;

public abstract class Action implements CoreValues, ActionValues, WeaponValues
{	
	protected Color actionColor;
	protected Unit actor;
	protected int duration;
	protected int time;
	private boolean cancelled;
	
	protected Action(Unit actor, int duration)
	{
		actionColor = new Color(150,150,150);
		this.actor = actor;
		this.duration = duration;
		this.time = -1;
	}
	
	public boolean isComplete()
	{
		return time == duration;
	}
	
	public boolean isCancelled()
	{
		return cancelled;
	}
		
	public void update()
	{
		
		if(!actor.isAlive())
		{
			cancel();
			return;
		}
		
		time++;

		if(actor.isAlive() && time == duration && !isCancelled())
		{
			complete();
		}
	}
	
	
	public void render(Graphics g)
	{

	}
	
	public void renderActionProgress(Graphics g)
	{
		final int WIDTH = CoreValues.CELL_SIZE-2;
		final int HEIGHT = 4;
		
		g.setColor(actionColor.darker());
		g.fillRect(actor.getXPixel()+1, actor.getYPixel()-CoreValues.CELL_SIZE-HEIGHT-5, WIDTH, 4);
		g.setColor(Color.black);
		g.drawRect(actor.getXPixel()+1, actor.getYPixel()-CoreValues.CELL_SIZE-HEIGHT-5, WIDTH, HEIGHT);
		
		g.setColor(actionColor.brighter());
		g.fillRect(actor.getXPixel()+1, actor.getYPixel()-CoreValues.CELL_SIZE-HEIGHT-5, (float) WIDTH * this.getPercentComplete(), HEIGHT);
		g.setColor(Color.black);
		g.drawRect(actor.getXPixel()+1, actor.getYPixel()-CoreValues.CELL_SIZE-HEIGHT-5, (float) WIDTH * this.getPercentComplete(), HEIGHT);
	}
	
	public Unit getActor()
	{
		return actor;
	}
	
	public float getPercentComplete()
	{
		return (float) time / (float) duration;
	}
	
	public String toString()
	{
		return getClass().getSimpleName() + "";
	}
	
	public void cancel()
	{
		cancelled = true;
		time = duration;
	}
	
	protected abstract void complete();

}
