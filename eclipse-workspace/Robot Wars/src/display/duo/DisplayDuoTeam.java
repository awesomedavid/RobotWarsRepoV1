package display.duo;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.Game;
import display.Camera;
import display.hud.DisplayTeam;
import files.Fonts;
import objects.Robot;
import objects.Team;
import values.DisplayValues;
import values.Settings;
import world.World;
import values.CoreValues;

public class DisplayDuoTeam extends DisplayTeam implements DisplayValues{

	public DisplayDuoTeam(Team team)
	{
		super(team);
		
		robots = new ArrayList<DisplayDuoRobot>();
		
		yPos = Game.getScreenHeight()-HUD_HEIGHT;
		
		if(team.getID() == 0)
		{
			xPos = DisplayValues.CARD_INDENT/2;
			resources = new DisplayDuoResources(team, Game.getScreenWidth()/2-225, yPos);
		}
		else
		{
			xPos = Game.getScreenWidth() - CARD_SPACING * 4; 
			resources = new DisplayDuoResources(team, Game.getScreenWidth()/2+180, yPos);
		}
		
		for(int i = 0; i < team.getRobots().size(); i++)
		{
			robots.add(new DisplayDuoRobot(team.getRobots().get(i), i*CARD_SPACING + xPos, yPos));
		}
	}

	public void update()
	{		
		if(Settings.tournamentMode)
		{
			for(DisplayDuoRobot r : robots)
			{
				r.update();
			}
		}
	}
	
	public void render(Graphics g)
	{	
		if(Settings.tournamentMode)
		{
			for(DisplayDuoRobot r : robots)
			{
				r.render(g);
			}
		}
		

		renderName(g);
		resources.render(g);
	}
	
	public void renderName(Graphics g)
	{
		String n = ""+team.toString().toUpperCase();
		Font f = Fonts.calibri24Bold;
		g.setFont(f);

		int w = f.getWidth(n);
		int h = f.getHeight(n);
		
		int indent = 20;
		float x = Camera.getX();
		
		if(team.getID() == 1)
		{
			x = x + Game.getScreenWidth() - w - indent;
		}
		else
		{
			x = x + indent;
		}
		

		float y = yPos + Camera.getY() - 20;
			
		g.setColor(Color.black);
		g.drawString(n, x + 2, y - h/2 + 2);
		g.setColor(team.getColor().brighter(.6f));
		g.drawString(n, x, y - h/2);

	}
	
	
	
}
