package animations.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import animations.Animation;
import core.Utility;
import files.Fonts;
import objects.GameObject;
import objects.Unit;
import values.CoreValues;

public class AnimFloatText extends Animation{
	
	Font myFont;
	String text;
	int red = 255;
	int green = 255;
	int blue = 255;
	
	public AnimFloatText(String text, float x, float y, int duration, int delay) {
		super(x, y, duration, delay);
		this.text = text;
		myFont = Fonts.calibri12Bold;
	}
	
	public AnimFloatText(String text, float x, float y, int duration) {
		this(text, x, y, duration, 0);
	}
	
	public AnimFloatText(String text, GameObject u, int duration, int delay)
	{
		this(text, 
			 u.getXPixel()+CoreValues.CELL_SIZE/2 + Utility.random(-CoreValues.CELL_SIZE/4, CoreValues.CELL_SIZE/4), 
			 u.getYPixel() + Utility.random(-CoreValues.CELL_SIZE/4, CoreValues.CELL_SIZE/4),
			 duration);
	}
	
	public AnimFloatText(String text, GameObject u, int duration)
	{
		this(text, u, duration, 0);
	}
	
	public void setColor(int r, int g, int b)
	{
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	public void setColor(Color c)
	{
		this.red = c.getRed();
		this.green = c.getGreen();
		this.blue = c.getBlue();
	}


	public void update()
	{
		super.update();
		y--;
	}
	
	public void render(Graphics g)
	{
		if(delay == 0)
		{
			g.setFont(myFont);
			g.setColor(new Color(80, 80, 80, getFadeAlphaValue()));
			g.drawString(text, x+2, y+2);
			g.setColor(new Color(red, green, blue, getFadeAlphaValue()));
			g.drawString(text, x, y);
		
		}

	}

}
