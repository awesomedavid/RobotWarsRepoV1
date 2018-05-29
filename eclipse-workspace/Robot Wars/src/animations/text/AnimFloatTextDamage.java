package animations.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.DamageType;
import files.Fonts;
import objects.GameObject;
import objects.Unit;
import values.DisplayValues;
import values.CoreValues;

public class AnimFloatTextDamage extends AnimFloatTextCombat
{
	final Color NORMAL_COLOR = new Color(255, 255, 255);
	final Color CRITICAL_COLOR = new Color(255, 255, 0);
	
	public AnimFloatTextDamage(String text, GameObject u, boolean isCritical)
	{
		super(text, u);
		setColor(isCritical);
	}
	
	public AnimFloatTextDamage(String text, GameObject u, int delay, boolean isCritical)
	{
		super(text, u,delay);
		setColor(isCritical);
	}
	
	public AnimFloatTextDamage(float damage, GameObject u, boolean isCritical)
	{
		super(damage, u);
		setColor(isCritical);
	}
	
	public void setColor(boolean isCritical)
	{
		if(isCritical)
		{
			super.setColor(CRITICAL_COLOR);
		}
		else 
		{
			super.setColor(NORMAL_COLOR);
		}
	}
	

}
