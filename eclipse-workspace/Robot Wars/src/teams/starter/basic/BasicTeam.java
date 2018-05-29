package teams.starter.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.Robot;
import objects.Team;
import teams.starter.basic.robots.BasicHunter;
import teams.starter.basic.robots.BasicRunner;
import teams.starter.basic.robots.BasicSniper;

public abstract class BasicTeam extends Team
{
	Color myColor;
	
	public BasicTeam(int id) {
		super(id);
		setName("Basic");
		int r = Utility.random(255);
		int g = Utility.random(255);
		int b = Utility.random(255);
		
		if(r + g + b < 200)
		{
			r += 75;
			g += 75;
			b += 75;
		}
		
		myColor = new Color(r, g, b);
	}

	public Color getColor() 
	{
		return myColor;
	}
	

}
