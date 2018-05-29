package world.cells;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;

import core.Utility;
import values.CoreValues;

class Smoke implements CoreValues {
	private int timer;
	private Ellipse[] cloudShapes;
	int[] cloudGrey;
	final int NUM_CLOUDS = 7;
	final float SCALE_CLOUDS_MIN = .4f;
	final float SCALE_CLOUDS_MAX = .8f;

	ArrayList<SmokeEvent> events;
	Cell c;
	
	Smoke(Cell c)
	{
		events = new ArrayList<SmokeEvent>();
		cloudGrey = new int[NUM_CLOUDS];
		cloudShapes = new Ellipse[NUM_CLOUDS];
		
		int sizeVar = 5;
		int locVar = 15;

		for(int i = 0; i < NUM_CLOUDS; i++)
		{
			cloudGrey[i] = Utility.random(100, 150);
			cloudShapes[i] = new Ellipse(c.getX()*CELL_SIZE + CELL_SIZE/2+Utility.random(-locVar, locVar),
									    c.getY()*CELL_SIZE+CELL_SIZE/2+Utility.random(-locVar, locVar),
									    (int)(CELL_SIZE*Utility.random(SCALE_CLOUDS_MIN, SCALE_CLOUDS_MAX))+Utility.random(-sizeVar, sizeVar),
									    (int)(CELL_SIZE*Utility.random(SCALE_CLOUDS_MIN, SCALE_CLOUDS_MAX))+Utility.random(-sizeVar, sizeVar));
		}
		this.c = c;
	}
	
	public boolean isActive()
	{
		return timer > 0;
		
	}
	public void addTime(int amount, int delay)
	{
		events.add(new SmokeEvent(amount, delay));
	}
	
	public void update()
	{
		if(timer > 0)
		{
			timer--;
		}

		for(int i = 0; i < events.size(); i++)
		{
			SmokeEvent s = events.get(i);
			s.delayLeft--;
						
			if(s.delayLeft == 0)
			{
				timer += s.amount;
				events.remove(s);
			}
		}
		
	}
	
	public void render(Graphics g)
	{
		if(isActive())
		{
			for(int i = 0; i < NUM_CLOUDS; i++)
			{
				int c = (int) Math.min(70, Math.sqrt(timer) * 4);
				g.setColor(new Color(cloudGrey[i], cloudGrey[i], cloudGrey[i], c));
				g.fill(cloudShapes[i]);
			}
		//	setDebugNum(c);

//			g.fillRect(x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE, CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);	

			
		}
	}
	
	class SmokeEvent
	{
		public int amount;
		public int delayLeft;
		
		SmokeEvent(int amount, int delayLeft)
		{
			this.amount = amount;
			this.delayLeft = delayLeft;
		}

	}
}
