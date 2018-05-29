package animations.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import files.Fonts;
import objects.GameObject;
import objects.Unit;
import values.DisplayValues;
import values.CoreValues;

public class AnimFloatTextCombat extends AnimFloatText{
	
	int actualValue;

	public AnimFloatTextCombat(String text, GameObject u)
	{
		super(text, u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION);
		actualValue = -1;
		formatText();
		setFont();
	}
	
	public AnimFloatTextCombat(String text, GameObject u, int delay)
	{
		super(text, u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION, delay);
		actualValue = -1;
		formatText();
		setFont();
	}
	
	public AnimFloatTextCombat(float damage, GameObject u)
	{
		super(""+ Math.round(damage), u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION);
		actualValue = Math.round(damage);
		formatText();
		setFont();
	}
	
	public void formatText()
	{
		if(actualValue == -1)
		{
			return;
		}
		
		if(actualValue == 0)
		{
			text = "Miss!";
		}
	}
	
	public void setFont()
	{
		if(actualValue == 0)
		{ 
			myFont = Fonts.calibri12Bold;
		}
		else if(actualValue <= 3)
		{ 
			myFont =  Fonts.calibri14Bold;
		}
		else if(actualValue > 3 && actualValue <= 10)
		{ 
			myFont =  Fonts.calibri16Bold;
		}
		else if(actualValue > 10 && actualValue <= 19)
		{ 
			myFont =  Fonts.calibri18Bold;
		}
		else if(actualValue > 19 && actualValue <= 49)
		{ 
			myFont =  Fonts.calibri20Bold;
		}
		else
		{
			myFont = Fonts.calibri22Bold;
		}
	}


}
