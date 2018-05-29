package display.ffa;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import core.Game;
import display.CaptureBar;
import display.Display;
import display.duo.DisplayDuoRobot;
import display.hud.DisplayTeam;
import objects.Robot;
import objects.Team;
import values.CoreValues;
import values.Settings;

public class DisplayFFA extends Display
{
	List<DisplayTeam> teams;
	List<CaptureBar> captureBars;
	
	public DisplayFFA()
	{
		teams = new ArrayList<DisplayTeam>();
		captureBars = new ArrayList<CaptureBar>();
		
		for(Team t : Game.getTeams())
		{
			teams.add(new DisplayFFATeam(t));
		}
		
		for(Team t : Game.getTeams())
		{
			captureBars.add(new CaptureBar(t));
		}	
	}

	public void update()
	{
		super.update();
	
		if(Settings.showHUD)
		{
			for(DisplayTeam d : teams)
			{
				d.update();
			}			
		}
	}
	
	public void render(Graphics g)
	{
		super.render(g);

		if(Settings.showHUD)
		{
			for(DisplayTeam d : teams)
			{
				d.render(g);
			}	
			
			for(CaptureBar c : captureBars)
			{
				c.render(g);
			}	
			
		}
	}

}
