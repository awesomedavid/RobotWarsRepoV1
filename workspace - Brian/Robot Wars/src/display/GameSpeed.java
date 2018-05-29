package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.Game;
import files.Fonts;
import values.CoreValues;
import values.Settings;

public class GameSpeed 
{
	float xPos;
	float yPos;
	String message;
	Font myFont;
	
	GameSpeed()
	{
		xPos = Game.getScreenWidth() * .497f;
		yPos = Game.getScreenHeight() * .93f;
		message = "";
		myFont = Fonts.calibri24Bold;
	}
	
	public void update()
	{
		if(Game.isPaused())
		{
			message = "||";
		}
		else
		{
			message = "";
			for(int i = 0; i < Settings.gameSpeed; i++)
			{
				message += ">";
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.setFont(myFont);
		g.setColor(new Color(40, 40, 40, 255));
		g.drawString(message, xPos+Camera.getX() - myFont.getWidth(message)/2+2, yPos+Camera.getY()+2);
		g.setColor(Color.white);
		g.drawString(message, xPos+Camera.getX() - myFont.getWidth(message)/2, yPos+Camera.getY());

	}
}
