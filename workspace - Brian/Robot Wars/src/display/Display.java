package display;

import org.newdawn.slick.Graphics;

import core.Game;
import display.duo.DisplayDuoRobot;
import display.hud.DisplayTeam;
import objects.Robot;
import values.CoreValues;
import values.Settings;

public class Display 
{
	Minimap minimap;
	GameSpeed gameSpeed;
	Victory victory;
	
	public Display()
	{
		minimap = new Minimap();
		gameSpeed = new GameSpeed();
		victory = new Victory();
	}
	
	public void update()
	{
		if(Settings.showMiniMap)
		{
			minimap.update();
		}
			
		if(Settings.showHUD)
		{
			gameSpeed.update();
		}
		
		if(Game.hasWinner())
		{
			victory.update();
		}
	}
	
	public void render(Graphics g)
	{
		if(Settings.showMiniMap)
		{
			minimap.render(g);
		}
		
		if(Settings.showHUD)
		{
			gameSpeed.render(g);
		}
		
		if(Game.hasWinner())
		{
			victory.render(g);
		}

	}
	


}
