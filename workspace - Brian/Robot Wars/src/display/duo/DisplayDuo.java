package display.duo;

import org.newdawn.slick.Graphics;

import core.Game;
import display.CaptureBar;
import display.Display;
import display.hud.DisplayTeam;
import objects.Robot;
import values.CoreValues;
import values.Settings;

public class DisplayDuo extends Display
{
	
	CaptureBar captureBar0;
	CaptureBar captureBar1;
	DisplayTeam team0;
	DisplayTeam team1;
	
	public DisplayDuo()
	{
		team0 = new DisplayDuoTeam(Game.getTeam(0));
		team1 = new DisplayDuoTeam(Game.getTeam(1));
		
		captureBar0 = new CaptureBar(Game.getTeam(0));
		captureBar1 = new CaptureBar(Game.getTeam(1));
	}
	
	public void update()
	{
		super.update();
	
		if(Settings.showHUD)
		{

				team0.update();
				team1.update();
			
		}
		
	}
	
	public void render(Graphics g)
	{
		super.render(g);

		if(Settings.showHUD)
		{
			team0.render(g);
			team1.render(g);
			captureBar0.render(g);
			captureBar1.render(g);	
		}
	}
	


}
