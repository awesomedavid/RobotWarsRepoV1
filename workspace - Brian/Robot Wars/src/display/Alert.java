package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import values.CoreValues;

public class Alert 
{
	private static int timeLeft;
	private static int timeTotal;
	private static int red;
	private static int green;
	private static int blue;

	private static final int BORDER = 15;
	
	public static void render(Graphics g)
	{
		if(timeLeft > 0)
		{
			g.setColor(new Color(red, green, blue, (int) (125 * percentLeft() )));
			
			g.fillRect(0, 0, Game.getScreenWidth(), BORDER);	// Top (Full)
			g.fillRect(0, Game.getScreenWidth()-BORDER, Game.getScreenWidth(), BORDER);  // Bottom (Full)
			g.fillRect(0, BORDER, BORDER, Game.getScreenHeight()-BORDER*2);  // Left (Partial)
			g.fillRect(Game.getScreenWidth()-BORDER, BORDER, BORDER, Game.getScreenHeight()-BORDER*2);  // Right (Partial)

			timeLeft--;
			
		}
		

	}
	
	public static void set(int time, int red, int green, int blue)
	{
		Alert.timeTotal = time;
		Alert.timeLeft = time;
		Alert.red = red;
		Alert.green = green;
		Alert.blue = blue;
	}
	
	private static float percentLeft()
	{
		return (float) timeLeft / (float) timeTotal;
	}
}
