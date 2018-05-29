package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import files.Images;
import objects.Unit;

public abstract class AnimCross extends Animation 
{
	public AnimCross(float x, float y, float size, int duration) 
	{
		super(x, y, duration);
		this.size = size;
	}
	
	public void render(Graphics g) 
	{
		drawCrossSegment(g, getLong(), getShort());
		drawCrossSegment(g, getShort(), getLong());
	}
	
	abstract float getLong();
	abstract float getShort();
	abstract Color getColor();
	abstract void fill(Graphics g, float x, float y, float w, float h);
	abstract void edge(Graphics g, float x, float y, float w, float h);

	public void drawCrossSegment(Graphics g, float w, float h)
	{
		Color c = getColor();
		
		float percent = (float) Math.sqrt(percentLeft());
		int fadeBG = (int) (percent * 255f * .75f);
		int fadeBorder = (int) (percent * 255f * 1.25f);
		//int lineWidth = (int) (3 * percent);
		
		float aW = w * percent;
		float aH = h * percent;
		
		//g.setLineWidth(lineWidth);
		g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fadeBG));
		
		fill(g, x - aW/2 + CELL_SIZE/2, y - aH/2+ CELL_SIZE, aW, aH);
			
		g.setColor(new Color((int) (c.getRed() * .5f), (int) (c.getGreen() * .5f), (int) (c.getBlue() * .5f), fadeBorder));
		edge(g, x - aW/2 + CELL_SIZE/2, y - aH/2+ CELL_SIZE, aW, aH);

		
		//g.setLineWidth(1);
	}

	
}