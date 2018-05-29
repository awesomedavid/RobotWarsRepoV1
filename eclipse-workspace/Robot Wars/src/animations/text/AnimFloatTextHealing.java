package animations.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import files.Fonts;
import objects.GameObject;
import objects.Unit;
import values.DisplayValues;
import values.CoreValues;

public class AnimFloatTextHealing extends AnimFloatTextCombat
{

	public AnimFloatTextHealing(String text, GameObject u)
	{
		super(text, u);
		setColor(Color.green);
	}
	
	public AnimFloatTextHealing(String text, GameObject u, int delay)
	{
		super(text, u,delay);
		setColor(Color.green);
	}
	
	public AnimFloatTextHealing(float damage, GameObject u)
	{
		super(damage, u);
		setColor(Color.green);
	}
	

}
